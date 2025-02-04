package at.mxerp.db.erp;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;

/**
 * Class _Metadata was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Metadata extends CayenneDataObject {

    @Deprecated
    public static final String DOMAIN_PROPERTY = "domain";
    @Deprecated
    public static final String ENTITY_PATH_PROPERTY = "entityPath";
    @Deprecated
    public static final String FIELD_PROPERTY = "field";
    @Deprecated
    public static final String MANDATORY_PROPERTY = "mandatory";
    @Deprecated
    public static final String OBJECT_PROPERTY = "object";
    @Deprecated
    public static final String READ_ONLY_PROPERTY = "readOnly";
    @Deprecated
    public static final String SEARCH_INDEX_PROPERTY = "searchIndex";
    @Deprecated
    public static final String TECHNICAL_PROPERTY = "technical";
    @Deprecated
    public static final String VVB_PROPERTY = "vvb";

    public static final String FIELD_PK_COLUMN = "field";
    public static final String OBJECT_PK_COLUMN = "object";

    public static final Property<String> DOMAIN = new Property<String>("domain");
    public static final Property<String> ENTITY_PATH = new Property<String>("entityPath");
    public static final Property<String> FIELD = new Property<String>("field");
    public static final Property<Boolean> MANDATORY = new Property<Boolean>("mandatory");
    public static final Property<String> OBJECT = new Property<String>("object");
    public static final Property<Boolean> READ_ONLY = new Property<Boolean>("readOnly");
    public static final Property<Boolean> SEARCH_INDEX = new Property<Boolean>("searchIndex");
    public static final Property<Boolean> TECHNICAL = new Property<Boolean>("technical");
    public static final Property<String> VVB = new Property<String>("vvb");

    public void setDomain(String domain) {
        writeProperty("domain", domain);
    }
    public String getDomain() {
        return (String)readProperty("domain");
    }

    public void setEntityPath(String entityPath) {
        writeProperty("entityPath", entityPath);
    }
    public String getEntityPath() {
        return (String)readProperty("entityPath");
    }

    public void setField(String field) {
        writeProperty("field", field);
    }
    public String getField() {
        return (String)readProperty("field");
    }

    public void setMandatory(Boolean mandatory) {
        writeProperty("mandatory", mandatory);
    }
    public Boolean getMandatory() {
        return (Boolean)readProperty("mandatory");
    }

    public void setObject(String object) {
        writeProperty("object", object);
    }
    public String getObject() {
        return (String)readProperty("object");
    }

    public void setReadOnly(Boolean readOnly) {
        writeProperty("readOnly", readOnly);
    }
    public Boolean getReadOnly() {
        return (Boolean)readProperty("readOnly");
    }

    public void setSearchIndex(Boolean searchIndex) {
        writeProperty("searchIndex", searchIndex);
    }
    public Boolean getSearchIndex() {
        return (Boolean)readProperty("searchIndex");
    }

    public void setTechnical(Boolean technical) {
        writeProperty("technical", technical);
    }
    public Boolean getTechnical() {
        return (Boolean)readProperty("technical");
    }

    public void setVvb(String vvb) {
        writeProperty("vvb", vvb);
    }
    public String getVvb() {
        return (String)readProperty("vvb");
    }

}
