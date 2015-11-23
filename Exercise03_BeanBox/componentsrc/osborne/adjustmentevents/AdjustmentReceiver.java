package adjustmentevents;
import java.awt.*;
import java.awt.event.*;

public class AdjustmentReceiver extends Panel 
implements AdjustmentListener {
  private TextArea ta;

  public AdjustmentReceiver() {
    setLayout(null);
    ta = new TextArea();
    ta.setBounds(0, 0, 200, 300);
    add(ta);
    setSize(200, 300);
  }

  public void adjustmentValueChanged(AdjustmentEvent ae) {
    int type = ae.getAdjustmentType();
    int value = ae.getValue();
    if(type == AdjustmentEvent.BLOCK_DECREMENT) {
      ta.append("block decrement, value = " + value + "\n");
    }
    else if(type == AdjustmentEvent.BLOCK_INCREMENT) {
      ta.append("block increment, value = " + value + "\n");
    }
    else if(type == AdjustmentEvent.TRACK) {
      ta.append("track, value = " + value + "\n");
    }
    else if(type == AdjustmentEvent.UNIT_DECREMENT) {
      ta.append("unit decrement, value = " + value + "\n");
    }
    else if(type == AdjustmentEvent.UNIT_INCREMENT) {
      ta.append("unit increment, value = " + value + "\n");
    }
  }
}
  