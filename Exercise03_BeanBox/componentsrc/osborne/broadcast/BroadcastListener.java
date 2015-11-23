package broadcast;
import java.rmi.*;

public interface BroadcastListener extends Remote {

  public void receiveBroadcast(String message) 
  throws RemoteException;
}
