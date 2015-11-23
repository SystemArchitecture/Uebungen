package greeting;
import java.awt.*;
import java.util.*;

public class Greeting extends Canvas {
  private final static int XPAD = 10;
  private final static int YPAD = 10;
  private String morning, afternoon, evening;
  private boolean border;

  public Greeting() {
    morning = "Good morning";
    afternoon = "Good afternoon";
    evening = "Good evening";
    border = true;
  }

  public String getMorning() {
    return morning;
  }

  public void setMorning(String morning) {
    this.morning = morning;
    adjustSize();
  }

  public String getAfternoon() {
    return afternoon;
  }

  public void setAfternoon(String afternoon) {
    this.afternoon = afternoon;
    adjustSize();
  }

  public String getEvening() {
    return evening;
  }

  public void setEvening(String evening) {
    this.evening = evening;
    adjustSize();
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

  public Dimension getPreferredSize() {
    Graphics g = getGraphics();
    FontMetrics fm = g.getFontMetrics();
    int w = fm.stringWidth(selectGreeting()) + 2*XPAD;
    int h = fm.getHeight() + 2*YPAD;
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
    String greeting = selectGreeting();
    Dimension d = getSize();
    FontMetrics fm = g.getFontMetrics();
    int x = (d.width - fm.stringWidth(greeting))/2;
    int y = (d.height + fm.getMaxAscent() - 
      fm.getMaxDescent())/2;
    g.drawString(greeting, x, y);
    if(border) {
      g.drawRect(0, 0, d.width - 1, d.height - 1);
    }
  }

  private String selectGreeting() {
    Calendar calendar = Calendar.getInstance();
    int hour = calendar.get(Calendar.HOUR_OF_DAY);
    if(hour < 12) {
      return morning;
    }
    else if(hour < 19) {
      return afternoon;
    }
    else {
      return evening;
    }
  }
}