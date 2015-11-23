package mgaussian;
import java.beans.*;

public class GaussianSenderBeanInfo extends SimpleBeanInfo {

  public BeanDescriptor getBeanDescriptor() {
    return new BeanDescriptor(GaussianSender.class, 
      GaussianSenderCustomizer.class);
  }
}