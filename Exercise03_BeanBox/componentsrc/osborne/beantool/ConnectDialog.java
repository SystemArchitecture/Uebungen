package beantool;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.lang.reflect.*;
import java.util.*;

public class ConnectDialog extends Dialog 
implements ItemListener {
  private Object bean1, bean2;
  private java.awt.List list;
  EventSetDescriptor esds[];

  public ConnectDialog(Frame frame, String title, 
  Object bean1, Object bean2) {

    // Invoke superclass constructor
    super(frame, "Connect Dialog", true);

    // Save bean1 and bean2
    this.bean1 = bean1;
    this.bean2 = bean2;

    // Create and initialize list
    list = new java.awt.List();
    initializeList();
    list.addItemListener(this);
    add("Center", list);

    // Register to receive window events
    addWindowListener(new MyWindowAdapter());

    // Set size of dialog and make it visible
    setSize(200, 200);
    show();
  }

  private void initializeList() {

    // Get the BeanInfo object for bean1
    Class cls1 = bean1.getClass();
    BeanInfo bi1;
    try {
      bi1 = Introspector.getBeanInfo(cls1);
    }
    catch(Exception ex) {
      return;
    }

    // Get the EventSetDescriptor objects for
    // bean1 and add these to the list
    esds = bi1.getEventSetDescriptors();
    for(int i = 0; i < esds.length; i++) {
      list.add(esds[i].getName());
    }
  }

  public void itemStateChanged(ItemEvent ie) {

    // Obtain the EventSetDescriptor object
    int index = list.getSelectedIndex();
    EventSetDescriptor esd = esds[index];

    // Obtain the registration method
    Method method = esd.getAddListenerMethod();

    // Invoke the registration method of
    // bean1 to register bean2
    Object args[] = new Object[1];
    args[0] = bean2;
    try {
      method.invoke(bean1, args);
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }

    // Dispose of this dialog box
    dispose();
  }

  class MyWindowAdapter extends WindowAdapter {
    public void windowClosing(WindowEvent we) {
      dispose();
    }
  }
}