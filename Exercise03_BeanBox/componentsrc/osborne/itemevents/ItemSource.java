package itemevents;
import java.awt.*;
import java.awt.event.*;
public class ItemSource extends List {
  public ItemSource() {
    add("One");
    add("Two");
    add("Three");
    add("Four");
    setMultipleMode(true);
  }
}