package at.mxerp.db.erp;

import java.util.Date;

import org.apache.cayenne.CayenneDataObject;
import org.apache.cayenne.exp.Property;

/**
 * Class _Users was generated by Cayenne.
 * It is probably a good idea to avoid changing this class manually,
 * since it may be overwritten next time code is regenerated.
 * If you need to make any customizations, please use subclass.
 */
public abstract class _Users extends CayenneDataObject {

    @Deprecated
    public static final String ACTIVE_PROPERTY = "active";
    @Deprecated
    public static final String CHANGED_AT_PROPERTY = "changedAt";
    @Deprecated
    public static final String CHANGED_BY_PROPERTY = "changedBy";
    @Deprecated
    public static final String CREATED_AT_PROPERTY = "createdAt";
    @Deprecated
    public static final String CREATED_BY_PROPERTY = "createdBy";
    @Deprecated
    public static final String DELETED_PROPERTY = "deleted";
    @Deprecated
    public static final String LAST_LOGIN_PROPERTY = "lastLogin";
    @Deprecated
    public static final String LAST_LOGIN_IP_PROPERTY = "lastLoginIp";
    @Deprecated
    public static final String LOCKED_PROPERTY = "locked";
    @Deprecated
    public static final String LOGON_LANGUAGE_PROPERTY = "logonLanguage";
    @Deprecated
    public static final String PARTNER_PROPERTY = "partner";
    @Deprecated
    public static final String PASSWORD_PROPERTY = "password";
    @Deprecated
    public static final String USER_PROPERTY = "user";

    public static final String USER_PK_COLUMN = "user";

    public static final Property<Boolean> ACTIVE = new Property<Boolean>("active");
    public static final Property<Date> CHANGED_AT = new Property<Date>("changedAt");
    public static final Property<String> CHANGED_BY = new Property<String>("changedBy");
    public static final Property<Date> CREATED_AT = new Property<Date>("createdAt");
    public static final Property<String> CREATED_BY = new Property<String>("createdBy");
    public static final Property<Boolean> DELETED = new Property<Boolean>("deleted");
    public static final Property<Date> LAST_LOGIN = new Property<Date>("lastLogin");
    public static final Property<String> LAST_LOGIN_IP = new Property<String>("lastLoginIp");
    public static final Property<Boolean> LOCKED = new Property<Boolean>("locked");
    public static final Property<String> LOGON_LANGUAGE = new Property<String>("logonLanguage");
    public static final Property<String> PARTNER = new Property<String>("partner");
    public static final Property<String> PASSWORD = new Property<String>("password");
    public static final Property<String> USER = new Property<String>("user");

    public void setActive(Boolean active) {
        writeProperty("active", active);
    }
    public Boolean getActive() {
        return (Boolean)readProperty("active");
    }

    public void setChangedAt(Date changedAt) {
        writeProperty("changedAt", changedAt);
    }
    public Date getChangedAt() {
        return (Date)readProperty("changedAt");
    }

    public void setChangedBy(String changedBy) {
        writeProperty("changedBy", changedBy);
    }
    public String getChangedBy() {
        return (String)readProperty("changedBy");
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

    public void setDeleted(Boolean deleted) {
        writeProperty("deleted", deleted);
    }
    public Boolean getDeleted() {
        return (Boolean)readProperty("deleted");
    }

    public void setLastLogin(Date lastLogin) {
        writeProperty("lastLogin", lastLogin);
    }
    public Date getLastLogin() {
        return (Date)readProperty("lastLogin");
    }

    public void setLastLoginIp(String lastLoginIp) {
        writeProperty("lastLoginIp", lastLoginIp);
    }
    public String getLastLoginIp() {
        return (String)readProperty("lastLoginIp");
    }

    public void setLocked(Boolean locked) {
        writeProperty("locked", locked);
    }
    public Boolean getLocked() {
        return (Boolean)readProperty("locked");
    }

    public void setLogonLanguage(String logonLanguage) {
        writeProperty("logonLanguage", logonLanguage);
    }
    public String getLogonLanguage() {
        return (String)readProperty("logonLanguage");
    }

    public void setPartner(String partner) {
        writeProperty("partner", partner);
    }
    public String getPartner() {
        return (String)readProperty("partner");
    }

    public void setPassword(String password) {
        writeProperty("password", password);
    }
    public String getPassword() {
        return (String)readProperty("password");
    }

    public void setUser(String user) {
        writeProperty("user", user);
    }
    public String getUser() {
        return (String)readProperty("user");
    }

}
