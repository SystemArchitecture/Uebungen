package network;
import java.rmi.*;

public interface NetworkListener extends Remote {

  void linkChange(LinkEvent le) throws RemoteException;
}