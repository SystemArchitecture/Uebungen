package applications;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.io.*;
import graphs.*;

public class GraphApplication {
  Graph graph;

  public static void main(String args[]) {
    new GraphApplication();
  }

  public GraphApplication() {
    Frame f = new Frame();
    f.setSize(250, 250);
    f.addWindowListener(new MyWindowAdapter());
    try {
      graph = (Graph)Beans.instantiate(null, "graphs.Graph");
      f.add(graph);
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
    f.setVisible(true);
  }
  
  class MyWindowAdapter extends WindowAdapter {
    public void windowClosing(WindowEvent we) {
      try {
        FileOutputStream fos = 
          new FileOutputStream("graphs/Graph.ser");
        ObjectOutputStream oos = 
          new ObjectOutputStream(fos);
        oos.writeObject(graph);
        oos.flush();
        oos.close();
      }
      catch(Exception ex) {
        ex.printStackTrace();
      }
      System.exit(0);
    }
  }
}
    