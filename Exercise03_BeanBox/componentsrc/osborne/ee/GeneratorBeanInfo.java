package ee;
import java.beans.*;
import java.lang.reflect.*;

public class GeneratorBeanInfo extends SimpleBeanInfo {

  public PropertyDescriptor[] getPropertyDescriptors() {
    try {
      PropertyDescriptor a, f, p;
      Class cls = Generator.class;
      a = new PropertyDescriptor("amplitude", cls);
      f = new PropertyDescriptor("frequency", cls);
      p = new PropertyDescriptor("phase", cls);
      PropertyDescriptor pds[] = { a, f, p };
      return pds;
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }

  public EventSetDescriptor[] getEventSetDescriptors() {
    try {
      EventSetDescriptor esd1;
      Class c = Generator.class;
      String es = "waveform";
      Class lc = WaveformListener.class;
      String names[] = { "waveformValueChanged" };
      String al = "addWaveformListener";
      String rl  = "removeWaveformListener";
      esd1 = new EventSetDescriptor(c, es, lc, names, al, rl);
      EventSetDescriptor esd[] = { esd1 };
      return esd;
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }

  public MethodDescriptor[] getMethodDescriptors() {
    MethodDescriptor mds[] = { };
    return mds;
  }
}