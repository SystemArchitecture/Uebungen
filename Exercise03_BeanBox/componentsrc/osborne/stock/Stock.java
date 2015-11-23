package stock;
import java.util.*;
public class Stock {
  private static Vector stocks = new Vector();
  private String symbol;
  private float price;
  public static Stock getInstance(String s) {
    Enumeration e = stocks.elements();
    while(e.hasMoreElements()) {
      Stock stock = (Stock)e.nextElement();
      if(stock.getSymbol().equals(s)) {
        stock.change();
        return stock;
      }
    } 
    return new Stock(s);
  }
  public Stock(String symbol) {
    this.symbol = symbol;
    int i = (int)(100*Math.random());
    int j = (int)(8 * Math.random());
    price = i + j * 0.125f;
    stocks.addElement(this);
  }
  public String getSymbol() {
    return symbol;
  }
  public float getPrice() {
    return price;
  }
  private void change() {
    int i = (int)price;
    int delta = (int)(i * 0.1 * Math.random());
    if(Math.random() < 0.5) {
      i += delta;
    }
    else {
      i -= delta;
    }
    int j = (int)(8 * Math.random());
    price = i + j * 0.125f;
  }
}