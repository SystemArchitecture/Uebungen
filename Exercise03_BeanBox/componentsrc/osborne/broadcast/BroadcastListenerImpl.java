package broadcast;
import java.rmi.*;
import java.rmi.server.*;

public class BroadcastListenerImpl 
extends UnicastRemoteObject 
implements BroadcastListener {

  public BroadcastListenerImpl() 
  throws RemoteException {
  }

  public void receiveBroadcast(String message) 
  throws RemoteException {
    System.out.println("Message = " + message);
  }
}
