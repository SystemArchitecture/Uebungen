package stock;
import java.io.*;
import java.net.*;

public class StockServerApp {
  private final static int PORT = 5000;

  public static void main(String args[]) {
    try {
      ServerSocket ssocket = new ServerSocket(PORT);
      while(true) {
        Socket socket = ssocket.accept();
        InputStream is = socket.getInputStream();
        BufferedReader br = 
          new BufferedReader(new InputStreamReader(is));
        OutputStream os = socket.getOutputStream();
        PrintWriter pw = new PrintWriter(os, true);
        String symbol = br.readLine();
        Stock stock = Stock.getInstance(symbol);
        if(stock != null) {
          pw.println("" + stock.getPrice());
        }
        socket.close();
      }
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
}
        
    