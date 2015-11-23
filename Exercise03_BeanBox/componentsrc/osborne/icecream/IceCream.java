package icecream;
import java.awt.*;
import java.util.*;

public class IceCream extends Canvas {
  private static Hashtable flavorToColor;
  static {
    flavorToColor = new Hashtable();
    flavorToColor.put("Vanilla", new Color(255, 255, 255));
    flavorToColor.put("Chocolate", new Color(119, 66, 8));
    flavorToColor.put("Strawberry", new Color(236, 112, 132));
    flavorToColor.put("Pistachio", new Color(113, 249, 158));
  }
  private String flavor;

  public IceCream() {
    flavor = "Strawberry";
    setSize(200, 200);
  }

  public String getFlavor() {
    return flavor;
  }

  public void setFlavor(String flavor) {
    this.flavor = flavor;
    repaint();
  }

  public void paint(Graphics g) {
    Color color = (Color)flavorToColor.get(flavor);
    g.setColor(color);
    Dimension d = getSize();
    g.fillRect(0, 0, d.width - 1, d.height - 1);
  }
}