package ee;
import java.util.*;

public interface WaveformListener extends EventListener {

  public abstract void waveformValueChanged(WaveformEvent we); 
}