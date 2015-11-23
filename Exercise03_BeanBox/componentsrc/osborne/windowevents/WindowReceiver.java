package windowevents;
import java.applet.*;
import java.awt.*;
import java.awt.event.*;

public class WindowReceiver extends Panel implements 
ActionListener, ComponentListener, WindowListener {
  private Frame f;
  private Button b;
  private TextArea ta;

  public WindowReceiver() {
    setLayout(new BorderLayout());
    f = new Frame();;
    f.setSize(200, 100);
    f.addComponentListener(this);
    f.addWindowListener(this);
    b = new Button("Show");
    b.addActionListener(this);
    add("North", b);
    ta = new TextArea();
    add("Center", ta);
  }

  public void actionPerformed(ActionEvent ae) {
    if(ae.getActionCommand() == "Show") {     
      f.setVisible(true);
      b.setLabel("Hide");
    } 
    else {
      f.setVisible(false);
      b.setLabel("Show");
    }
  }

  public void componentHidden(ComponentEvent ce) {
    ta.append("Component hidden\n");
  }

  public void componentMoved(ComponentEvent ce) {
    ta.append("Component moved\n");
  }

  public void componentResized(ComponentEvent ce) {
    ta.append("Component resized\n");
  }

  public void componentShown(ComponentEvent ce) {
    ta.append("Component shown\n");
  }

  public void windowActivated(WindowEvent we) {
    ta.append("Window activated\n");
  }

  public void windowClosed(WindowEvent we) {
    ta.append("Window closed\n");
  }

  public void windowClosing(WindowEvent we) {
    ta.append("Window closing\n");
    f.dispose();
  }

  public void windowDeactivated(WindowEvent we) {
    ta.append("Window deactivated\n");
  }

  public void windowDeiconified(WindowEvent we) {
    ta.append("Window deiconified\n");
  }

  public void windowIconified(WindowEvent we) {
    ta.append("Window iconified\n");
  }

  public void windowOpened(WindowEvent we) {
    ta.append("Window opened\n");
  }
}