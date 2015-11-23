package counter;
import java.awt.*;

public class Counter extends Canvas {
  private final static int XPAD = 10;
  private final static int YPAD = 10;
  private int count;
  private boolean operate;

  public Counter() {
    count = 0;
    operate = true;
  }

  public void reset() {
    count = 0;
    repaint();
  }

  public void start( ) {
    operate = true;
  }

  public void stop() {
    operate = false;
  }

  public synchronized void increment() {
    if(operate) {
      ++count;
      adjustSize();
      repaint();
    }
  }

  public void setFont(Font font) {
    super.setFont(font);
    adjustSize();
  }

  public Dimension getPreferredSize() {
    Graphics g = getGraphics();
    FontMetrics fm = g.getFontMetrics();
    int w = fm.stringWidth("" + count) + 2 * XPAD;
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
    FontMetrics fm = g.getFontMetrics();
    int x = (d.width - fm.stringWidth("" + count))/2;
    int y = (d.height + fm.getMaxAscent() - 
      fm.getMaxDescent())/2;
    g.drawString("" + count, x, y);
    g.drawRect(0, 0, d.width - 1, d.height - 1);
  }
}
    
  