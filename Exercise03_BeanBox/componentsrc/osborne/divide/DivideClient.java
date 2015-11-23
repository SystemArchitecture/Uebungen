package divide;
import java.rmi.*;

public class DivideClient {

  public static void main(String args[]) {
    try {
     
      // Make rmi URL to name DivideServer
      String divideServerURL;
      divideServerURL = "rmi://" + args[0] + "/DivideServer";

      // Obtain a reference to that remote object
      DivideServer divideServer;
      divideServer = (DivideServer)Naming.lookup(divideServerURL);

      // Display numbers
      System.out.println("The first number is: " + args[1]);
      double d1 = Double.valueOf(args[1]).doubleValue();
      System.out.println("The second number is: " + args[2]);
      double d2 = Double.valueOf(args[2]).doubleValue();

      // Invoke remote method and display result
      double result = divideServer.divide(d1, d2);
      System.out.println("The result is: " + result);
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
}
