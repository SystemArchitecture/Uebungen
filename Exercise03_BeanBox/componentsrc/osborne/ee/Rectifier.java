package ee;
import java.io.*;
import java.util.*;

public class Rectifier 
implements Serializable, WaveformListener {
  private Vector listeners;

  public Rectifier() {
    listeners = new Vector();
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
    if(value < 0) {
      we2.setValue(-value);
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