package textevents;
import java.awt.*;
import java.awt.event.*;

public class TextReceiver extends Panel 
implements TextListener {
  private TextArea ta;

  public TextReceiver() {
    setLayout(null);
    ta = new TextArea();
    ta.setBounds(0, 0, 150, 300);
    add(ta);
    setSize(150, 300);
  }

  public void textValueChanged(TextEvent te) {
    ta.append("text value changed\n");
  }
}