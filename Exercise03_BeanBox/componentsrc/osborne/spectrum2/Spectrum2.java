package spectrum2;
import java.awt.*;

public class Spectrum2 extends Canvas {
  private boolean vertical;

  public Spectrum2() {
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
    Color c;
    float saturation = 1.0f;
    float brightness = 1.0f;
    Dimension d = getSize();
    if(vertical) {
      for(int y = 0; y < d.height; y++) {
        float hue = (float)y/(d.height - 1);
        c = Color.getHSBColor(hue, saturation, brightness);
        g.setColor(c);
        g.drawLine(0, y, d.width - 1, y);
      }
    }
    else {
      for(int x = 0; x < d.width; x++) {
        float hue = (float)x/(d.width - 1);
        c = Color.getHSBColor(hue, saturation, brightness);
        g.setColor(c);
        g.drawLine(x, 0, x, d.height - 1);
      }
    }
  }
}