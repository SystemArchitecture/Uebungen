package network;
import java.io.*;
import java.util.*;

public class Link implements Serializable {
  private static Vector links = new Vector();
  private String city1, city2;
  private boolean operational;

  public static Vector getLinks() {
    return links;
  }

  public Link(String city1, String city2, 
  boolean operational) {
    this.city1 = city1;
    this.city2 = city2;
    this.operational = operational;
    links.addElement(this);
  }

  public String getCity1() {
    return city1;
  }

  public String getCity2() {
    return city2;
  }

  public boolean getOperational() {
    return operational;
  }

  public void setOperational(boolean operational) {
    this.operational = operational;
  }
}