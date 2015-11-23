package adapter;
import java.awt.*;
import java.awt.event.*;

public class Dot extends Canvas {
  private Point p;

  public Dot() {
    setSize(200, 200);
    p = new Point(100, 100);
    addMouseListener(new MyMouseAdapter(this));
  }

  public void changePoint(MouseEvent me) {
    p = new Point(me.getX(), me.getY());
    repaint();
  }

  public void paint(Graphics g) {
    Dimension d = getSize();
    g.drawRect(0, 0, d.width - 1, d.height - 1);
    g.fillRect(p.x - 2, p.y - 2, 4, 4);
  }
}

class MyMouseAdapter extends MouseAdapter {
  private Dot dot;
  public MyMouseAdapter(Dot dot) {
    this.dot = dot;
  }
  public void mouseClicked(MouseEvent me) {
    dot.changePoint(me);
  }
}
    
