package reflect;
import javax.swing.*;
import java.awt.*;
import java.lang.reflect.*;

public class GetDeclaredConstructorsDemo {
  public static void main(String args[]) {
    try {

      // Get the class object
      Class c = Class.forName(args[0]);

      // Display the constructors and their parameters
      Constructor constructors[] = c.getDeclaredConstructors();
      for(int i = 0; i < constructors.length; i++) {
        System.out.print(constructors[i].getName() + ": ");
        Class parameters[];
        parameters = constructors[i].getParameterTypes();
        for(int j = 0; j < parameters.length; j++) {
          String s = parameters[j].getName();
          s = s.substring(s.lastIndexOf(".") + 1, s.length());
          System.out.print(s + " ");
        }
        System.out.println("");
      }
      Class c1 = JPanel.class;
      constructors[0] = c1.getDeclaredConstructor(new Class[] {boolean.class});

    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
}
      