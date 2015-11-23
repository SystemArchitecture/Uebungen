package network;
import java.beans.*;

public class NetworkCanvasBeanInfo 
extends SimpleBeanInfo {

  public BeanDescriptor getBeanDescriptor() {
    return new BeanDescriptor(NetworkCanvas.class, 
      NetworkCanvasCustomizer.class);
  }
}