package ee;
import java.util.*;

public class WaveformEvent extends EventObject {
  private double value;

  public WaveformEvent(Object source, double value) {
    super(source);
    this.value = value;
  }

  public double getValue() {
    return value;
  }

  public void setValue(double value) {
     this.value = value;
  }
}