package veto;
import java.beans.*;

public class Auto {
  private int speed;
  private PropertyChangeSupport pcs;

  private VetoableChangeSupport vcs;

  public Auto() {
    speed = 0;
    vcs = new VetoableChangeSupport(this);
    pcs = new PropertyChangeSupport(this);

  }

  public int getSpeed() {
    return speed;
  }

  public void setSpeed(int value) 
  throws PropertyVetoException {
    
    Integer oldSpeed = new Integer(speed);
    Integer newSpeed = new Integer(value);
    vcs.fireVetoableChange("speed", oldSpeed, newSpeed);
    
    speed = value;
    //benachrichtige alle Listener über einen Property Change
    //bound property
    pcs.firePropertyChange("speed", oldSpeed, newSpeed);


  }

  public void 
  addVetoableChangeListener(VetoableChangeListener vcl) {
    vcs.addVetoableChangeListener(vcl);
  }

  public void 
  removeVetoableChangeListener(VetoableChangeListener vcl) {
    vcs.removeVetoableChangeListener(vcl);
  }

  public void 
    addPropertyChangeListener(PropertyChangeListener pcl) {
      pcs.addPropertyChangeListener(pcl);
    }

  public void 
    removePropertyChangeListener(PropertyChangeListener pcl) {
      pcs.removePropertyChangeListener(pcl);
    }

}