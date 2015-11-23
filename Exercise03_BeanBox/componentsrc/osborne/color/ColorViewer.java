package color;
import java.awt.*;

public class ColorViewer extends Canvas {
  private Color color;

  public ColorViewer() {
    color = Color.blue;
    setSize(200, 200);
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
    repaint();
  }

  public void update(Graphics g) {
    paint(g);
  }

  public void paint(Graphics g) {
    g.setColor(color);
    Dimension d = getSize();
    g.fillRect(0, 0, d.width - 1, d.height - 1);
  }
}