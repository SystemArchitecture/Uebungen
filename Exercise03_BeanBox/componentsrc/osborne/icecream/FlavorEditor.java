package icecream;
import java.beans.*;

public class FlavorEditor extends PropertyEditorSupport {

  public String[] getTags() {
    String flavors[] = { "Vanilla", "Chocolate", 
      "Strawberry", "Pistachio" };
    return flavors;
  }
}