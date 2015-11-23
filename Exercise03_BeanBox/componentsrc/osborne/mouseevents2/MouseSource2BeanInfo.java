package mouseevents2;
import java.beans.*;
import java.awt.event.*;
import java.lang.reflect.*;

public class MouseSource2BeanInfo extends SimpleBeanInfo {

  public PropertyDescriptor[] getPropertyDescriptors() {
    PropertyDescriptor pds[] = {  };
    return pds;
  }

  public EventSetDescriptor[] getEventSetDescriptors() {
    try {
      EventSetDescriptor esd1, esd2;
      String mnames[] = { "mouseClicked", "mouseEntered", 
        "mouseExited", "mousePressed", "mouseReleased" };
      esd1 = new EventSetDescriptor(MouseSource2.class, 
        "mouse", MouseListener.class, mnames, 
        "addMouseListener", "removeMouseListener");
      String mmnames[] = { "mouseDragged", "mouseMoved" };
      esd2 = new EventSetDescriptor(MouseSource2.class, 
        "mouseMotion", MouseMotionListener.class, mmnames, 
        "addMouseMotionListener", "removeMouseMotionListener");
      EventSetDescriptor esd[] = { esd1, esd2 };
      return esd;
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }

  public MethodDescriptor[] getMethodDescriptors() {
    MethodDescriptor mds[] = {  };
    return mds;
  }
}