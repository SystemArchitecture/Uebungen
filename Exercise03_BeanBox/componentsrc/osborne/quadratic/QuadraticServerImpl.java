package quadratic;
import java.rmi.*;
import java.rmi.server.*;

public class QuadraticServerImpl 
extends UnicastRemoteObject implements QuadraticServer {

  public QuadraticServerImpl() 
  throws RemoteException {
  }

  public Complex[] solve(double a, double b, double c) 
  throws RemoteException {
    Complex roots[] = new Complex[2];
    double d = b * b - 4 * a * c;
    if(d > 0) {
      d = Math.sqrt(d);
      roots[0] = new Complex((-b + d)/(2 * a), 0);
      roots[1] = new Complex((-b - d)/(2 * a), 0);
    }
    else {
      d = Math.sqrt(-d);
      double e = -b/(2 * a);
      double f = d/(2 * a);
      roots[0] = new Complex(e, f);
      roots[1] = new Complex(e, -f);
    }
    return roots;
  }
}
