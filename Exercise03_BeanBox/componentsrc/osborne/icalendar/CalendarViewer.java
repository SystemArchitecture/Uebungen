package icalendar;
import java.awt.*;
import java.util.*;

public class CalendarViewer extends Panel {
  private GregorianCalendar gCalendar;
  private CalendarLabel calendarLabel;
  private CalendarCanvas calendarCanvas;
  private CalendarButtons calendarButtons;

  public CalendarViewer() {

    // Set locale for testing purposes
    Locale.setDefault(Locale.ITALY);

    // Create a calendar initialized with the 
    // current date/time in the default locale
    gCalendar = new GregorianCalendar();

    // Adjust the date of this calendar to  
    // the first of the current month
    int date = gCalendar.get(Calendar.DATE);
    gCalendar.add(Calendar.DATE, -(date - 1));

    // Create GUI
    setLayout(new BorderLayout());
    calendarLabel = new CalendarLabel(this);
    add("North", calendarLabel);
    calendarCanvas = new CalendarCanvas(this);
    add("Center", calendarCanvas);
    calendarButtons = new CalendarButtons(this);
    add("South", calendarButtons);
  }

  public Dimension getPreferredSize() {
    return new Dimension(400, 300);
  }

  public GregorianCalendar getGCalendar() {
    return gCalendar;
  }

  public void next() {
    int month = gCalendar.get(Calendar.MONTH);
    int year = gCalendar.get(Calendar.YEAR);
    int daysInMonths[] = { 31, 28, 31, 30, 31, 30, 
      31, 31, 30, 31, 30, 31 };
    daysInMonths[1] += 
      gCalendar.isLeapYear(year) ? 1 : 0;
    gCalendar.add(Calendar.DATE, daysInMonths[month]);
    dateChanged();
  }

  public void previous() {
    int month = gCalendar.get(Calendar.MONTH);
    int year = gCalendar.get(Calendar.YEAR);
    int daysInMonths[] = { 31, 28, 31, 30, 31, 30, 
      31, 31, 30, 31, 30, 31 };
    daysInMonths[1] += 
      gCalendar.isLeapYear(year) ? 1 : 0;
    if(--month < 0) {
      month = 11;
    }
    gCalendar.add(Calendar.DATE, -daysInMonths[month]);
    dateChanged();
  }

  private void dateChanged() {
    calendarLabel.repaint();
    calendarCanvas.repaint();
  }
}