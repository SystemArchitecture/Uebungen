package containerevents;
import java.awt.*;
import java.awt.event.*;
public class ContainerSource extends Panel {
  private final static int MAXBUTTONS = 9;
  private Button buttons[];
  private int index;
  public ContainerSource() {
    setLayout(new GridLayout(3, 3));
    buttons = new Button[MAXBUTTONS];
    index = 0;
  }
  public Dimension getPreferredSize() {
    return new Dimension(320, 80);
  }
  public void paint(Graphics g) {
    Dimension d = getSize();
    g.drawRect(0, 0, d.width - 1, d.height - 1);
  }
  public void addButton() {
    if(index >= MAXBUTTONS) {
      return;
    }
    buttons[index] = new Button("Button" + index);
    add(buttons[index]);
    ++index;
    doLayout();
  }
  public void removeButton() {
    if(index == 0) {
      return;
    }
    --index;
    remove(buttons[index]);
    doLayout();
  }
}