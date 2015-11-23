package network;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class NetworkServerImpl 
extends UnicastRemoteObject implements NetworkServer {
  private Vector listeners;

  public NetworkServerImpl() 
  throws RemoteException {
    listeners = new Vector();
  }

  public void addNetworkListener(NetworkListener nl) 
  throws RemoteException {
    listeners.addElement(nl);
  }

  public void removeNetworkListener(NetworkListener nl) 
  throws RemoteException {
    listeners.removeElement(nl);
  }

  public Vector getLinks() 
  throws RemoteException {
    return Link.getLinks();
  }

  public void generateLinkEvent(int id, boolean operational) {
    Vector links = Link.getLinks();
    Link link = (Link)links.elementAt(id);
    link.setOperational(operational);
    LinkEvent le = new LinkEvent(this, id, operational);
    fireLinkEvent(le);
  }

  private void fireLinkEvent(LinkEvent le) {

    // v1 = a clone of the listeners vector
    // v2 = a new vector
    Vector v1, v2;
    synchronized(this) {
      v1 = (Vector)listeners.clone();
    }
    v2 = new Vector();

    // Broadcast the message to these listeners
    Enumeration e1 = v1.elements();
    while(e1.hasMoreElements()) {
      NetworkListener nl = (NetworkListener)e1.nextElement();
      try {
        nl.linkChange(le);
      }
      catch(Exception ex) {
        v2.addElement(nl);
      }
    }

    // Remove listeners that caused exceptions
    Enumeration e2 = v2.elements();
    while(e2.hasMoreElements()) {
      listeners.removeElement(e2.nextElement());
    }
  }
}