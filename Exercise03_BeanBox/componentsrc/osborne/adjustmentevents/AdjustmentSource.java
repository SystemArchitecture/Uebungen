package adjustmentevents;
import java.awt.*;

public class AdjustmentSource extends Scrollbar {

  public AdjustmentSource() {
  }

  public Dimension getPreferredSize() {
    return new Dimension(20, 200);
  }
}