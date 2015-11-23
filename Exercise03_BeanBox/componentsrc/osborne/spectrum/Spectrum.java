package spectrum;
import java.awt.*;
public class Spectrum extends Canvas {
  private boolean vertical;
  public Spectrum() {
    vertical = true;
    setSize(100, 100);
  }
  public boolean getVertical() {
    return vertical;
  }
  public void setVertical(boolean vertical) {
    this.vertical = vertical;
    repaint();
  }
  public void paint(Graphics g) {
    float saturation = 1.0f;
    float brightness = 1.0f;
    Dimension d = getSize();
    if(vertical) {
      for(int y = 0; y < d.height; y++) {
        float hue = (float)y/(d.height - 1);
        g.setColor(Color.getHSBColor(hue, saturation, brightness));
        g.drawLine(0, y, d.width - 1, y);
      }
    }
    else {
      for(int x = 0; x < d.width; x++) {
        float hue = (float)x/(d.width - 1);
        g.setColor(Color.getHSBColor(hue, saturation, brightness));
        g.drawLine(x, 0, x, d.height - 1);
      }
    }
  }
}