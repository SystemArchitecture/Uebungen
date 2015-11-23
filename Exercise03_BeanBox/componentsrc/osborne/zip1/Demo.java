package zip1;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;

public class Demo extends Frame {

  public static void main(String[] args) {
    new Demo();
  }

  public Demo() {

    // Set the overall dimensions of the frame
    setSize(400, 400);

    // Process the contents of all the JAR files
    Jar.process();

    // Get reference to the one instance of JarClassLoader
    JarClassLoader jcl = JarClassLoader.singleton;

    // Set the layout manager to null
    setLayout(null);

    try {
      // Instantiate two Beans and add these to the frame
      Component component;
      String name = "plot3d.Plot3D";
      component = (Component)Beans.instantiate(jcl, name);
      component.setBounds(10, 10, 300, 250);
      add(component);
      name = "spectrum.Spectrum";
      component = (Component)Beans.instantiate(jcl, name);
      component.setBounds(10, 270, 100, 100);
      add(component);
    }
    catch(Exception ex) {
      System.out.println(ex);
    }

    // Arrange to handle window events
    addWindowListener(new MyWindowAdapter());

    // make the frame visible
    setVisible(true);
  }

  class MyWindowAdapter extends WindowAdapter {
    public void windowClosing(WindowEvent we) {
      System.exit(0);
    }
  }
}