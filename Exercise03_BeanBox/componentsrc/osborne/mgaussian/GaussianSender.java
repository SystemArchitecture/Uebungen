package mgaussian;
import java.io.*;
import java.net.*; 
import java.util.*;

public class GaussianSender 
implements Runnable, Serializable { 
  private String address;
  private int port, msec;
  private transient Thread t;

  public GaussianSender() {
    address = "";
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public int getPort() {
    return port;
  }

  public void setPort(int port) {
    this.port = port;
  }

  public int getMsec() {
    return msec;
  }

  public void setMsec(int msec) {
    this.msec = msec;
  }

  public void apply(String address, int port, int msec) {

    // Set the address, port, and msec properties
    setAddress(address);
    setPort(port);
    setMsec(msec);

    // Start a new thread
    t = new Thread(this);
    t.start();
  }

  public void run() {
    try {

      // Create a pseudorandom number generator 
      Random r = new Random();

      // Create a MulticastSocket object for the port
      MulticastSocket ms = new MulticastSocket(port); 

      // Create an InetAddress object for the address
      InetAddress ia = InetAddress.getByName(address); 

      while(true) { 
  
        // Sleep for msec milliseconds
        Thread.sleep(msec); 

        // Generate the next random number
        double d = r.nextGaussian();

        // Convert the number to an array of bytes
        String s = "" + d; 
        byte buffer[] = s.getBytes(); 

        // Create a DatagramPacket object and send it on
        // the multicast socket
        DatagramPacket dp;
        dp = new DatagramPacket(buffer, buffer.length, ia, port); 
        ms.send(dp); 
      }
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  } 

  private void readObject(ObjectInputStream ois) 
  throws ClassNotFoundException, IOException {
    ois.defaultReadObject();
    // Start a new thread
    t = new Thread(this);
    t.start();
  }
}