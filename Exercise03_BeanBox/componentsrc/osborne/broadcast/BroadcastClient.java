package broadcast;
import java.rmi.*;

public class BroadcastClient {
  private static BroadcastListenerImpl bli;

  public static void main(String args[]) {
    try {

      // Make rmi URL to BroadcastServer
      String broadcastServerURL;
      broadcastServerURL = 
        "rmi://" + args[0] + "/BroadcastServer";

      // Obtain a reference to that remote object
      BroadcastServer broadcastServer =
        (BroadcastServer)Naming.lookup(broadcastServerURL);
      bli = new BroadcastListenerImpl();
      broadcastServer.addBroadcastListener(bli);
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
}
