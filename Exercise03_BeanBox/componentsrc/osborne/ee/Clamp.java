package ee;
import java.io.*;
import java.util.*;

public class Clamp implements Serializable, WaveformListener {
  private double max;
  private double min;
  private Vector listeners;

  public Clamp() {
    max = 0.5;
    min = -0.5;
    listeners = new Vector();
  }

  public double getMax() {
    return max;
  }

  public double getMin() {
    return min;
  }

  public void setMax(double max) {
    this.max = max;
  }

  public void setMin(double min) {
    this.min = min;
  }

  public void addWaveformListener(WaveformListener wl) {
    listeners.addElement(wl);
  }

  public void removeWaveformListener(WaveformListener wl) {
    listeners.removeElement(wl);
  }

  public void waveformValueChanged(WaveformEvent we) {
    WaveformEvent we2;
    we2 = new WaveformEvent(we.getSource(), we.getValue());
    double value = we2.getValue();
    if(value < min) {
      we2.setValue(min);
    }
    else if(value > max) {
      we2.setValue(max);
    }
    Vector v;
    synchronized(this) {
      v = (Vector)listeners.clone();
    }
    for(int i = 0; i < v.size(); i++) {
      WaveformListener wl = (WaveformListener)v.elementAt(i);
      wl.waveformValueChanged(we2);
    }
  }
}