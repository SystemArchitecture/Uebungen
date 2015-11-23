package icalendar;
import java.awt.*;
import java.text.*;
import java.util.*;

public class CalendarCanvas extends Canvas {
  private CalendarViewer cv;
  private String dayNames[];

  public CalendarCanvas(CalendarViewer cv) {
    this.cv = cv;
    DateFormatSymbols dfs = new DateFormatSymbols();
    dayNames = dfs.getWeekdays();
  }

  public void paint(Graphics g) {

    // Get year, month, date, and day-of-week information
    GregorianCalendar gc = cv.getGCalendar();
    int year = gc.get(Calendar.YEAR);
    int month = gc.get(Calendar.MONTH);
    int date = gc.get(Calendar.DATE);
    int dow = gc.get(Calendar.DAY_OF_WEEK);
    int daysInMonths[] = { 31, 28, 31, 30, 31, 30, 
      31, 31, 30, 31, 30, 31 };
    daysInMonths[1] += 
      gc.isLeapYear(year) ? 1 : 0;

    // Draw rectangle around the canvas boundaries
    Dimension d = getSize();
    int h = d.height;
    int w = d.width;
    g.drawRect(0, 0, w - 1, h - 1);

    // Draw lines for the grid columns
    int c = w/7;
    for(int i = 1; i < 7; i++) {
      g.drawLine(i * c, 0, i * c, h);
    }

    // Draw lines for the grid rows
    int r = h/13;
    for(int i = 1; i <= 11; i = i + 2) {
      g.drawLine(0, i * r, w, i * r);
    }

    // Draw the names of the days on the grid
    int j = gc.getFirstDayOfWeek();
    for(int i = 0; i < 7; i++) {
      String s = dayNames[j];
      FontMetrics fm = g.getFontMetrics();
      int ascent = fm.getAscent();
      int width = fm.stringWidth(s);
      int x = i * c + (c - width)/2;
      int y = (r - ascent)/2 + ascent;
      g.drawString(s, x, y);
      if(++j > 7) {
        j = 1;
      }
    }

    // Compute the variable dom
    // (If necessary, this will be a negative value)
    int fdow = gc.getFirstDayOfWeek();
    int dom = (fdow <= dow) ? fdow - dow + 1 : fdow - 8 + dow;

    // Draw numbers into the grid cells
    for(int row = 0; row < 6; row++) {
      for(int col = 0; col < 7; col++) {
        if(dom > daysInMonths[month]) {
          break;
        }
        if(dom > 0) {
          // Display date in the grid cell
          String s = "" + dom;
          FontMetrics fm = g.getFontMetrics();
          int ascent = fm.getAscent();
          int width = fm.stringWidth(s);
          int x = c * col + (c - width)/2;
          int y = 2 * r * row + 
            (2 * r - ascent)/2 + ascent + r;
          g.drawString(s, x, y);
        }
        ++dom;
      }
    }
  }
}