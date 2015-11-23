package smtp;
import java.util.*;

public class SmtpEvent extends EventObject {
  private String sender;
  private String recipient;
  private String message;

  public SmtpEvent(Object source, String sender, 
  String recipient, String message) {
    super(source);
    this.sender = sender;
    this.recipient = recipient;
    this.message = message;
  }

  public String getSender() {
    return sender;
  }

  public String getRecipient() {
    return recipient;
  }

  public String getMessage() {
    return message;
  }
}