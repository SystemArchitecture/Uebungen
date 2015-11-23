package graphe;
import java.awt.*;
import java.io.*;

public class Node {
  private final static int NODERADIUS = 10;
  private static int count = 0;
  private int x, y, id;

  public Node(int x, int y) {
    this.x = x;
    this.y = y;
    id = count++;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getId() {
    return id;
  }

  public boolean contains(int x, int y) {
    int deltax = this.x - x;
    int deltay = this.y - y;
    int a = deltax * deltax + deltay * deltay;
    int b = NODERADIUS * NODERADIUS;
    return (a <= b);
  }

  public void draw(Graphics g) {
    int w = 2 * NODERADIUS;
    int h = w;
    g.fillOval(x - NODERADIUS, y - NODERADIUS, w, h);
  }
}
