package spectrum2;
import java.beans.*;
import java.awt.event.*;
import java.lang.reflect.*;

public class Spectrum2BeanInfo extends SimpleBeanInfo {

  public PropertyDescriptor[] getPropertyDescriptors() {
    try {
      Class cls = Spectrum2.class;
      PropertyDescriptor pd;
      pd = new PropertyDescriptor("vertical", cls);
      PropertyDescriptor pds[] = { pd };
      return pds;
    }
    catch(Exception ex) {
    }
    return null;
  }

  public EventSetDescriptor[] getEventSetDescriptors() {
    EventSetDescriptor esds[] = {  };
    return esds;
  }

  public MethodDescriptor[] getMethodDescriptors() {
    MethodDescriptor mds[] = {  };
    return mds;
  }
}