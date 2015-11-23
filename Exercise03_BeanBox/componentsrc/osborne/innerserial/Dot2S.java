package innerserial;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class Dot2S extends Canvas {
  private Point p;

  public Dot2S() {
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

  class MyMouseAdapter extends MouseAdapter 
  implements Serializable {
    public void mouseClicked(MouseEvent me) {
      changePoint(me);
    }
  }
}
