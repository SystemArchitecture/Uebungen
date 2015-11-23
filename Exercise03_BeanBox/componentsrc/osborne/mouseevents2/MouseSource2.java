package mouseevents2;
import java.awt.*;
public class MouseSource2 extends Canvas {
  public MouseSource2() {
    setSize(100, 100);
  }
  public void paint(Graphics g) {
    Dimension d = getSize();
    g.drawRect(0, 0, d.width - 1, d.height - 1);
  }
}