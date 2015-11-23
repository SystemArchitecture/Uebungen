package slides;
import java.beans.*;

public class SlidesBeanInfo extends SimpleBeanInfo {

  public PropertyDescriptor[] getPropertyDescriptors() {
    try {
      PropertyDescriptor pd1;
      pd1 = new PropertyDescriptor("interval", Slides.class);
      PropertyDescriptor pds[] = { pd1 };
      return pds;
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }

  public EventSetDescriptor[] getEventSetDescriptors() {
    EventSetDescriptor esds[] = { };
    return esds;
  }

  public MethodDescriptor[] getMethodDescriptors() {
    MethodDescriptor mds[] = { };
    return mds;
  }
}
