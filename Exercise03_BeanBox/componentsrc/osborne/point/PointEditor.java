package point;
import java.awt.*;
import java.beans.*;

public class PointEditor extends PropertyEditorSupport {

  public String getAsText() {
    Point point = (Point)getValue();
    return "" + point.x + "," + point.y;
  }

  public void setAsText(String text) 
  throws IllegalArgumentException {
    try {
      int index = text.indexOf(",");
      String sx = (index == -1) ? text : 
        text.substring(0, index);
      String sy = (index == -1) ? "0" : 
        text.substring(index + 1);
      int x = Integer.parseInt(sx.trim());
      int y = Integer.parseInt(sy.trim());
      setValue(new Point(x, y));
    }
    catch(Exception ex) {
      throw new IllegalArgumentException();
    }
  }
}
    