package toggleb;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;

public class ToggleButton extends Canvas 
implements MouseListener {
  private final static int XPAD = 5;
  private final static int YPAD = 5;
  private String label1, label2;
  private boolean state;
  private transient boolean pressed;
  private transient boolean inside;
  private PropertyChangeSupport pcs;

  public ToggleButton() {
    label1 = "Stop";
    label2 = "Run";
    state = true;
    pressed = false;
    inside = false;
    pcs = new PropertyChangeSupport(this);
    setFont(new Font("SansSerif", Font.BOLD, 16));
    addMouseListener(this);
  }

  public String getLabel1() {
    return label1;
  }

  public String getLabel2() {
    return label2;
  }

  public boolean getState() {
    return state;
  }

  public void setFont(Font font) {
    super.setFont(font);
    adjustSize();
  }

  public void setLabel1(String label1) {
    this.label1 = label1;
    adjustSize();
  }

  public void setLabel2(String label2) {
    this.label2 = label2;
    adjustSize();
  }

  public Dimension getPreferredSize() {
    FontMetrics fm = getFontMetrics(getFont());
    int w1 = fm.stringWidth(label1);
    int w2 = fm.stringWidth(label2);
    int w = (w1 > w2) ? w1 : w2;
    int width = w + 2 * XPAD;
    int height = fm.getHeight() + 2 * YPAD;
    return new Dimension(width, height);
  }

  public void adjustSize() {
    Dimension d = getPreferredSize();
    setSize(d.width, d.height);
    Component parent = getParent();
    if(parent != null) {
      parent.invalidate();
      parent.doLayout();
    }
  }

  public void paint(Graphics g) {
    Dimension d = getSize();
    g.setColor(getBackground());
    g.fill3DRect(0, 0, d.width - 1, d.height - 1, !pressed);
    g.fillRect(1, 1, d.width - 3, d.height - 3);
    g.setColor(getForeground());
    FontMetrics fm = g.getFontMetrics();
    String s = state ? label1 : label2;
    int x = (d.width - fm.stringWidth(s))/2;
    int y = (d.height + fm.getMaxAscent() 
      - fm.getMaxDescent())/2;
    g.drawString(s, x, y);
  }

  public void 
  addPropertyChangeListener(PropertyChangeListener pcl) {
    pcs.addPropertyChangeListener(pcl);
  }

  public void 
  removePropertyChangeListener(PropertyChangeListener pcl) {
    pcs.removePropertyChangeListener(pcl);
  }

  public void mouseClicked(MouseEvent me) {
  }

  public void mouseEntered(MouseEvent me) {
    inside = true;
  }

  public void mouseExited(MouseEvent me) {
    inside = false;
    if(pressed) {
      doMouseReleased();
    }
  }

  public void mousePressed(MouseEvent me) {
    if(inside) {
      doMousePressed();
    }
  }

  public void mouseReleased(MouseEvent me) {
    if(inside) {
      doMouseReleased();
    }
  }

  public void doMousePressed() {
    pressed = true;
    repaint();
  }

  public void doMouseReleased() {
    Boolean t = new Boolean(true);
    Boolean f = new Boolean(false);
    if(state) {
      pcs.firePropertyChange("state", t, f);
    }
    else {
      pcs.firePropertyChange("state", f, t);
    }
    state = !state;
    pressed = false;
    repaint();
  }
}
