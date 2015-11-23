package broadcast;
import java.net.*;
import java.rmi.*;

public class BroadcastServerApp {

  public static void main(String args[]) {
    try {
      BroadcastServerImpl bsi;
      bsi = new BroadcastServerImpl();
      Naming.rebind("BroadcastServer", bsi);
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
}
