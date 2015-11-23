package hotjava;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;

public class UrlTextField extends TextField 
implements ActionListener, PropertyChangeListener {
  private PropertyChangeSupport pcs;

  public UrlTextField() {
    super("", 25);
    addActionListener(this);
    pcs = new PropertyChangeSupport(this);
  }

  public String getText() {
    return super.getText();
  }

  public void setText(String str) {
    super.setText(str);
  }

  public void actionPerformed(ActionEvent ae) {
    pcs.firePropertyChange("text", "", getText());
  }

  public void propertyChange(PropertyChangeEvent pce) {
    setText((String)pce.getNewValue());
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