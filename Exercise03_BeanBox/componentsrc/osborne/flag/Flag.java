package flag;
import java.applet.*;
import java.awt.*;
import java.awt.image.*;
import java.io.*;
import java.net.*;
import java.util.*;

public class Flag extends Applet {
  private transient Image image;

  public void init() {

    // Set locale for testing purposes
    Locale.setDefault(Locale.JAPAN);

    // Get the default locale
    Locale locale = Locale.getDefault();

    // Get the resource bundle
    String name = "flag.CountryResources";
    ResourceBundle rb =
      ResourceBundle.getBundle(name, locale);

    // Get name of the flag file
    String flagFile = rb.getString("FlagFile");
    
    // Get the resource
    URL flagURL = getClass().getResource(flagFile);

    // Get the image
    try{
      image = 
        createImage((ImageProducer)flagURL.getContent());
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }

    // Set size of the Bean
    setSize(250, 100);
  }

  public void paint(Graphics g) {
    g.drawImage(image, 0, 0, this);
  }

  private void readObject(ObjectInputStream ois) 
  throws ClassNotFoundException, IOException {
    ois.defaultReadObject();
    init();
  }
}