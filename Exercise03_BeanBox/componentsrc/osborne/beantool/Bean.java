package beantool;
import java.util.*;

public class Bean {
  private static Vector beans = new Vector();
  private String name;

  public static Vector getBeans() {
    return beans;
  }

  public Bean(String name) {
    this.name = name;
    beans.addElement(this);
  }

  public String getName() {
    return name;
  }
}