package network;
import java.io.*;
import java.rmi.*;
import java.util.*;

public class NetworkServerApp {

  public static void main(String args[]) {
    try {

      // Create and register a NetworkServerImpl object
      NetworkServerImpl nsi = new NetworkServerImpl();
      Naming.rebind("NetworkServer", nsi);

      // Read and process link definitions from links.txt
      Vector links = new Vector();
      FileReader fr = new FileReader("network/links.txt");
      BufferedReader br = new BufferedReader(fr);
      String line;
      int id = 0;
      System.out.println("Links:");
      while((line = br.readLine()) != null) {
        StringTokenizer st = new StringTokenizer(line, ",");
        String city1 = st.nextToken();
        String city2 = st.nextToken();
        new Link(city1, city2, true);
        String str = "\t" + id + ": " + city1 + "," + city2;
        System.out.println(str);
        ++id;
      }
      System.out.println("");

      // Prompt user for changes to link status
      InputStreamReader isr;
      isr = new InputStreamReader(System.in);
      br = new BufferedReader(isr);
      while(true) {
        System.out.print("Link number? ");
        int linkNum = Integer.parseInt(br.readLine());
        if(linkNum >= id) {
          System.out.println("Error:  Invalid link number");
          continue;
        }
        System.out.print("Operational (true/false)? ");
        String status = br.readLine();
        boolean operational;
        operational = Boolean.valueOf(status).booleanValue();
        nsi.generateLinkEvent(linkNum, operational);
        System.out.println("");
      } 
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
}  
    