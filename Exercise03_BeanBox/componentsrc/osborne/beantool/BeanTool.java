package beantool;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.*;

public class BeanTool extends Frame {
  private int x, y;
  private Object bean1;

  public static void main(String args[]) {
    Jar.process();
    new BeanTool();
  }

  public BeanTool() {
    super("Bean Tool");
    addMouseListener(new MyMouseAdapter());
    addWindowListener(new MyWindowAdapter());
    setSize(400, 400);
    setVisible(true);
  }

  class MyMouseAdapter extends MouseAdapter {
    public void mouseClicked(MouseEvent me) {
      x = me.getX();
      y = me.getY();
      if(me.isAltDown()) {
        add();
      }
      else if(me.isShiftDown()) {
        connect();
      }
      repaint();
    }
  }

  class MyWindowAdapter extends WindowAdapter {
    public void windowClosing(WindowEvent we) {
      System.exit(0);
    }
  }

  public void add() {
    new AddDialog(this);
    bean1 = null;
  }

  public void connect() {
     BeanInstance bi = selectBeanInstance();
     if(bi != null) {
       if(bean1 == null) {
         bean1 = bi.getBean();
         return;
       }
       Object bean2 = bi.getBean();
       new ConnectDialog(this, "Title", bean1, bean2);
     }
     bean1 = null;
  }

  public BeanInstance selectBeanInstance() {
    // Determine which component is being selected
    Vector v = BeanInstance.getBeanInstances();
    Enumeration e = v.elements();
    while(e.hasMoreElements()) {
      BeanInstance bi = (BeanInstance)e.nextElement();
      Component c = (Component)bi.getBean();
      Point p = c.getLocation();
      Dimension d = c.getSize();
      Rectangle r = 
        new Rectangle(p.x, p.y - 10, d.width - 1, 10);
      if(r.contains(x, y)) {
        return bi;
      }
    }
    return null;
  }

  public void doLayout() {
    // Layout all of the components
    Vector v = BeanInstance.getBeanInstances();
    Enumeration e = v.elements();
    while(e.hasMoreElements()) {
      BeanInstance bi = (BeanInstance)e.nextElement();
      Component c = (Component)bi.getBean();
      Dimension d = c.getPreferredSize();
      c.setBounds(bi.getX(), bi.getY(), d.width, d.height);
      c.doLayout();
    }
  }
    
  public void insertBean(String beanName) {

    // Create a new instance of the Bean
    // named beanName
    BeanInstance bi = new BeanInstance(this, beanName, x, y);

    // Layout all components 
    doLayout();
  }
}
        
        