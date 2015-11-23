package serial;

import java.io.*;

public class Restore4 {
  public static void main(String args[]) {
    try {
      FileInputStream fis = new FileInputStream("save4.data");
      ObjectInputStream ois = new ObjectInputStream(fis);
      System.out.println(ois.readObject());
      fis.close();
    }
    catch(Exception ex) {
      System.out.println("Exception: " + ex);
    }
  }
}