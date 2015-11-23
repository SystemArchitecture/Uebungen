package color;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;

public class ColorEditor extends Panel 
implements AdjustmentListener, PropertyEditor {
  private PropertyChangeSupport support;
  private Color color;
  private Scrollbar sr, sg, sb;

  public ColorEditor() {

   // Create and arrange GUI elements
    setLayout(new GridLayout(3, 2, 5, 5));
    Label lr = new Label("Red");
    lr.setAlignment(Label.RIGHT);
    add(lr);
    sr = new Scrollbar(Scrollbar.HORIZONTAL, 0, 10, 0, 255);
    add(sr);
    sr.addAdjustmentListener(this);
    Label lg = new Label("Green");
    lg.setAlignment(Label.RIGHT);
    add(lg);
    sg = new Scrollbar(Scrollbar.HORIZONTAL, 0, 10, 0, 255);
    add(sg);
    sg.addAdjustmentListener(this);
    Label lb = new Label("Blue");
    lb.setAlignment(Label.RIGHT);
    add(lb);
    sb = new Scrollbar(Scrollbar.HORIZONTAL, 0, 10, 0, 255);
    add(sb);
    sb.addAdjustmentListener(this);

    // Create a PropertyChangeSupport object
    support = new PropertyChangeSupport(this);
  }

  public void adjustmentValueChanged(AdjustmentEvent ae) {
    int r = sr.getValue();
    sr.setValue(r);
    int g = sg.getValue();
    sg.setValue(g);
    int b = sb.getValue();
    sb.setValue(b);
    color = new Color(r, g, b);
    support.firePropertyChange("", null, null);
  }

  public void setValue(Object o) {
    color = (Color)o;
    sr.setValue(color.getRed());
    sg.setValue(color.getGreen());
    sb.setValue(color.getBlue());
    support.firePropertyChange("", null, null);
  }

  public Object getValue() {
    return color;
  }

  public String getJavaInitializationString() {
    return null;
  }

  public boolean isPaintable() {
    return true;
  }

  public void paintValue(Graphics g, Rectangle r) {
    g.setColor(color);
    g.fillRect(0, 0, r.width - 1, r.height - 1);
  }

  public String getAsText() {
    return null;
  }

  public void setAsText(String s) 
  throws IllegalArgumentException {
    throw new IllegalArgumentException(s);  
  }

  public String[] getTags() {
    return null;
  }

  public boolean supportsCustomEditor() {
    return true;
  }

  public Component getCustomEditor() {
    return this;
  }

  public void 
  addPropertyChangeListener(PropertyChangeListener pcl) {
    support.addPropertyChangeListener(pcl);
  }

  public void 
  removePropertyChangeListener(PropertyChangeListener pcl) {
    support.removePropertyChangeListener(pcl);
  }
}
