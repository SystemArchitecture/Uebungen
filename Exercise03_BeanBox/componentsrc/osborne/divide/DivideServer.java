package divide;
import java.rmi.*;

public interface DivideServer extends Remote {

  double divide(double d1, double d2) throws RemoteException;
}
