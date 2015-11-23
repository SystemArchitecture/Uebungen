package beantool;
import java.awt.*;
import java.beans.*;
import java.util.*;

public class BeanInstance {
  private static Vector beanInstances = new Vector();
  private Object bean;
  private int x, y;

  public static Vector getBeanInstances() {
    return beanInstances;
  }

  public BeanInstance(BeanTool beanTool, 
  String beanName, int x, int y) {

     // Save the location of the component
     this.x = x;
     this.y = y;

    // Instantiate the component named beanName
    try {
      bean = Beans.instantiate(JarClassLoader.singleton, 
        beanName);
    }
    catch(Exception ex) {
      return;
    }

    // Ignore invisible components
    if(!Beans.isInstanceOf(bean, Component.class)) {
      return;
    }

    // Add the component to beanInstances
    beanInstances.addElement(this);

    // Position and layout the component
    Component c = (Component)bean;
    c.setLocation(x, y);
    c.doLayout();

    // Add the component to the BeanTool frame
    beanTool.add(c);
  }

  public Object getBean() {
    return bean;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }
}

        
        