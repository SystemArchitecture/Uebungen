package ee;
import java.beans.*;
import java.lang.reflect.*;

public class PlotterBeanInfo extends SimpleBeanInfo {

  public PropertyDescriptor[] getPropertyDescriptors() {
    try {
      PropertyDescriptor min, max, background;
      Class cls = Plotter.class;
      min = new PropertyDescriptor("min", cls);
      max = new PropertyDescriptor("max", cls);
      background = new PropertyDescriptor("background", cls);
      PropertyDescriptor pd[] = { min, max, background };
      return pd;
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
    return null;
  }

  public EventSetDescriptor[] getEventSetDescriptors() {
    EventSetDescriptor esds[] = { };
    return esds;
  }

  public MethodDescriptor[] getMethodDescriptors() {
    try {
      Class c = Plotter.class;
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