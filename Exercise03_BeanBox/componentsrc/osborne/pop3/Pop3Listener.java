package pop3;
import java.util.*;

public interface Pop3Listener extends EventListener {

  public void connect(Pop3Event pop3e);

  public void retr(Pop3Event pop3e);

  public void dele(Pop3Event pop3e);

  public void disconnect(Pop3Event pop3e);
}
