package smtp;
import java.util.*;

public interface SmtpListener extends EventListener {

  public void send(SmtpEvent smtpe);
}
