package mouseevents2;
import java.awt.*;
import java.awt.event.*;

public class MouseReceiver2 extends Panel 
implements MouseListener, MouseMotionListener {
  private TextArea ta;

  public MouseReceiver2() {
    setLayout(null);
    ta = new TextArea();
    ta.setBounds(0, 0, 150, 300);
    add(ta);
    setSize(150, 300);
  }

  public void mouseClicked(MouseEvent me) {
    ta.append("mouse clicked\n");
  }

  public void mouseEntered(MouseEvent me) {
    ta.append("mouse entered\n");
  }

  public void mouseExited(MouseEvent me) {
    ta.append("mouse exited\n");
  }

  public void mousePressed(MouseEvent me) {
    ta.append("mouse pressed\n");
  }

  public void mouseReleased(MouseEvent me) {
    ta.append("mouse released\n");
  }

  public void mouseDragged(MouseEvent me) {
    ta.append("mouse dragged\n");
  }

  public void mouseMoved(MouseEvent me) {
    ta.append("mouse moved\n");
  }
}
  