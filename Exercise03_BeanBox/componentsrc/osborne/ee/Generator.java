package ee;
import java.io.*;
import java.util.*;

public class Generator implements Runnable, Serializable {
  private double amplitude;
  private double frequency;
  private double phase;
  private Vector listeners;
  private transient double time;
  private transient double value;
  private transient Thread t;

  public Generator() {
    amplitude = 1;
    frequency = 0.2;  //  cycles/sec
    phase = 0;
    listeners = new Vector();
    time = 0;
    startThread();
  }

  private void startThread() {
    t = new Thread(this);
    t.start();
  }

  public double getAmplitude() {
    return amplitude;
  }

  public void setAmplitude(double amplitude) {
    this.amplitude = amplitude;
  }

  public double getFrequency() {
    return frequency;
  }

  public void setFrequency(double frequency) {
    this.frequency = frequency;
  }

  public double getPhase() {
    return phase;
  }

  public void setPhase(double phase) {
    this.phase = phase;
  }

  public void run() {
    try {
      while(true) {
        double f = frequency;
        if(f == 0) {
          Thread.sleep(1000);
          continue;
        }
        double radians = 2 * Math.PI * f * time/1000 + phase;
        value = amplitude * Math.sin(radians);
        long interval = (long)(10/f);
        time += interval;
        fireWaveformEvent();
        Thread.sleep(interval);
      }
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public void addWaveformListener(WaveformListener wl) {
    listeners.addElement(wl);
  }

  public void removeWaveformListener(WaveformListener wl) {
    listeners.removeElement(wl);
  }

  protected synchronized void fireWaveformEvent() {
    Vector v;
    synchronized(this) {
      v = (Vector)listeners.clone();
    }
    WaveformEvent we = new WaveformEvent(this, value);
    for(int i = 0; i < v.size(); i++) {
      WaveformListener wl = (WaveformListener)v.elementAt(i);
      wl.waveformValueChanged(we);
    }
  }

  private void readObject(ObjectInputStream ois) {
    try {
      ois.defaultReadObject();
      startThread();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
}
