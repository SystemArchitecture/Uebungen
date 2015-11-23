package pop3;
import java.util.*;

public class Pop3Event extends EventObject {
  private String user, pass;
  private int msgId;

  public Pop3Event(Object source, String user, String pass) {
    super(source);
    this.user = user;
    this.pass = pass;
  }

  public Pop3Event(Object source, int msgId) {
    super(source);
    this.msgId = msgId;
  }

  public Pop3Event(Object source) {
    super(source);
  }

  public String getUser() {
    return user;
  }

  public String getPass() {
    return pass;
  }

  public int getMsgId() {
    return msgId;
  }
}