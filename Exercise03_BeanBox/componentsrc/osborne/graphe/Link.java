package graphe;
import java.awt.*;
import java.io.*;

public class Link {
  private Node node1, node2;

  public Link(Node node1, Node node2) {
    this.node1 = node1;
    this.node2 = node2;
  }

  public Node getNode1() {
    return node1;
  }

  public Node getNode2() {
    return node2;
  }

  public void draw(Graphics g) {
    int x1 = node1.getX();
    int y1 = node1.getY();
    int x2 = node2.getX();
    int y2 = node2.getY();
    g.drawLine(x1, y1, x2, y2); 
  }
}
