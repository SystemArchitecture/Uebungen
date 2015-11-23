package version;
import java.io.*;

public class Restore {
  public static void main(String args[]) {
    try {
      FileInputStream fis = new FileInputStream(args[0]);
      ObjectInputStream ois = new ObjectInputStream(fis);
      Object obj = ois.readObject();
      fis.close();
      System.out.println(obj);
    }
    catch(Exception ex) {
      System.out.println("Exception: " + ex);
    }
  }
}
