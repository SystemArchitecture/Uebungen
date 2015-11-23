package mgaussian;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;

public class GaussianReceiverCustomizer 
extends Panel implements ActionListener, Customizer {
  private PropertyChangeSupport pcsupport;
  TextField taddress, tport;
  GaussianReceiver greceiver;

  public GaussianReceiverCustomizer() {

    // Create GUI
    setLayout(new BorderLayout());
    Panel pc = new Panel();
    pc.setLayout(new GridLayout(2, 2, 5, 5));
    Label laddress = new Label("Address");
    laddress.setAlignment(Label.RIGHT);
    pc.add(laddress);
    taddress = new TextField("");
    pc.add(taddress);
    Label lport = new Label("Port");
    lport.setAlignment(Label.RIGHT);
    pc.add(lport);
    tport = new TextField("");
    pc.add(tport);
    add("Center", pc);
    Panel ps = new Panel();
    Button bapply = new Button("Apply");
    ps.add(bapply);
    bapply.addActionListener(this);
    add("South", ps);
    setSize(300, 400);

    // Create a PropertyChangeSupport object
    pcsupport = new PropertyChangeSupport(this);
  }

  public void setObject(Object o) {
    // Called by builder tool 
    greceiver = (GaussianReceiver)o;
    taddress.setText(greceiver.getAddress());
    tport.setText("" + greceiver.getPort());
  }

  public void actionPerformed(ActionEvent ae) {
    // Called when "apply" button is pressed
    int port = Integer.parseInt(tport.getText());
    greceiver.apply(taddress.getText(), port);
    pcsupport.firePropertyChange("", null, null);
  }

  public void 
  addPropertyChangeListener(PropertyChangeListener pcl) {
    pcsupport.addPropertyChangeListener(pcl);
  }

  public void 
  removePropertyChangeListener(PropertyChangeListener pcl) {
    pcsupport.removePropertyChangeListener(pcl);
  }
}