package applications;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import spectrum.*;

public class SpectrumApplication {

  public static void main(String args[]) {
    new SpectrumApplication();
  }

  public SpectrumApplication() {
    Frame f = new Frame();
    f.setSize(200, 200);
    f.addWindowListener(new MyWindowAdapter());
    try {
      String name = "spectrum.Spectrum";
      Spectrum spectrum = 
        (Spectrum)Beans.instantiate(null, name);
      spectrum.setVertical(false);
      f.setLayout(null);
      Dimension d = spectrum.getSize();
      spectrum.setBounds(50, 50, d.width, d.height);
      f.add(spectrum);
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
    