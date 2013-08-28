package erp;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang3.time.DateUtils;



@SuppressWarnings("serial")
public class AuditHead extends _AuditHead {

  public AuditHead() {
    this.setGuid(UUID.randomUUID().toString());
  }
  
  /* (non-Javadoc)
   * @see db.mii.eggermes._PpAfkoPordershead#setChanged(java.util.Date)
   * round for optimistic locking, problem when converting to oracle timestamp
   */
  @Override
  public void setTimestamp(Date changed) {
    changed = DateUtils.setMilliseconds(changed, 0);
    super.setTimestamp(changed);
  }
}
