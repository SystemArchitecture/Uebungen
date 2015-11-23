package applications;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import cselector.*;

public class ColorSelectorApplication {

  public static void main(String args[]) {
    new ColorSelectorApplication();
  }

  public ColorSelectorApplication() {
    Frame f = new Frame();
    f.setSize(200, 200);
    f.addWindowListener(new MyWindowAdapter());
    try {
      String name = "cselector.Painter";
      Painter painter = (Painter)Beans.instantiate(null, name);
      name = "cselector.Selector";
      Selector selector = (Selector)Beans.instantiate(null, name);
      selector.addColorListener(painter);
      f.setLayout(null);
      selector.setBounds(35, 95, 130, 90);
      f.add(selector);
      painter.setBounds(75, 40, 50, 50);
      f.add(painter);
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
    f.setVisible(true);
  }
  
  class MyWindowAdapter extends WindowAdapter {
    public void windowClosing(WindowEvent we) {
      System.exit(0);
    }
  }
}
    