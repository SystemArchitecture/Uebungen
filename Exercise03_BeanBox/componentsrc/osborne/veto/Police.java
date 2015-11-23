package veto;
import java.beans.*;

public class Police 
implements VetoableChangeListener {
  private static final int MAXSPEED = 85;
  private static final int MINSPEED = 0;

  public Police() {
  }

  public void vetoableChange(PropertyChangeEvent pce) 
  throws PropertyVetoException {
    if(pce.getPropertyName().equals("speed")) {
      Integer i = (Integer)pce.getNewValue();
      int speed = i.intValue();
      if(speed < MINSPEED || speed > MAXSPEED) {
        String msg = "Speed out of range";
        throw new PropertyVetoException(msg, pce);
      }
    }
  }
} 
  
      