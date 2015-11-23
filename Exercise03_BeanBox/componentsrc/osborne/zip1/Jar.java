package zip1;
import java.io.*;
import java.util.*;
import java.util.zip.*;

public class Jar {
  private static Hashtable data = new Hashtable();
  private String filename;

  public static void putData(String clsName, byte[] buffer) {
    data.put(clsName, buffer);
  }

  public static Object getData(String clsName) {
    return data.get(clsName);
  }

  public static void process() {
    try {
      char c = File.separatorChar;
      File dir = new File("zip1" + c + "jars");
      String entries[] = dir.list();
      for(int i = 0; i < entries.length; i++) {
        if(entries[i].endsWith(".jar")) {
          new Jar("zip1" + c + "jars" + c + entries[i]);
        }
      }
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public Jar(String filename) {
    // Read and process the .class entries in the JAR file
    this.filename = filename;
    try {
      FileInputStream fis = new FileInputStream(filename);
      ZipInputStream zis = new ZipInputStream(fis);
      ZipEntry ze = null;
      while((ze = zis.getNextEntry()) != null) {
        String name = ze.getName();
        if(name.endsWith(".class")) {
          processClassFile(name, zis);
        }
      }
      zis.close();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  private void processClassFile(String name1, 
  ZipInputStream zis) {

    // Determine class name
    String name2 = name1.replace('/','.');
    int i = name2.indexOf(".class");
    if(i != -1) {
      name2 = name2.substring(0, i);
    }

    try {

      // Read bytecodes from the zip input stream
      ByteArrayOutputStream baos = 
        new ByteArrayOutputStream();
      for(;;) {
        byte block[] = new byte[1024];
        int len = zis.read(block);
        if(len < 0) {
          break;
        }
        baos.write(block, 0, len);
      }
      byte buffer[] = baos.toByteArray();

      // Save these bytecodes 
      putData(name2, buffer);
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
}

        
        