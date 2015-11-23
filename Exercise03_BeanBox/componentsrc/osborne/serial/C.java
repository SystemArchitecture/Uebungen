package serial;

import java.io.*;

public class C extends B implements Serializable {
  protected int c;
  public C() {
    c = 3;
  }
}