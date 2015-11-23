package icecream;
import java.beans.*;

public class IceCreamBeanInfo extends SimpleBeanInfo {

  public PropertyDescriptor[] getPropertyDescriptors() {
    try {
      PropertyDescriptor p1;
      p1 = new PropertyDescriptor("flavor", IceCream.class);
      p1.setPropertyEditorClass(FlavorEditor.class);
      PropertyDescriptor pds[] = { p1 };
      return pds;
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }
}