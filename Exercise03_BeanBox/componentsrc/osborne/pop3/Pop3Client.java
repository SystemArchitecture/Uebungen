package pop3;
import java.io.*;
import java.net.*;

public class Pop3Client implements Pop3Listener, Serializable {
  public final static int FAILEDCONNECT = 0;
  public final static int FAILEDLOGIN = 1;
  public final static int CONNECTOK = 2;
  public final static int RETRIEVEOK = 3;
  public final static int BADMSGINDEX = 4;
  public final static int MSGDELETED = 5;
  public final static int FAILEDRETRIEVE = 6;
  public final static int DELETEOK = 7;
  public final static int FAILEDDELETE = 8;
  public final static int QUITOK = 9;
  public final static int FAILEDQUIT = 10;
  public final static int TIMEOUT = 11;
  private String hostname;
  private int port;
  private transient PrintWriter pw;
  private transient BufferedReader br;

  public Pop3Client() {
    hostname = "";
    port = 110;
  }

  public String getHostname() {
    return hostname;
  }

  public int getPort() {
    return port;
  }

  public void setHostname(String hostname) {
    this.hostname = hostname;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public void connect(Pop3Event pop3e) {
    Pop3Source pop3s = (Pop3Source)pop3e.getSource();
    try {

      // Create BufferedReader and PrintWriter 
      Socket socket = new Socket(hostname, port);
      InputStream is = socket.getInputStream();
      InputStreamReader isr = new InputStreamReader(is);
      br =  new BufferedReader(isr);
      OutputStream os = socket.getOutputStream();
      BufferedOutputStream bos = new BufferedOutputStream(os);
      pw = new PrintWriter(bos);

      // Check return code
      if(!readResponse().startsWith("+OK")) {
        pop3s.respond(FAILEDCONNECT);
        return;
      }

      // Supply user and password information
      writeCommand("USER " + pop3e.getUser());
      if(!readResponse().startsWith("+OK")) {
        pop3s.respond(FAILEDLOGIN);
        return;
      }
      writeCommand("PASS " + pop3e.getPass());
      if(!readResponse().startsWith("+OK")) {
        pop3s.respond(FAILEDLOGIN);
        return;
      }
      pop3s.respond(CONNECTOK);

    }
    catch(Exception ex) {
      pop3s.respond(FAILEDCONNECT);
    }
  }

  public void retr(Pop3Event pop3e) {
    Pop3Source pop3s = (Pop3Source)pop3e.getSource();
    try {
      writeCommand("RETR " + pop3e.getMsgId());
      String line = br.readLine();
      if(line.startsWith("+OK")) {
        pop3s.respond(RETRIEVEOK);
        pop3s.message(readMessage());
      }
      else if(line.indexOf("does not exist") != -1) {
        pop3s.respond(BADMSGINDEX);
      }
      else if(line.indexOf("has been deleted") != -1) {
        pop3s.respond(MSGDELETED);
      }
      else if(line.indexOf("timeout") != -1) {
        pop3s.respond(TIMEOUT);
      }
      else {
        pop3s.respond(FAILEDRETRIEVE);
      }
    }
    catch(Exception ex) {
      pop3s.respond(FAILEDRETRIEVE);
    }
  }

  public void dele(Pop3Event pop3e) {
    Pop3Source pop3s = (Pop3Source)pop3e.getSource();
    try {
      writeCommand("DELE " + pop3e.getMsgId());
      String response = readResponse();
      if(response.startsWith("+OK")) {
        pop3s.respond(DELETEOK);
        return;
      }
      else if(response.indexOf("timeout") != -1) {
        pop3s.respond(TIMEOUT);
        return;
      }
    }
    catch(Exception ex) {
    }
    pop3s.respond(FAILEDDELETE);
  }

  public void disconnect(Pop3Event pop3e) {
    Pop3Source pop3s = (Pop3Source)pop3e.getSource();
    try {
      writeCommand("QUIT");
      String response = readResponse();
      if(response.startsWith("+OK")) {
        pop3s.respond(QUITOK);
        return;
      }
      else if(response.indexOf("timeout") != -1) {
        pop3s.respond(TIMEOUT);
        return;
      }
    }
    catch(Exception ex) {
    }
    pop3s.respond(FAILEDQUIT);
  }

  private void writeCommand(String command) {
    pw.print(command);
    pw.print("\r\n");
    pw.flush();
  }

  private String readResponse() {
    String line = "";
    try {
      line = br.readLine();
    }
    catch(Exception ex) {
    }
    return line;
  }

  private String readMessage() {
    String message = "";
    while(true) {
      try {
        String line = br.readLine();
        message += line + "\n";
        if(line.startsWith(".")) {
          break;
        }
      }
      catch(Exception ex) {
        break;
      }
    }
    return message;
  }
}


  