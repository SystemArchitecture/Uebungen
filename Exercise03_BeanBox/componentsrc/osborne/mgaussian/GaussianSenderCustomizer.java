package mgaussian;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;

public class GaussianSenderCustomizer 
extends Panel implements ActionListener, Customizer {
  private PropertyChangeSupport pcsupport;
  private TextField taddress, tport, tmseconds;
  private GaussianSender gsender;

  public GaussianSenderCustomizer() {

    // Create GUI
    setLayout(new BorderLayout());
    Panel pc = new Panel();
    pc.setLayout(new GridLayout(3, 2, 5, 5));
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
    Label lmseconds = new Label("Milliseconds");
    lmseconds.setAlignment(Label.RIGHT);
    pc.add(lmseconds);
    tmseconds = new TextField("");
    pc.add(tmseconds);
    add("Center", pc);
    Panel ps = new Panel();
    Button bapply = new Button("Apply");
    ps.add(bapply);
    bapply.addActionListener(this);
    add("South", ps);
    setSize(300, 400);

    // Create PropertyChangeSupport object
    pcsupport = new PropertyChangeSupport(this);
  }

  public void setObject(Object o) {
    // Called by builder tool 
    gsender = (GaussianSender)o;
    taddress.setText(gsender.getAddress());
    tport.setText("" + gsender.getPort());
    tmseconds.setText("" + gsender.getMsec());
  }

  public void actionPerformed(ActionEvent ae) {
    // Called when "apply" button is pressed
    int port = Integer.parseInt(tport.getText());
    int msec = Integer.parseInt(tmseconds.getText());
    gsender.apply(taddress.getText(), port, msec);
  }

  public void addPropertyChangeListener(
  PropertyChangeListener pcl) {
    pcsupport.addPropertyChangeListener(pcl);
  }

  public void removePropertyChangeListener(
  PropertyChangeListener pcl) {
    pcsupport.removePropertyChangeListener(pcl);
  }
}