package cselector;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.*;

public class Selector extends Panel 
implements AdjustmentListener {
  private Color color;
  private Vector listeners;
  private Scrollbar rScrollbar, gScrollbar, bScrollbar;

  public Selector() {

    // Initialize listeners vector
    listeners = new Vector();

    // Initialize GUI elements
    setLayout(new GridLayout(3, 2, 5, 5));
    rScrollbar = 
      new Scrollbar(Scrollbar.HORIZONTAL, 255, 10, 0, 265);
    add(rScrollbar);
    rScrollbar.addAdjustmentListener(this);
    Label rLabel = new Label("Red", Label.LEFT);
    add(rLabel);
    gScrollbar = 
      new Scrollbar(Scrollbar.HORIZONTAL, 255, 10, 0, 265);
    add(gScrollbar);
    gScrollbar.addAdjustmentListener(this);
    Label gLabel = new Label("Green", Label.LEFT);
    add(gLabel);
    bScrollbar = 
      new Scrollbar(Scrollbar.HORIZONTAL, 255, 10, 0, 265);
    add(bScrollbar);
    bScrollbar.addAdjustmentListener(this);
    Label bLabel = new Label("Blue", Label.LEFT);
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
    color = new Color(r, g, b);
    fireColorEvent(new ColorEvent(this, color));
  }

  public void
  addColorListener(ColorListener cl) {
    listeners.addElement(cl);
  }

  public void
  removeColorListener(ColorListener cl) {
    listeners.removeElement(cl);
  }

  public void fireColorEvent(ColorEvent ce) {
    Vector v;
    synchronized(this) {
      v = (Vector)listeners.clone();
    }
    for(int i = 0; i < v.size(); i++) {
      ColorListener cl = (ColorListener)v.elementAt(i);
      cl.changeColor(ce);
    }
  }
}
