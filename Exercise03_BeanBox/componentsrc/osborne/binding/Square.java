package binding;
import java.awt.*;
import java.beans.*;

public class Square extends Canvas 
implements PropertyChangeListener {
  private Color color;

  public Square() {
    color = Color.red;
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
    g.fillRect(0, 0, d.width - 1, d.height - 1);
  }
}