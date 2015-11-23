package network;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;

public class NetworkCanvasCustomizer 
extends Panel implements ActionListener, Customizer {
  private PropertyChangeSupport pcsupport = 
    new PropertyChangeSupport(this);
  private NetworkCanvas networkCanvas;
  private Label l;
  private TextField tf;
  private Button b;

  public NetworkCanvasCustomizer() {
  }

  public void setObject(Object object) {
    networkCanvas = (NetworkCanvas)object;
    l = new Label("Server Address");
    add(l);
    tf = new TextField("", 15);
    tf.setText(networkCanvas.getAddress());
    add(tf);
    b = new Button("Apply");
    add(b);
    b.addActionListener(this);
  }

  public Dimension getPreferredSize() {
    return new Dimension(200, 70);
  }

  public void 
  addPropertyChangeListener(PropertyChangeListener pcl) {
    pcsupport.addPropertyChangeListener(pcl);
  }

  public void 
  removePropertyChangeListener(PropertyChangeListener pcl) {
    pcsupport.removePropertyChangeListener(pcl);
  }

  public void actionPerformed(ActionEvent ae) {
    networkCanvas.apply(tf.getText());
  }
}