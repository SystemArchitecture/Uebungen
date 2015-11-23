package divide;
import java.net.*;
import java.rmi.*;

public class DivideServerApp {

  public static void main(String args[]) {
    try {
      DivideServerImpl divideServerImpl;
      divideServerImpl = new DivideServerImpl();
      Naming.rebind("DivideServer", divideServerImpl);
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
}
