package surface3d;
import java.awt.*;
import java.applet.*;
import java.io.*;
import java.util.*;

public class Surface3D extends Applet 
implements Runnable {
  private final static int WIDTH = 300;
  private final static int HEIGHT = 250;
  private final static double XMAX = 100;
  private final static double DELTAX = 20;
  private final static double YMAX = 100;
  private final static double DELTAY = 20;
  private final static double ZMAX = 100;
  private final static int NTRIANGLES = 
    (int)(4 * (2 * XMAX/DELTAX) * (2 * YMAX/DELTAY));
  private transient double theta;
  private transient Thread thread;

  public void init() {
    setSize(WIDTH, HEIGHT);
    theta = 0;
    thread = new Thread(this);
    thread.start();
  }

  public void run() {
    try {
      while(true) {
        Thread.sleep(200);
        theta += Math.PI/180;
        repaint();
      }
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }

  public void update(Graphics g) {
    paint(g);
  }

  public void paint(Graphics g) {

    // Generate triangles
    Triangle triangles[] = new Triangle[NTRIANGLES];
    Triangle t;
    int index = 0;
    for(double y = YMAX; y > -YMAX; y -= DELTAY) {
      for(double x = XMAX; x > -XMAX; x -= DELTAX) {
        Point3D p1, p2, p3, p4, p5;
        double z;
        z = f(x, y);
        p1 = new Point3D(x, y, z);
        z = f(x - DELTAX, y);
        p2 = new Point3D(x - DELTAX, y, z);
        z = f(x - DELTAX/2, y - DELTAY/2);
        p3 = new Point3D(x - DELTAX/2, y - DELTAY/2, z);
        z = f(x - DELTAX, y - DELTAY);
        p4 = new Point3D(x - DELTAX, y - DELTAY, z);
        z = f(x, y - DELTAY);
        p5 = new Point3D(x, y - DELTAY, z);
        p1.rotateZ(theta);
        p2.rotateZ(theta);
        p3.rotateZ(theta);
        p4.rotateZ(theta);
        p5.rotateZ(theta);
        t = new Triangle(p1, p2, p3);
        triangles[index++] = t;
        t = new Triangle(p2, p4, p3);
        triangles[index++] = t;
        t = new Triangle(p3, p4, p5);
        triangles[index++] = t;
        t = new Triangle(p1, p3, p5);
        triangles[index++] = t;
      }
    }

    // Sort triangles according to distance from 
    // viewport center
    Point3D viewportCenter = new Point3D(0, -150, 0);
    Triangle sortedTriangles[] = 
      sortTriangles(triangles, viewportCenter);

    // Draw triangles in background buffer
    Image buffer = createImage(WIDTH, HEIGHT);
    Graphics bufferg = buffer.getGraphics();

     // Create white background
    bufferg.setColor(Color.white);
    bufferg.fillRect(0, 0, WIDTH - 1, HEIGHT - 1);

    // Draw triangles in background buffer
    Point3D lightSource = new Point3D(0, -150, 0);
    drawTriangles(bufferg, sortedTriangles, lightSource);

    // Copy background buffer to foreground
    g.drawImage(buffer, 0, 0, null);
  }

  private double f(double x, double y) {
    double exponent = -(x * x + y * y)/5000;
    return 100 * Math.pow(Math.E, exponent);
  }

  private Triangle[] sortTriangles(Triangle triangles[], 
  Point3D viewportCenter) {
    double distance[] = new double[NTRIANGLES];
    for(int i = 0; i < NTRIANGLES; i++) {
      distance[i] = 
        computeDistance(triangles[i], viewportCenter);
    }
    Triangle sortedTriangles[] = new Triangle[NTRIANGLES];
    for(int i = 0; i < NTRIANGLES; i++) {
      double d = -1;
      int k = 0;
      for(int j = 0; j < NTRIANGLES; j++) {
        if(distance[j] > d) {
          d = distance[j];
          k = j;
        }
      }
      sortedTriangles[i] = triangles[k];
      distance[k] = -1;
    }
    return sortedTriangles;
  }

  private double computeDistance(Triangle t, Point3D p) {
    double xc = (t.p1.x + t.p2.x + t.p3.x)/3;
    double yc = (t.p1.y + t.p2.y + t.p3.y)/3;
    double zc = (t.p1.z + t.p2.z + t.p3.z)/3;
    double xp = p.x;
    double yp = p.y;
    double zp = p.z;
    double dx = xc - xp;
    double dy = yc - yp;
    double dz = zc - zp;
    return Math.sqrt(dx * dx + dy * dy + dz * dz);
  }

  private void drawTriangles(Graphics g, 
  Triangle sortedTriangles[], Point3D lightSource) {
    Dimension d = getSize();
    int w = d.width;
    int h = d.height;
    int xpoints[] = new int[3];
    int ypoints[] = new int[3];
    for(int i = 0; i < NTRIANGLES; i++) {
      Triangle t = sortedTriangles[i];
      xpoints[0] = (int)(w/2 + t.p1.x);
      ypoints[0] = (int)(h/2 - t.p1.z);
      xpoints[1] = (int)(w/2 + t.p2.x);
      ypoints[1] = (int)(h/2 - t.p2.z);
      xpoints[2] = (int)(w/2 + t.p3.x);
      ypoints[2] = (int)(h/2 - t.p3.z);
      g.setColor(t.computeColor(lightSource));
      g.fillPolygon(xpoints, ypoints, 3);
    }
  }

  private void readObject(ObjectInputStream ois) 
  throws IOException, ClassNotFoundException {
    try {
      ois.defaultReadObject();
      thread = new Thread(this);
      thread.start();
    }
    catch(Exception ex) {
      ex.printStackTrace();
    }
  }
}
    