package at.mxerp.db.erp;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;

/**
 * Class _AuditPosition was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _AuditPosition extends CayenneDataObject {

    @Deprecated
    public static final String FIELD_PROPERTY = "field";
    @Deprecated
    public static final String ID_PROPERTY = "id";
    @Deprecated
    public static final String VALUE_NEW_PROPERTY = "valueNew";
    @Deprecated
    public static final String VALUE_OLD_PROPERTY = "valueOld";
    @Deprecated
    public static final String AUDIT_HEADER_PROPERTY = "auditHeader";

    public static final String ID_PK_COLUMN = "id";

    public static final Property<String> FIELD = new Property<String>("field");
    public static final Property<String> ID = new Property<String>("id");
    public static final Property<String> VALUE_NEW = new Property<String>("valueNew");
    public static final Property<String> VALUE_OLD = new Property<String>("valueOld");
    public static final Property<AuditHeader> AUDIT_HEADER = new Property<AuditHeader>("auditHeader");

    public void setField(String field) {
        writeProperty("field", field);
    }
    public String getField() {
        return (String)readProperty("field");
    }

    public void setId(String id) {
        writeProperty("id", id);
    }
    public String getId() {
        return (String)readProperty("id");
    }

    public void setValueNew(String valueNew) {
        writeProperty("valueNew", valueNew);
    }
    public String getValueNew() {
        return (String)readProperty("valueNew");
    }

    public void setValueOld(String valueOld) {
        writeProperty("valueOld", valueOld);
    }
    public String getValueOld() {
        return (String)readProperty("valueOld");
    }

    public void setAuditHeader(AuditHeader auditHeader) {
        setToOneTarget("auditHeader", auditHeader, true);
    }

    public AuditHeader getAuditHeader() {
        return (AuditHeader)readProperty("auditHeader");
    }


}
