package keyevents;
import java.awt.*;
import java.awt.event.*;

public class KeyReceiver extends Panel 
implements KeyListener {
  private TextArea ta;

  public KeyReceiver() {
    setLayout(null);
    ta = new TextArea();
    ta.setBounds(0, 0, 150, 300);
    add(ta);
    setSize(150, 300);
  }

  public void keyPressed(KeyEvent ke) {
    ta.append("key pressed\n");
  }

  public void keyReleased(KeyEvent ke) {
    ta.append("key released\n");
  }

  public void keyTyped(KeyEvent ke) {
    ta.append("key typed: " + ke.getKeyChar() + "\n");
  }
}
  