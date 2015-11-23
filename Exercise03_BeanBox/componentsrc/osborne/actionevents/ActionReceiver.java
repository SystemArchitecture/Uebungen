package actionevents;
import java.awt.*;
import java.awt.event.*;

public class ActionReceiver extends Panel 
implements ActionListener {
  private TextArea ta;

  public ActionReceiver() {
    setLayout(null);
    ta = new TextArea();
    ta.setBounds(0, 0, 200, 300);
    add(ta);
    setSize(200, 300);
  }

  public void actionPerformed(ActionEvent ae) {
    String ac = ae.getActionCommand();
    int modifiers = ae.getModifiers();
    String s = ac;
    if((modifiers & ActionEvent.ALT_MASK) != 0) {
      s += ", ALT_MASK";
    }
    if((modifiers & ActionEvent.CTRL_MASK) != 0) {
      s += ", CTRLT_MASK";
    }
    if((modifiers & ActionEvent.META_MASK) != 0) {
      s += ", META_MASK";
    }
    if((modifiers & ActionEvent.SHIFT_MASK) != 0) {
      s += ", SHIFT_MASK";
    }
    ta.append(s + "\n");
  }
}