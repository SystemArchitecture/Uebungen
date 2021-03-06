package color;
import java.beans.*;

public class ColorViewerBeanInfo extends SimpleBeanInfo {

  public PropertyDescriptor[] getPropertyDescriptors() {
    try {
      PropertyDescriptor p1;
      p1 = new PropertyDescriptor("color", ColorViewer.class);
      p1.setPropertyEditorClass(ColorEditor.class);
      PropertyDescriptor pds[] = { p1 };
      return pds;
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }
}