package binding;
import java.awt.*;
import java.beans.*;

public class Circle extends Canvas {
  private Color color;
  private PropertyChangeSupport pcs;

  public Circle() {
    color = Color.green;
    setSize(50, 50);
    pcs = new PropertyChangeSupport(this);
  } 

  public Color getColor() {
    return color;
  }

  public void setColor(Color newColor) {
    Color oldColor = color;
    this.color = newColor;
    pcs.firePropertyChange("color", oldColor, newColor);
    repaint();
  }

  public void paint(Graphics g) {
    Dimension d = getSize();
    g.setColor(color);
    g.fillOval(0, 0, d.width - 1, d.height - 1);
  }
  
  public void 
  addPropertyChangeListener(PropertyChangeListener pcl) {
    pcs.addPropertyChangeListener(pcl);
  }

  public void 
  removePropertyChangeListener(PropertyChangeListener pcl) {
    pcs.removePropertyChangeListener(pcl);
  }
}