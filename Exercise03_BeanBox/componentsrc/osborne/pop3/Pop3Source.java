package pop3;

public interface Pop3Source {

  public void respond(int code);

  public void message(String msg);
}