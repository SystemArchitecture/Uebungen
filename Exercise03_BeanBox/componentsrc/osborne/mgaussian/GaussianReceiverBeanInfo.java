package mgaussian;
import java.beans.*;

public class GaussianReceiverBeanInfo extends SimpleBeanInfo {

  public BeanDescriptor getBeanDescriptor() {
    BeanDescriptor bd = new BeanDescriptor(GaussianReceiver.class, 
      GaussianReceiverCustomizer.class);
    return bd;
  }
}