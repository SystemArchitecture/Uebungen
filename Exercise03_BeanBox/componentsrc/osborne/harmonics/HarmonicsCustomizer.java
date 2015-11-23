package harmonics;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.util.*;

public class HarmonicsCustomizer extends Panel 
implements Customizer, ItemListener {
  private PropertyChangeSupport pcsupport = 
    new PropertyChangeSupport(this);
  private Harmonics harmonics;
  private java.awt.List list;
  private Panel p;

  public HarmonicsCustomizer() {
    setLayout(new BorderLayout());
    Label label = 
      new Label("Select Harmonics:", Label.CENTER);
    add("North", label);
    list = new java.awt.List(Harmonics.NFREQUENCIES, true);
    for(int i = 1; i <= Harmonics.NFREQUENCIES; i++) {
      String s = "(1/" + i + ")(sin" + i + "x)";
      list.add(s);
    }
    list.addItemListener(this);
    p = new Panel();
    p.add(list);
    add("Center", p);
  }

  public Dimension getPreferredSize() {
    return new Dimension(300, 340);
  }

  public void setObject(Object object) {

    // Save reference to the Harmonics object
    harmonics = (Harmonics)object;

    // Get data from the harmonics object
    BitSet frequencies = harmonics.getFrequencies();
    for(int i = 0; i < Harmonics.NFREQUENCIES; i++) {
      if(frequencies.get(i)) {
        list.select(i);
      }
    }
  }

  public void 
  addPropertyChangeListener(PropertyChangeListener pcl) {
    pcsupport.addPropertyChangeListener(pcl);
  }

  public void 
  removePropertyChangeListener(PropertyChangeListener pcl) {
    pcsupport.removePropertyChangeListener(pcl);
  }

  public void itemStateChanged(ItemEvent ie) {
    BitSet frequencies = new BitSet(Harmonics.NFREQUENCIES);
    for(int i = 0; i < Harmonics.NFREQUENCIES; i++) {
      if(list.isIndexSelected(i)) {
        frequencies.set(i);
      }
    }
    harmonics.setFrequencies(frequencies);
  }
}