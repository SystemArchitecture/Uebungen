package containerevents;
import java.awt.*;
import java.awt.event.*;

public class ContainerReceiver extends Panel 
implements ContainerListener {
  private TextArea ta;

  public ContainerReceiver() {
    setLayout(null);
    ta = new TextArea();
    ta.setBounds(0, 0, 150, 300);
    add(ta);
    setSize(150, 300);
  }

  public void componentAdded(ContainerEvent ce) {
    ta.append("component added\n");
  }

  public void componentRemoved(ContainerEvent ce) {
    ta.append("component removed\n");
  }
}
  