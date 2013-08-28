package services.auditing;

import java.lang.reflect.Method;
import java.util.GregorianCalendar;
import java.util.List;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.access.DataContext;
import org.apache.cayenne.annotation.PostPersist;
import org.apache.cayenne.annotation.PostRemove;
import org.apache.cayenne.annotation.PreUpdate;
import org.apache.cayenne.configuration.CayenneRuntime;
import org.apache.cayenne.exp.Expression;
import org.apache.cayenne.exp.ExpressionFactory;
import org.apache.cayenne.query.SelectQuery;
import org.joor.Reflect;

import services.auditing.AuditHelper.FieldCompare;
import utils.Helper;
import erp.AuditHead;
import erp.AuditPos;

public class AuditListener {
  private DataContext context;

  public AuditListener(CayenneRuntime runtime) {
    context = (DataContext) runtime.newContext();
  }

  private AuditHead createAuditHead(CayenneDataObject obj, String action, String transaction, String changedBy, String sourceapp) {
    AuditHead head = context.newObject(AuditHead.class);

    boolean foundGetGuid = false;
    boolean foundGetId = false;
    String clob = "";

    for (Method m : obj.getClass().getMethods())
      if (m.isAnnotationPresent(Auditing.class))
        if (!"".equals(m.getAnnotation(Auditing.class).literal()))
          clob += m.getAnnotation(Auditing.class).literal() + "=" + Reflect.on(obj).call(m.getName()).get() + ";";

    head.setKeyFields(clob);
    head.setTablename(obj.getClass().getAnnotation(Auditing.class).tableName());

    for (Method m : obj.getClass().getMethods())
      if (m.getName().equals("getGuid"))
        foundGetGuid = true;
      else if (m.getName().equals("getId"))
        foundGetId = true;

    if (foundGetGuid)
      head.setRecordId((String) Reflect.on(obj).call("getGuid").get());
    else if (foundGetId)
      head.setRecordId((String) Reflect.on(obj).call("getId").get());
    else
      head.setRecordId(null);

    head.setAction(action);
    head.setTransaction(transaction);
    head.setChangedBy(changedBy);
    head.setSourceapp(sourceapp);
    head.setTimestamp(GregorianCalendar.getInstance().getTime());

    return head;
  }

  private String createAuditPos(AuditHead head, String field, String value_old, String value_new) {
    AuditPos pos = context.newObject(AuditPos.class);

    pos.setToCcAuditHead(head);
    pos.setHeadId(head.getGuid());
    pos.setField(field);
    if (value_old != null && value_old.length() <= 80)
      pos.setValueOld(value_old);
    else
      pos.setValueOld(null);
    if (value_new != null && value_new.length() <= 80)
      pos.setValueNew(value_new);
    else
      pos.setValueNew(null);

    context.commitChanges();

    return pos.getGuid();
  }

  @PostPersist(entityAnnotations = Auditing.class)
  void postPersist(CayenneDataObject obj) {
    if (obj.getClass().getAnnotation(Auditing.class) != null) {
      this.createAuditHead(obj, "I", "-", Helper.getUserName(), "test");
      context.commitChanges();
    }
  }

  @PostRemove(entityAnnotations = Auditing.class)
  void postRemove(CayenneDataObject obj) {
    if (obj.getClass().getAnnotation(Auditing.class) != null) {
      this.createAuditHead(obj, "D", "-", Helper.getUserName(), "test");
      context.commitChanges();
    }
  }

  @PreUpdate(entityAnnotations = Auditing.class)
  void postUpdate(CayenneDataObject obj) {
    boolean foundGetId = false;
    boolean foundGetGuid = false;
    AuditHead head = this.createAuditHead(obj, "U", "-", Helper.getUserName(), "test");

    for (Method m : obj.getClass().getMethods())
      if (m.getName().equals("getGuid"))
        foundGetGuid = true;
      else if (m.getName().equals("getId"))
        foundGetId = true;

    Expression qualifier = null;
    if (foundGetGuid)
      qualifier = ExpressionFactory.matchExp("guid", Reflect.on(obj).call("getGuid").get());
    else if (foundGetId)
      qualifier = ExpressionFactory.matchExp("id", Reflect.on(obj).call("getId").get());
    else {
      System.err.println("ERROR: Updatable record not found");
      return;
    }

    SelectQuery<? extends CayenneDataObject> selectQuery = SelectQuery.query(obj.getClass(), qualifier);
    selectQuery.setFetchLimit(1);
    List<? extends CayenneDataObject> cdos = context.select(selectQuery);

    if (cdos.size() == 1) {
      CayenneDataObject cdo_old = cdos.get(0);

      for (FieldCompare fc : AuditHelper.getDifferences(cdo_old, obj))
        this.createAuditPos(head, fc.getField(), fc.getVal1(), fc.getVal2());
    } else
      System.err.println("ERROR: Updatable record not found");
  }
}
