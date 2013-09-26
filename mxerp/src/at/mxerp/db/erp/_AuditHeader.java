package at.mxerp.db.erp;

import java.util.Date;
import java.util.List;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;

/**
 * Class _AuditHeader was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _AuditHeader extends CayenneDataObject {

    @Deprecated
    public static final String ACTION_PROPERTY = "action";
    @Deprecated
    public static final String CREATED_AT_PROPERTY = "createdAt";
    @Deprecated
    public static final String CREATED_BY_PROPERTY = "createdBy";
    @Deprecated
    public static final String ID_PROPERTY = "id";
    @Deprecated
    public static final String RECORD_ID_PROPERTY = "recordId";
    @Deprecated
    public static final String TABLE_NAME_PROPERTY = "tableName";
    @Deprecated
    public static final String AUDIT_POSITIONS_PROPERTY = "auditPositions";

    public static final String ID_PK_COLUMN = "id";

    public static final Property<String> ACTION = new Property<String>("action");
    public static final Property<Date> CREATED_AT = new Property<Date>("createdAt");
    public static final Property<String> CREATED_BY = new Property<String>("createdBy");
    public static final Property<String> ID = new Property<String>("id");
    public static final Property<String> RECORD_ID = new Property<String>("recordId");
    public static final Property<String> TABLE_NAME = new Property<String>("tableName");
    public static final Property<List<AuditPosition>> AUDIT_POSITIONS = new Property<List<AuditPosition>>("auditPositions");

    public void setAction(String action) {
        writeProperty("action", action);
    }
    public String getAction() {
        return (String)readProperty("action");
    }

    public void setCreatedAt(Date createdAt) {
        writeProperty("createdAt", createdAt);
    }
    public Date getCreatedAt() {
        return (Date)readProperty("createdAt");
    }

    public void setCreatedBy(String createdBy) {
        writeProperty("createdBy", createdBy);
    }
    public String getCreatedBy() {
        return (String)readProperty("createdBy");
    }

    public void setId(String id) {
        writeProperty("id", id);
    }
    public String getId() {
        return (String)readProperty("id");
    }

    public void setRecordId(String recordId) {
        writeProperty("recordId", recordId);
    }
    public String getRecordId() {
        return (String)readProperty("recordId");
    }

    public void setTableName(String tableName) {
        writeProperty("tableName", tableName);
    }
    public String getTableName() {
        return (String)readProperty("tableName");
    }

    public void addToAuditPositions(AuditPosition obj) {
        addToManyTarget("auditPositions", obj, true);
    }
    public void removeFromAuditPositions(AuditPosition obj) {
        removeToManyTarget("auditPositions", obj, true);
    }
    @SuppressWarnings("unchecked")
    public List<AuditPosition> getAuditPositions() {
        return (List<AuditPosition>)readProperty("auditPositions");
    }


}
