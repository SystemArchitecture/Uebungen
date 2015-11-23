package icselector;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.*;

public class Selector extends Panel 
implements AdjustmentListener {
  private Color color;
  private PropertyChangeSupport pcs;
  private Scrollbar rScrollbar, gScrollbar, bScrollbar;

  public Selector() {

    // Set locale for testing purposes
    Locale.setDefault(Locale.ITALY);

    // Get the default locale
    Locale locale = Locale.getDefault();

    // Initialize variables
    color = Color.white;
    pcs = new PropertyChangeSupport(this);

    // Get the resource bundle
    ResourceBundle rb = 
      ResourceBundle.getBundle("icselector.Resources", 
        locale);

    // Get labels for the sliders
    String red = rb.getString("Red");
    String green = rb.getString("Green");
    String blue = rb.getString("Blue");

    // Initialize GUI elements
    setLayout(new GridLayout(3, 2, 5, 5));
    rScrollbar = 
      new Scrollbar(Scrollbar.HORIZONTAL, 255, 10, 0, 265);
    add(rScrollbar);
    rScrollbar.addAdjustmentListener(this);
    Label rLabel = new Label(red, Label.LEFT);
    add(rLabel);
    gScrollbar = 
      new Scrollbar(Scrollbar.HORIZONTAL, 255, 10, 0, 265);
    add(gScrollbar);
    gScrollbar.addAdjustmentListener(this);
    Label gLabel = new Label(green, Label.LEFT);
    add(gLabel);
    bScrollbar = 
      new Scrollbar(Scrollbar.HORIZONTAL, 255, 10, 0, 265);
    add(bScrollbar);
    bScrollbar.addAdjustmentListener(this);
    Label bLabel = new Label(blue, Label.LEFT);
    add(bLabel);
  }

  public Insets getInsets() {
    return new Insets(5, 5, 5, 5);
  }

  public Color getColor() {
    return color;
  }

  public void setColor(Color color) {
    this.color = color;
  }

  public void paint(Graphics g) {
    Dimension d = getSize();
    g.drawRect(0, 0, d.width - 1, d.height - 1);
  }

  public void adjustmentValueChanged(AdjustmentEvent ae) {
    Scrollbar source = (Scrollbar)ae.getSource();
    int value = ae.getValue();
    source.setValue(value);
    int r = rScrollbar.getValue();
    int g = gScrollbar.getValue();
    int b = bScrollbar.getValue();
    Color oldColor = color;
    color = new Color(r, g, b);
    pcs.firePropertyChange("color", oldColor, color);
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
