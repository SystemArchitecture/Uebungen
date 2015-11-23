package widgets;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Widget extends Canvas {
  private static Vector widgets = new Vector();
  private static boolean square = false;

  public Widget() {
    widgets.addElement(this);
    setSize(40, 40);
  }

  public boolean getSquare() {
    return square;
  }

  public void setSquare(boolean square) {
    this.square = square;
    Enumeration e = widgets.elements();
    while(e.hasMoreElements()) {
      Widget w = (Widget)e.nextElement();
      w.repaint();
    }
  }

  public void paint(Graphics g) {
    Dimension d = getSize();
    if(square) {
      g.fillRect(0, 0, d.width - 1, d.height - 1);
    }
    else {
      g.fillOval(0, 0, d.width - 1, d.height - 1);
    }
  }

  private void writeObject(ObjectOutputStream oos) 
  throws IOException {
    try {
      oos.defaultWriteObject();
      if(widgets.elementAt(0) == this) {
        oos.writeBoolean(square);
      }
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  private void readObject(ObjectInputStream ois) 
  throws IOException, ClassNotFoundException {
    try { 
      ois.defaultReadObject();
      widgets.addElement(this);
      if(widgets.elementAt(0) == this) {      
        square = ois.readBoolean();
      }
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
}