package network;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import java.rmi.*;
import java.util.*;

public class NetworkCanvas extends Canvas {
  private transient Image image;
  private transient int height, width;
  private transient Vector cities;
  private transient Vector links;
  private transient NetworkServer networkServer;
  private transient NetworkListenerImpl nli;
  private String address = "";

  public NetworkCanvas() {
    init();
  }

  private void init() {
    try {

      // Initialize the cities and links vectors
      cities = new Vector();
      links = new Vector();

      // Get map image from 'map.gif'
      MediaTracker mt = new MediaTracker(this);
      URL url = getClass().getResource("map.gif");
      image = createImage((ImageProducer)url.getContent());
      mt.addImage(image, 0);
      mt.waitForAll();
      height = image.getHeight(this);
      width = image.getWidth(this);

      // Read and process entries from 'cities.txt'
      Class cls = getClass();
      String r = "cities.txt";
      InputStream is = cls.getResourceAsStream(r);
      InputStreamReader isr = new InputStreamReader(is);
      BufferedReader br = new BufferedReader(isr);
      String line;
      while((line = br.readLine()) != null) {
        StringTokenizer st = new StringTokenizer(line, ",");
        String name = st.nextToken();
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        City city = new City(name, x, y);
        cities.addElement(city);
      }
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public Dimension getPreferredSize() {
    return new Dimension(width, height);
  }

  private void adjustSize() {
    Dimension d = getPreferredSize();
    setSize(d.width, d.height);
    Component parent = getParent();
    if(parent != null) {
      parent.invalidate();
      parent.doLayout();
    }
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public void linkChange(LinkEvent le) {
    int linkId = le.getLinkId();
    Link link = (Link)links.elementAt(linkId);
    link.setOperational(le.getOperational());
    repaint();
  }

  public void paint(Graphics g) {
    g.drawImage(image, 0, 0, this);
    Enumeration e = links.elements();
    while(e.hasMoreElements()) {
      drawLink(g, (Link)e.nextElement());
    }
  }

  private void drawLink(Graphics g, Link link) {

    // Determine link color
    if(link.getOperational()) {
      g.setColor(new Color(0, 128, 0));
    }
    else {
      g.setColor(new Color(128, 0, 0));
    } 

    // Determine location of first city
    int x1, y1;
    x1 = y1 = 0;
    Enumeration e = cities.elements();
    while(e.hasMoreElements()) {
      City city = (City)e.nextElement();
      if(city.getName().equals(link.getCity1())) {
        x1 = city.getX();
        y1 = city.getY();
        break;
      }
    }

    // Determine location of second city
    int x2, y2;
    x2 = y2 = 0;
    e = cities.elements();
    while(e.hasMoreElements()) {
      City city = (City)e.nextElement();
      if(city.getName().equals(link.getCity2())) {
        x2 = city.getX();
        y2 = city.getY();
        break;
      }
    }
    g.drawLine(x1, y1, x2, y2);
  }

  public void apply(String address) {

    // Save server address
    this.address = address;

    // Remove existing NetworkListenerImpl 
    if(networkServer != null) {
      try {
        networkServer.removeNetworkListener(nli);
      }
      catch(Exception ex) {
        ex.printStackTrace();
      }
      networkServer = null;
    }

    // Lookup existing NetworkServer,
    // Create a NetworkListenerImpl object,
    // and update display
    String networkServerURL = 
      "rmi://" + address + "/NetworkServer";
    try {
      networkServer = 
        (NetworkServer)Naming.lookup(networkServerURL);
      nli = new NetworkListenerImpl(this);
      networkServer.addNetworkListener(nli);
      links = networkServer.getLinks();
      repaint();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  private void readObject(ObjectInputStream ois) 
  throws IOException, ClassNotFoundException {
    try {
      ois.defaultReadObject();
      init();
      adjustSize();
      repaint();
      apply(address);
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
}