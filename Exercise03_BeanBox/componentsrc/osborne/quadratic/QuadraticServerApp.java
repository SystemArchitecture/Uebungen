package quadratic;
import java.rmi.*;

public class QuadraticServerApp {

  public static void main(String args[]) {
    try {
      QuadraticServerImpl quadraticServerImpl;
      quadraticServerImpl = new QuadraticServerImpl();
      Naming.rebind("QuadraticServer", quadraticServerImpl);
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
}
