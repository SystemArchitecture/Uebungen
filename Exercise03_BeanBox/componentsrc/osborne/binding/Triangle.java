package binding;
import java.awt.*;
import java.beans.*;

public class Triangle extends Canvas 
implements PropertyChangeListener {
  private Color color;

  public Triangle() {
    color = Color.blue;
    setSize(50, 50);
  } 

  public Color getColor() {
    return color;
  }

  public void setColor(Color newColor) {
    this.color = newColor;
    repaint();
  }

  public void propertyChange(PropertyChangeEvent pce) {
    setColor((Color)pce.getNewValue());
  }

  public void paint(Graphics g) {
    Dimension d = getSize();
    g.setColor(color);
    int xpoints[] = new int[3];
    int ypoints[] = new int[3];
    xpoints[0] = 0;
    ypoints[0] = d.height - 1;
    xpoints[1] = d.width/2;
    ypoints[1] = 0;
    xpoints[2] = d.width - 1;
    ypoints[2] = d.height - 1;
    g.fillPolygon(xpoints, ypoints, 3);
  }
}