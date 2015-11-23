package beantool;

public class JarClassLoader extends ClassLoader {
  public static JarClassLoader singleton = 
    new JarClassLoader();

  protected Class loadClass(String clsName, boolean resolve)
  throws ClassNotFoundException {

    // Check the System class loader
    Class cls;
    try {
      cls = super.findSystemClass(clsName);
      return cls;
    }
    catch(ClassNotFoundException ex) {
    }
    catch(NoClassDefFoundError err) {
    }

    // Check if this class has already
    // been loaded
    cls = findLoadedClass(clsName);
    if(cls != null) {
      return cls;
    }

    // Get the bytecodes for this class
    byte buffer[] = (byte[])Jar.getData(clsName);
    if(buffer == null) {
      throw new ClassNotFoundException();
    }

    // Parse the data
    cls = defineClass(clsName, buffer, 0, buffer.length);
    if(cls == null) {
      throw new ClassFormatError();
    }

    // Resolve the class if necessary
    if(resolve) {
      resolveClass(cls);
    }

    // Return the class
    return cls;
  }
}