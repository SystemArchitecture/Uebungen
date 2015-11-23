package ee;
import java.awt.*;
import java.io.*;

public class Plotter extends Canvas 
implements WaveformListener {
  private final static int NVALUES = 51;
  private double min;
  private double max;
  private transient int data[];
  private transient int validEntries;
  private transient int writeIndex;
  private transient int gridOffset;

  public Plotter() {
    min = -1;
    max = 1;
    data = new int[NVALUES];
    validEntries = 0;
    writeIndex = 0;
    gridOffset = 0;
    setSize(200, 100);
  }

  public double getMax() {
    return max;
  }

  public void setMax(double max) {
    this.max = max;
    repaint();
  }

  public double getMin() {
    return min;
  }

  public void setMin(double min) {
    this.min = min;
    repaint();
  }

  public void waveformValueChanged(WaveformEvent we) {
    Dimension d = getSize();
    int h = d.height;
    data[writeIndex] = scale(we.getValue(), h);
    if(validEntries < NVALUES) {
      ++validEntries;
    }
    if(++writeIndex >= NVALUES) {
      writeIndex = 0;
    }
    repaint();
  }

  private int scale(double value, int h) {
    if(value > max) {
      return 0;
    }
    else if(value < min) {
      return h;
    }
    else {
      double k = (value-min)/(max-min);
      return (int)(h * (1 - k));
    }  
  }

  public void paint(Graphics g) {

    // Draw rectangle around the display area
    Dimension d = getSize();
    int h = d.height;
    int w = d.width;
    g.setColor(Color.gray);
    g.drawRect(0, 0, w - 1, h - 1);

    // Draw vertical lines
    double deltax = w/(NVALUES - 1);
    int x;
    for(int i = 0; i <= 10; i++) {
      x = i * w/10 - gridOffset;
      if(x < 0) {
        continue;
      }
      g.drawLine(x, 0, x, h);
    }
    gridOffset += deltax;
    if(gridOffset >= w/10) {
      gridOffset = 0;
    }

    // Draw horizontal lines
    int y;
    for(int i = 1; i < 5; i++) {
      y = i * h/5;
      g.drawLine(0, y, w, y);
    }

    // Prepare to plot the WaveformEvent objects
    g.setColor(Color.blue);
    int readIndex = 0;
    if(validEntries == NVALUES) {
      readIndex = writeIndex + 1;
      if(readIndex == NVALUES) {
        readIndex = 0;
      }
    }

    // Return if there are not at least two points
    if(validEntries < 2) {
      return;
    }

    // Draw the plot
    for(int i = 0; i < validEntries - 2; i++) {
      int x1, y1, x2, i2, y2;
      x1 = (int)(i * deltax);
      y1 = data[readIndex];
      x2 = (int)((i + 1) * deltax);
      i2 = (readIndex+1 == NVALUES) ? 0 : readIndex+1; 
      y2 = data[i2];
      g.drawLine(x1, y1, x2, y2);
      ++readIndex;
      if(readIndex == NVALUES) {
        readIndex = 0;
      }
    }
  }

  private void readObject(ObjectInputStream ois) {
    try {
      ois.defaultReadObject();
      data = new int[NVALUES];
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
}