package ee;
import java.awt.*;

public class Meter extends Canvas 
implements WaveformListener {
  private final static int NDEGREES = 180;
  public double min;
  public double max;
  public transient double value;

  public Meter() {
    min = -1;
    max = 1;
    value = 0;
    setSize(200, 100);
  }

  public double getMin() {
    return min;
  }

  public void setMin(double min) {
    this.min = min;
    repaint();
  }

  public double getMax() {
    return max;
  }

  public void setMax(double max) {
    this.max = max;
    repaint();
  }

  public void waveformValueChanged(WaveformEvent we) {
    value = we.getValue();
    repaint();
  }

  public void paint(Graphics g) {

    // Draw rectangle around the display area
    Dimension d = getSize();
    int h = d.height;
    int w = d.width;
    g.setColor(Color.gray);
    g.drawRect(0, 0, w - 1, h - 1);

    // Draw arc representing the dial
    int centerx = (int)(w * 0.5);
    int centery = (int)(h * 0.9);
    int radius = (int)(w * 0.4);
    int xa = (int)(w * 0.1);
    int ya = (int)(h * 0.1);
    int wa = 2 * radius;
    int ha = 2 * radius;
    g.drawArc(xa, ya, wa, ha, 0, 180);

    // Draw line representing the base of the meter
    g.drawLine(centerx - radius, centery, 
      centerx + radius, centery);

    // Compute position of needle
    double angle;
    if(value > max) {
      angle = 180;
    }
    else if(value < min) {
      angle = 0;
    }
    else {
      angle = (int)(180 * (value - min)/(max - min));
    }

    // Draw line representing the needle
    double radians = angle * Math.PI/180;
    int x = (int)(centerx - radius * 0.9 * Math.cos(radians));
    int y = (int)(centery - radius * 0.9 * Math.sin(radians));
    g.setColor(Color.blue);
    g.drawLine(centerx, centery, x, y);
  }
}