package erp;

import java.util.UUID;



@SuppressWarnings("serial")
public class AuditPos extends _AuditPos {

  public AuditPos() {
    this.setGuid(UUID.randomUUID().toString());
  }
}
