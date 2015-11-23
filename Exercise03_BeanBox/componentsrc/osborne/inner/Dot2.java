package inner;
import java.awt.*;
import java.awt.event.*;

public class Dot2 extends Canvas {
  private Point p;

  public Dot2() {
    setSize(200, 200);
    p = new Point(100, 100);
    addMouseListener(new MyMouseAdapter());
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

  class MyMouseAdapter extends MouseAdapter {
    public void mouseClicked(MouseEvent me) {
      changePoint(me);
    }
  }
}
    
