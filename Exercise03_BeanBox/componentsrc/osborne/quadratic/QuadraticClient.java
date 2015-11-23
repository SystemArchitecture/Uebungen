package quadratic;
import java.rmi.*;

public class QuadraticClient {

  public static void main(String args[]) {
    try {

      // Make rmi URL to name QuadraticServer
      String quadraticServerURL;
      quadraticServerURL = 
        "rmi://" + args[0] + "/QuadraticServer";

      // Obtain a reference to that remote object
      QuadraticServer quadraticServer;
      quadraticServer = 
        (QuadraticServer)Naming.lookup(quadraticServerURL);

      // Display coefficients
      double a = Double.valueOf(args[1]).doubleValue();
      double b = Double.valueOf(args[2]).doubleValue();
      double c = Double.valueOf(args[3]).doubleValue();
      System.out.println("The coefficients are:");
      System.out.println("\ta = " + a);
      System.out.println("\tb = " + b);
      System.out.println("\tc = " + c);

      // Invoke remote method and display results
      Complex results[] = quadraticServer.solve(a, b, c);
      System.out.println("The results are:");
      System.out.println("\t" + results[0]);
      System.out.println("\t" + results[1]);
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
}
