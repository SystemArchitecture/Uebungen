package mouseevents2;
import java.beans.*;
import java.awt.event.*;
import java.lang.reflect.*;

public class MouseReceiver2BeanInfo extends SimpleBeanInfo {

  public PropertyDescriptor[] getPropertyDescriptors() {
    PropertyDescriptor pds[] = {  };
    return pds;
  }

  public EventSetDescriptor[] getEventSetDescriptors() {
    EventSetDescriptor esds[] = {  };
    return esds;
  }

  public MethodDescriptor[] getMethodDescriptors() {
    try {
      Class c = MouseReceiver2.class;
      Class pTypes[] = new Class[1];
      pTypes[0] = MouseEvent.class;
      Method method1 = c.getMethod("mouseClicked", pTypes);
      Method method2 = c.getMethod("mouseEntered", pTypes);
      Method method3 = c.getMethod("mouseExited", pTypes);
      Method method4 = c.getMethod("mousePressed", pTypes);
      Method method5 = c.getMethod("mouseReleased", pTypes);
      Method method6 = c.getMethod("mouseDragged", pTypes);
      Method method7 = c.getMethod("mouseMoved", pTypes);
      ParameterDescriptor pds[] = new ParameterDescriptor[1];
      pds[0] = new ParameterDescriptor();
      MethodDescriptor md1 = new MethodDescriptor(method1, pds);
      MethodDescriptor md2 = new MethodDescriptor(method2, pds);
      MethodDescriptor md3 = new MethodDescriptor(method3, pds);
      MethodDescriptor md4 = new MethodDescriptor(method4, pds);
      MethodDescriptor md5 = new MethodDescriptor(method5, pds);
      MethodDescriptor md6 = new MethodDescriptor(method6, pds);
      MethodDescriptor md7 = new MethodDescriptor(method7, pds);
      MethodDescriptor mds[] = { md1, md2, md3, md4, 
        md5, md6, md7 };
      return mds;
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }
}