package broadcast;
import java.rmi.*;

public interface BroadcastServer extends Remote {

  void addBroadcastListener(BroadcastListener bl) 
  throws RemoteException;

  void removeBroadcastListener(BroadcastListener bl) 
  throws RemoteException;
}
