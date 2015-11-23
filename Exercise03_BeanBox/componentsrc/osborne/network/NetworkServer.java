package network;
import java.rmi.*;
import java.util.*;

public interface NetworkServer extends Remote {

  public void addNetworkListener(NetworkListener nl) 
  throws RemoteException;

  public void removeNetworkListener(NetworkListener nl) 
  throws RemoteException;

  public Vector getLinks() 
  throws RemoteException;
}