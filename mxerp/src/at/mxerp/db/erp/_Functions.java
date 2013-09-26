package at.mxerp.db.erp;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;

/**
 * Class _Functions was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Functions extends CayenneDataObject {

    @Deprecated
    public static final String ID_PROPERTY = "id";
    @Deprecated
    public static final String LOCKED_PROPERTY = "locked";

    public static final String ID_PK_COLUMN = "id";

    public static final Property<String> ID = new Property<String>("id");
    public static final Property<Boolean> LOCKED = new Property<Boolean>("locked");

    public void setId(String id) {
        writeProperty("id", id);
    }
    public String getId() {
        return (String)readProperty("id");
    }

    public void setLocked(Boolean locked) {
        writeProperty("locked", locked);
    }
    public Boolean getLocked() {
        return (Boolean)readProperty("locked");
    }

}
