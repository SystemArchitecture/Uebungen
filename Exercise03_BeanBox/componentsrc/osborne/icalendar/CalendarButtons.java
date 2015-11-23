package icalendar;
import java.awt.*;
import java.awt.event.*;

public class CalendarButtons extends Panel 
implements ActionListener {
  private CalendarViewer cv;
  private Button previous, next;

  public CalendarButtons(CalendarViewer cv) {
    this.cv = cv;
    previous = new Button("-");
    previous.addActionListener(this);
    add(previous);
    next = new Button("+");
    next.addActionListener(this);
    add(next);
  }

  public void actionPerformed(ActionEvent ae) {
    if(ae.getSource() == previous) {
      cv.previous();
    }
    else {
      cv.next();
    }
  }
}