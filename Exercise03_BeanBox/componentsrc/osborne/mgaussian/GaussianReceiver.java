package mgaussian;
import java.awt.*;
import java.io.*;
import java.net.*;

public class GaussianReceiver extends Canvas 
implements Runnable {
  private final static int MAXGAUSSIAN = 3;
  private final static int BUFSIZE = 10;
  private int width, height;
  private String address;
  private int port;
  private transient Thread t;
  private transient long data[];

  public GaussianReceiver() {
    width = 300;
    height = 100;
    address = "";
    port = 0;
    data = new long[width];
    for(int i = 0; i < width; i++) {
      data[i] = 0;
    }
    setSize(width, height);    
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

  public void apply(String address, int port) {

    // Set data elements to zero
    for(int i = 0; i < width; i++) {
      data[i] = 0;
    }

    // Set address and port
    setAddress(address);
    setPort(port);

    // Start thread
    t = new Thread(this);
    t.start();
  }

  public void run() {
    try { 

      // Join the multicast group defined by address and port
      MulticastSocket ms = new MulticastSocket(port); 
      InetAddress ia = InetAddress.getByName(address); 
      ms.joinGroup(ia);

      while(true) { 

        // Receive a datagram packet
        byte buffer[] = new byte[BUFSIZE];
        int n = buffer.length;
        DatagramPacket dp = new DatagramPacket(buffer, n); 
        ms.receive(dp); 

        // Get data from datagram packet
        String str = new String(dp.getData());
        double d = Double.valueOf(str).doubleValue();
        receive(d);

        // Update the frequency distribution graph
        repaint();
      }
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  } 

  public void receive(double d) {

    // Ignore values outside of the range
    if(d < -MAXGAUSSIAN || d > MAXGAUSSIAN)
      return;

    // Increment one of the elements in data[]
    double deltax = 
      2 * MAXGAUSSIAN/((double)(getSize().width));
    int x = -MAXGAUSSIAN;
    for(int i = 1; i < width; i++) {
      if(-MAXGAUSSIAN + deltax * i > d) {
        // Determine which entry to increment
        double xa = -MAXGAUSSIAN + deltax * (i - 1);
        double xb = -MAXGAUSSIAN + deltax * i;
        double a = d - xa;
        double b = xb - d;
        if(a < b) {
          ++data[i - 1];
        }
        else {
          ++data[i];
        }
        break;
      }
    }
  }

  public void update(Graphics g) {
    paint(g);
  }

  public void paint(Graphics g) {

    // Create background buffer
    Image buffer = createImage(width, height);
    Graphics bg = buffer.getGraphics();
    bg.setColor(getBackground());
    bg.fillRect(0, 0, width - 1, height - 1);

    // Draw border around the Bean
    bg.setColor(getForeground());
    bg.drawRect(0, 0, width - 1, height - 1);

    // Determine the maximum entry
    long max = 0;
    for(int i = 0; i < width; i++) {
      max = (data[i] > max) ? data[i] : max;
    }

    // Draw lines to represent the frequency distribution
    if(max != 0) {
      for(int i = 0; i < width; i++) {
        long hd = (int)(height * data[i]);
        int y = (int)(height - hd/max);
        bg.drawLine(i, height, i, y);
      }
    }

    // Copy background buffer to foreground
    g.drawImage(buffer, 0, 0, null);
  }

  private void readObject(ObjectInputStream ois) 
  throws ClassNotFoundException, IOException {
    ois.defaultReadObject();
    // Initialize data[]
    data = new long[width];
    for(int i = 0; i < width; i++) {
      data[i] = 0;
    }
    // Start thread
    t = new Thread(this);
    t.start();
  }
}