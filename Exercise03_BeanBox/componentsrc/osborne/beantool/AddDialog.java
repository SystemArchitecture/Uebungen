package beantool;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class AddDialog extends Dialog 
implements ItemListener {
  private BeanTool beanTool;
  private java.awt.List list;

  public AddDialog(BeanTool beanTool) {

    // Invoke superclass constructor
    super(beanTool, "Add Dialog", true);

    // Initialize beanTool
    this.beanTool = beanTool;

    // Create and initialize list 
    // and add it to dialog box
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
    // Initialize list
    Vector beans = Bean.getBeans();
    Enumeration e = beans.elements();
    while(e.hasMoreElements()) {
      Bean bean = (Bean)e.nextElement();
      list.add(bean.getName());
    } 
  }

  public void itemStateChanged(ItemEvent ie) {
    // Process list selection
    String beanName = list.getSelectedItem();
    beanTool.insertBean(beanName);
    dispose();
  }

  class MyWindowAdapter extends WindowAdapter {
    public void windowClosing(WindowEvent we) {
      dispose();
    }
  }
}