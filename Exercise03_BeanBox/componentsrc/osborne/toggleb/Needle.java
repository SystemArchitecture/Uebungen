package toggleb;
import java.awt.*;
import java.beans.*;
import java.io.*;

public class Needle extends Canvas 
implements PropertyChangeListener, Runnable {
  private int interval;
  private boolean operate;
  private int angle;

  public Needle() {
    interval = 10;
    operate = true;
    angle = 0;
    setSize(200, 200);
    Thread t = new Thread(this);
    t.start();
  }

  public int getInterval() {
    return interval;
  }

  public boolean getOperate() {
    return operate;
  }

  public void setInterval(int interval) {
    this.interval = interval;
  }

  public void setOperate(boolean operate) {
    this.operate = operate;
  }

  public void run() {
    try {
      while(true) {
        Thread.sleep(interval);
        if(operate) {
          ++angle;
          repaint();
        }
      }
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public void propertyChange(PropertyChangeEvent pce) {
    Boolean b = (Boolean)pce.getNewValue();
    setOperate(b.booleanValue());
  }

  public void paint(Graphics g) {
    Dimension d = getSize();
    g.drawOval(0, 0, d.width - 1, d.height - 1);
    int centerx = d.width/2;
    int centery = d.height/2;
    int radius = d.width/2;
    int x = (int)(radius * Math.sin(Math.PI * angle / 180));
    int y = (int)(radius * Math.cos(Math.PI * angle / 180));
    g.drawLine(centerx, centery, centerx + x, centery - y);
  }

  private void readObject(ObjectInputStream ois) 
  throws IOException, ClassNotFoundException {
    try {
      ois.defaultReadObject();
      Thread t = new Thread(this);
      t.start();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
}