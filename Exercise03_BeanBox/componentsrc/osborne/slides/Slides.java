package slides;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import  java.util.*;

public class Slides extends Canvas implements Runnable {
  private int interval;
  private transient Vector images;
  private transient Thread thread;
  private transient int index;
  private transient int numimages;

  public Slides() {
    interval = 1000;
    images = new Vector();
    loadImages();
    thread = new Thread(this);
    thread.start();
    setSize(300, 300);
  }

  public int getInterval() {
    return interval;
  }

  public void setInterval(int interval) {
    this.interval = interval;
  }

  private void loadImages() {
    try {
      InputStream is;
      is = getClass().getResourceAsStream("slides.txt");
      BufferedReader br;
      br = new BufferedReader(new InputStreamReader(is));
      String line;
      while((line = br.readLine()) != null) {
        URL url = getClass().getResource(line);
        ImageProducer ip = (ImageProducer)url.getContent();
        Image image = createImage(ip);
        images.addElement(image);
        ++numimages;
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
        index = (index + 1) % numimages;
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
