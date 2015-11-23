package ee;
import java.beans.*;
import java.lang.reflect.*;

public class RectifierBeanInfo extends SimpleBeanInfo {

  public PropertyDescriptor[] getPropertyDescriptors() {
    PropertyDescriptor pds[] = { };
    return pds;
  }

  public EventSetDescriptor[] getEventSetDescriptors() {
    try {
      EventSetDescriptor esd1;
      Class c = Rectifier.class;
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
    try {
      Class c = Rectifier.class;
      Class parameterTypes[] = new Class[1];
      parameterTypes[0] = WaveformEvent.class;
      String name = "waveformValueChanged";
      Method method1 = c.getMethod(name, parameterTypes);
      ParameterDescriptor pds[] = new ParameterDescriptor[1];
      pds[0] = new ParameterDescriptor();
      MethodDescriptor md1 = new MethodDescriptor(method1, pds);
      MethodDescriptor mds[] = { md1 };
      return mds;
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }
}