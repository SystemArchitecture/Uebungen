package slidesapplet;
import java.applet.*;
import java.awt.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class SlidesApplet 
extends Applet implements Runnable {
  private final static int NUMIMAGES = 4;
  private int interval;
  private transient Vector images;
  private transient Thread thread;
  private transient int index;

  public void init() {
    interval = 1000;
    images = new Vector();
    loadImages();
    thread = new Thread(this);
    thread.start();
    setSize(299, 280);
  }

  public int getInterval() {
    return interval;
  }

  public void setInterval(int interval) {
    this.interval = interval;
  }

  private void loadImages() {
    try {
      for(int i = 1; i <= NUMIMAGES; i++) {
        String name = "slidesapplet/slide" + i + ".jpg";
        URL url = new URL(getCodeBase(), name);
        Image image = getImage(url);
        images.addElement(image);
      }
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }   
  }

  public void paint(Graphics g) {
    g.drawImage((Image)images.elementAt(index), 0, 0, this);
  }

  public void run() {
    try {
      while(true) {
        repaint();
        index = (index + 1) % NUMIMAGES;
        Thread.sleep(interval);
      }
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  private void readObject(ObjectInputStream ois) 
  throws IOException, ClassNotFoundException {
    try {
       ois.defaultReadObject();
       images = new Vector();
       loadImages();
       thread = new Thread(this);
       thread.start();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
}
