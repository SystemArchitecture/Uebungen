package icalendar;
import java.awt.*;
import java.text.*;
import java.util.*;

public class CalendarLabel extends Label {
  private CalendarViewer cv;

  public CalendarLabel(CalendarViewer cv) {
    this.cv = cv;
    setAlignment(Label.CENTER);
  }

  public void paint(Graphics g) {
    // Format month and year information
    SimpleDateFormat sdf = new SimpleDateFormat("MMMM yyyy");
    GregorianCalendar gc = cv.getGCalendar();
    String s = sdf.format(gc.getTime());
    setText(s);
  }
}