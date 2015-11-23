package slidesapplet;
import java.beans.*;

public class SlidesAppletBeanInfo extends SimpleBeanInfo {

  public PropertyDescriptor[] getPropertyDescriptors() {
    try {
      PropertyDescriptor pd1 =
        new PropertyDescriptor("interval", SlidesApplet.class);
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
