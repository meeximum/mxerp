package at.mxerp.services.auditing;

import java.util.Date;
import java.util.List;
import java.util.UUID;

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

import at.mxerp.db.erp.AuditHeader;
import at.mxerp.db.erp.AuditPosition;
import at.mxerp.services.auditing.AuditHelper.FieldCompare;
import at.mxerp.utils.Helper;

public class AuditListener {
  private DataContext context;

  public AuditListener(CayenneRuntime runtime) {
    context = (DataContext) runtime.newContext();
  }

  private AuditHeader createAuditHeader(CayenneDataObject obj, String action, String createdBy) {
    AuditHeader header = context.newObject(AuditHeader.class);
    header.setId(UUID.randomUUID().toString());
    header.setTableName(obj.getObjectContext().getEntityResolver().getObjEntity(obj).getDbEntityName());
    header.setRecordId((String) Reflect.on(obj).call("getId").get());
    header.setAction(action);
    header.setCreatedBy(createdBy);
    header.setCreatedAt(new Date());

    return header;
  }

  private void createAuditPosition(AuditHeader header, String field, String value_old, String value_new) {
    AuditPosition position = context.newObject(AuditPosition.class);
    position.setId(UUID.randomUUID().toString());
    position.setAuditHeader(header);
    position.setField(field);
    if (value_old != null)
      position.setValueOld(value_old);
    else
      position.setValueOld(null);
    if (value_new != null)
      position.setValueNew(value_new);
    else
      position.setValueNew(null);

    context.commitChanges();
  }

  @PostPersist(entityAnnotations = Auditing.class)
  void postPersist(CayenneDataObject obj) {
    if (obj.getClass().getAnnotation(Auditing.class) != null) {
      this.createAuditHeader(obj, "I", Helper.getUserName());
      context.commitChanges();
    }
  }

  @PostRemove(entityAnnotations = Auditing.class)
  void postRemove(CayenneDataObject obj) {
    if (obj.getClass().getAnnotation(Auditing.class) != null) {
      this.createAuditHeader(obj, "D", Helper.getUserName());
      context.commitChanges();
    }
  }

  @PreUpdate(entityAnnotations = Auditing.class)
  void postUpdate(CayenneDataObject obj) {
    AuditHeader header = this.createAuditHeader(obj, "U", Helper.getUserName());

    Expression qualifier = ExpressionFactory.matchExp("id", Reflect.on(obj).call("getId").get());

    SelectQuery<? extends CayenneDataObject> selectQuery = SelectQuery.query(obj.getClass(), qualifier);
    selectQuery.setFetchLimit(1);
    List<? extends CayenneDataObject> cdos = context.select(selectQuery);

    CayenneDataObject cdo_old = cdos.get(0);

    for (FieldCompare fc : AuditHelper.getDifferences(cdo_old, obj))
        this.createAuditPosition(header, fc.getField(), fc.getVal1(), fc.getVal2());
    
  }
}
