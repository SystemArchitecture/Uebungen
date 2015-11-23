package point;
import java.beans.*;

public class PointViewerBeanInfo extends SimpleBeanInfo {

  public PropertyDescriptor[] getPropertyDescriptors() {
    try {
      PropertyDescriptor p1;
      p1 = new PropertyDescriptor("point", PointViewer.class);
      p1.setPropertyEditorClass(PointEditor.class);
      PropertyDescriptor pds[] = { p1 };
      return pds;
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }
}