package focusevents;
import java.awt.*;
import java.awt.event.*;

public class FocusReceiver extends Panel 
implements FocusListener {
  private TextArea ta;

  public FocusReceiver() {
    setLayout(null);
    ta = new TextArea();
    ta.setBounds(0, 0, 150, 300);
    add(ta);
    setSize(150, 300);
  }

  public void focusGained(FocusEvent fe) {
    ta.append("focus gained\n");
  }

  public void focusLost(FocusEvent fe) {
    ta.append("focus lost\n");
  }
}
  