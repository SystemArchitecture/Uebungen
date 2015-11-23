package network;
import java.rmi.*;
import java.rmi.server.*;

public class NetworkListenerImpl 
extends UnicastRemoteObject implements NetworkListener {
  private NetworkCanvas nc;

  public NetworkListenerImpl(NetworkCanvas nc) 
  throws RemoteException {
    this.nc = nc;
  }

  public void linkChange(LinkEvent le) 
  throws RemoteException {
    nc.linkChange(le);
  }
}