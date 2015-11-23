package cselector;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class ColorEvent extends EventObject {
  private Color color;

  public ColorEvent(Object source, Color color) {
    super(source);
    this.color = color;
  }

  public Color getColor() {
    return color;
  }
}