package graphs;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Graph extends Canvas 
implements MouseListener, MouseMotionListener {
  private final static int NNODES = 6;
  private Vector nodes;
  private Vector links;
  private transient Node node1, node2;
  private transient int x, y;

  public Graph() {
    setSize(200, 200);
    nodes = new Vector();
    links = new Vector();
    addMouseListener(this);
    addMouseMotionListener(this);
    makeNodes();
  }

  public void mouseClicked(MouseEvent me) {
  }

  public void mouseEntered(MouseEvent me) {
  }

  public void mouseExited(MouseEvent me) {
  }

  public void mousePressed(MouseEvent me) {
    doMousePressed(me);
  }

  public void mouseReleased(MouseEvent me) {
    doMouseReleased(me);
  }

  public void mouseDragged(MouseEvent me) {
    doMouseDragged(me);
  }

  public void mouseMoved(MouseEvent me) {
  }

  public void doMousePressed(MouseEvent me) {
    // Check if node1 should be initialized
    x = me.getX();
    y = me.getY();
    Enumeration e = nodes.elements();
    while(e.hasMoreElements()) {
      node1 = (Node)e.nextElement();
      if(node1.contains(x, y)) {
        return;
      }
    }
    node1 = null;
  }

  public void doMouseDragged(MouseEvent me) {
    x = me.getX();
    y = me.getY();
    repaint();
  }

  public void doMouseReleased(MouseEvent me) {
    // Make a link between node1 and node2
    x = me.getX();
    y = me.getY();
    if(node1 != null) {
      Enumeration e = nodes.elements();
      while(e.hasMoreElements()) {
        node2 = (Node)e.nextElement();
        if(node2.contains(x, y)) {
          makeLink(node1.getId(), node2.getId());
          break;
        }
      }
      node1 = node2 = null;
      repaint();
    }
  }

  public void paint(Graphics g) {
    // Draw nodes
    Enumeration e = nodes.elements();
    while(e.hasMoreElements()) {
      ((Node)e.nextElement()).draw(g);
    } 
    // Draw links
    e = links.elements();
    while(e.hasMoreElements()) {
      ((Link)e.nextElement()).draw(g);
    } 
    // Draw rubber band line (if any)
    if(node1 != null) {
      g.drawLine(node1.getX(), node1.getY(), x, y);
    }
  }

  private void makeNodes() {
    // Initialize nodes variable
    Dimension d = getSize();
    int width = d.width;
    int height = d.height;
    int centerx = width/2;
    int centery = height/2;
    double radius = 
      (width < height) ? 0.4 * width : 0.4 * height;
    for(int i = 0; i < NNODES; i++) {
      double theta = i * 2 * Math.PI/NNODES;
      int x = (int)(centerx + radius * Math.cos(theta));
      int y = (int)(centery - radius * Math.sin(theta));
      nodes.addElement(new Node(x, y));
    }
  }

  private void makeLink(int id1, int id2) {
    // Return if a link already exists between these nodes
    if(linkExists(id1, id2)) {
      return;
    }
    // Otherwise, create a new link
    Node n1 = (Node)nodes.elementAt(id1);
    Node n2 = (Node)nodes.elementAt(id2);
    links.addElement(new Link(n1, n2));
  }

  private boolean linkExists(int i, int j) {
    // Check if a link exists between nodes i and j
    Enumeration e = links.elements();
    while(e.hasMoreElements()) {
      Link link = (Link)e.nextElement();
      int id1 = link.getNode1().getId();
      int id2 = link.getNode2().getId();
      if((id1 == i && id2 == j) || (id1 == j && id2 == i)) {
        return true;
      }
    }
    return false;
  }
}
