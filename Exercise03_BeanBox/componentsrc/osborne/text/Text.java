package text;
import java.awt.*;

public class Text extends Canvas {
  private final static int XPAD = 10;
  private final static int YPAD = 10;
  private String message;
  private boolean border;

  public Text() {
    message = "Hello";
  }

  public boolean getBorder() {
    return border;
  }

  public void setBorder(boolean border) {
    this.border = border;
    repaint();
  }

  public void setFont(Font font) {
    super.setFont(font);
    adjustSize();
  }

  public String getMessage() {
    return message;
  }
  public void setMessage(String message) {
    this.message = message;
    adjustSize();
  }

  public Dimension getPreferredSize() {
    Graphics g = getGraphics();
    FontMetrics fm = g.getFontMetrics();
    int w = fm.stringWidth(message) + 2 * XPAD;
    int h = fm.getHeight() + 2 * YPAD;
    return new Dimension(w, h);
  }

  private void adjustSize() {
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
    int w = d.width;
    int h = d.height;
    FontMetrics fm = g.getFontMetrics();
    int x = (d.width - fm.stringWidth(message))/2;
    int y = (d.height + fm.getMaxAscent() - 
      fm.getMaxDescent())/2;
    g.drawString(message, x, y);
    if(border) {
      g.drawRect(0, 0, w - 1, h - 1);
    }
  }
}