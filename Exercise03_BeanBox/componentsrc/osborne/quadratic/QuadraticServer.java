package quadratic;
import java.rmi.*;

public interface QuadraticServer extends Remote {

  Complex[] solve(double a, double b, double c) 
  throws RemoteException;
}
