package numbers;
import java.awt.*;
import java.text.*;
import java.util.*;

public class Numbers extends Canvas {
  private Locale locales[];

  public Numbers() {
    initLocales();
    setSize(420, 120);
  }

  private void initLocales() {
    locales = new Locale[5];
    locales[0] = Locale.CANADA;
    locales[1] = Locale.FRANCE;
    locales[2] = Locale.GERMANY;
    locales[3] = Locale.ITALY;
    locales[4] = Locale.JAPAN;
  }

  public void paint(Graphics g) {

    // Draw rectangle around the canvas boundaries
    Dimension d = getSize();
    int h = d.height;
    int w = d.width;
    g.drawRect(0, 0, w - 1, h - 1);

    // Draw lines for the grid columns
    int c = w/locales.length;
    for(int i = 1; i < locales.length; i++) {
      g.drawLine(i * c, 0, i * c, h);
    }

    // Draw a line under the first row
    int r = h/4;
    g.drawLine(0, r, w, r);

    // Determine font ascent
    FontMetrics fm = g.getFontMetrics();
    int ascent = fm.getAscent();

    // Draw the names of the countries on the first row
    for(int i = 0; i < locales.length; i++) {
      String s = locales[i].getDisplayCountry();
      int width = fm.stringWidth(s);
      int x = i * c + (c - width)/2;
      int y = (r - ascent)/2 + ascent;
      g.drawString(s, x, y);
    }

    // Draw the number 1000000 on the second row
    for(int i = 0; i < locales.length; i++) {
      NumberFormat nf;
      nf = NumberFormat.getNumberInstance(locales[i]);
      String s = nf.format(1000000);
      int width = fm.stringWidth(s);
      int x = i * c + (c - width)/2;
      int y = r + (r - ascent)/2 + ascent;
      g.drawString(s, x, y);
    } 

    // Draw the currency value 2000.5 on the third row
    for(int i = 0; i < locales.length; i++) {
      NumberFormat nf;
      nf = NumberFormat.getCurrencyInstance(locales[i]);
      String s = nf.format(2000.5);
      int width = fm.stringWidth(s);
      int x = i * c + (c - width)/2;
      int y = 2 * r + (r - ascent)/2 + ascent;
      g.drawString(s, x, y);
    } 

    // Draw the percentage value 34.67% on the fourth row
    for(int i = 0; i < locales.length; i++) {
      NumberFormat nf;
      nf = NumberFormat.getPercentInstance(locales[i]);
      String s = nf.format(34.67);
      int width = fm.stringWidth(s);
      int x = i * c + (c - width)/2;
      int y = 3 * r + (r - ascent)/2 + ascent;
      g.drawString(s, x, y);
    } 
  }
}