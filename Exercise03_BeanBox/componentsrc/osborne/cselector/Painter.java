package cselector;
import java.awt.*;

public class Painter extends Canvas implements ColorListener {
  private Color color;

  public Painter() {
    color = Color.white;
    setSize(50, 50);
  }

  public void paint(Graphics g) {
    Dimension d = getSize();
    int w = d.width;
    int h = d.height;
    g.setColor(color);
    g.fillRect(0, 0, w - 1, h - 1);
    g.setColor(Color.black);
    g.drawRect(0, 0, w - 1, h - 1);
  }

  public void changeColor(ColorEvent ce) {
    this.color = ce.getColor();
    repaint();
  }
}
