package icselector;
import java.awt.*;

public class Painter extends Canvas {
  private Color color;

  public Painter() {
    color = Color.white;
    setSize(50, 50);
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
    repaint();
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
}
