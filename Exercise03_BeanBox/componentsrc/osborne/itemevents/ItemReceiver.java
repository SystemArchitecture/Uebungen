package itemevents;
import java.awt.*;
import java.awt.event.*;

public class ItemReceiver extends Panel 
implements ItemListener {
  private TextArea ta;

  public ItemReceiver() {
    setLayout(null);
    ta = new TextArea();
    ta.setBounds(0, 0, 150, 300);
    add(ta);
    setSize(150, 300);
  }

  public void itemStateChanged(ItemEvent ie) {
    int sc = ie.getStateChange();
    Object obj = ie.getItem();
    if(sc == ItemEvent.DESELECTED) {
      ta.append("deselected " + obj + "\n");
    }
    else {
      ta.append("selected " + obj + "\n");
    }
  }
}
  