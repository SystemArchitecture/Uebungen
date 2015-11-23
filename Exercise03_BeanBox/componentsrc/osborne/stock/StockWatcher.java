package stock;
import java.awt.*;
import java.io.*;
import java.net.*;

public class StockWatcher extends Panel {
  private Label symbolData, priceData;
  private String hostname, port, symbol;

  public StockWatcher() {
    setFont(new Font("Helvetica", Font.BOLD, 18));
    setLayout(new GridLayout(2, 2, 5, 5));
    Label symbolLabel = new Label("Symbol:", Label.RIGHT);
    add(symbolLabel);
    symbolData = new Label("");
    add(symbolData);
    Label priceLabel = new Label("Price:", Label.RIGHT);
    add(priceLabel);
    priceData = new Label("");
    add(priceData);
    hostname = port = symbol = "";
  }

  public String getHostname() {
    return hostname;
  }

  public void setHostname(String hostname) {
    this.hostname = hostname;
  }

  public String getPort() {
    return port;
  }

  public void setPort(String port) {
    this.port = port;
  }

  public String getSymbol() {
    return symbol;
  }

  public void setSymbol(String symbol) {
    this.symbol = symbol;
    repaint();
  }

  public void paint(Graphics g) {
    Dimension d = getSize();
    g.drawRect(0, 0, d.width - 1, d.height - 1);
    symbolData.setText(symbol);
  }

  public Insets getInsets() {
    return new Insets(5, 5, 5, 5);
  }

  public void refresh() {
    try {
      Socket socket = 
        new Socket(hostname, Integer.parseInt(port));
      InputStream is = socket.getInputStream();
      OutputStream os = socket.getOutputStream();
      PrintWriter pw = new PrintWriter(os, true);
      pw.println(symbol);
      BufferedReader br = 
        new BufferedReader(new InputStreamReader(is));
      String line = br.readLine();
      priceData.setText(line);
      socket.close();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
}

    