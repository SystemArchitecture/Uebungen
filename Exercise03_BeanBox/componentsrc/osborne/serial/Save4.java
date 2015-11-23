package serial;

import java.awt.*;
import java.io.*;

public class Save4 {
  public static void main(String args[]) {
    try {
      FileOutputStream fos = new FileOutputStream("save4.data");
      ObjectOutputStream oos = new ObjectOutputStream(fos);
      oos.writeObject(new E());
      oos.flush();
      fos.close();
    }
    catch(Exception ex) {
      System.out.println("Exception: " + ex);
    }
  }
}