package mouseevents;
import java.awt.*;

public class MouseSource extends Canvas {

  public MouseSource() {
    setSize(100, 100);
  }

  public void paint(Graphics g) {
    Dimension d = getSize();
    g.drawRect(0, 0, d.width - 1, d.height - 1);
  }
}