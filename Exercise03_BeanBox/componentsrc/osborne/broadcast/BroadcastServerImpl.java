package broadcast;
import java.io.*;
import java.rmi.*;
import java.rmi.server.*;
import java.util.*;

public class BroadcastServerImpl 
extends UnicastRemoteObject 
implements BroadcastServer, Runnable {
  private Vector listeners;
  private Thread thread;

  public BroadcastServerImpl() throws RemoteException {
    listeners = new Vector();
    thread = new Thread(this);
    thread.start();
  }

  public void addBroadcastListener(BroadcastListener bl) 
  throws RemoteException {
    listeners.addElement(bl);
  }

  public void removeBroadcastListener(BroadcastListener bl) 
  throws RemoteException {
    listeners.removeElement(bl);
  }

  public void run() {
    try {
      InputStreamReader isr;
      isr = new InputStreamReader(System.in);
      BufferedReader br;
      br = new BufferedReader(isr);
      while(true) {
        String p = "Enter the message to be broadcast:";
        System.out.println(p);
        String s = br.readLine();
        if(s == null) {
          return;
        }
        sendMessage(s);
      }
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  protected void sendMessage(String message) {

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
      BroadcastListener bl;
      bl = (BroadcastListener)e1.nextElement();
      try {
        bl.receiveBroadcast(message);
      }
      catch(Exception ex) {
        v2.addElement(bl);
      }
    }

    // Remove listeners that caused exceptions
    Enumeration e2 = v2.elements();
    while(e2.hasMoreElements()) {
      listeners.removeElement(e2.nextElement());
    }
  }
}
