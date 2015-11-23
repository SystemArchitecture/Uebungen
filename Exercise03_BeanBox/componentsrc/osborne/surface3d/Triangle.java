package surface3d;
import java.awt.*;

public class Triangle {
  public Point3D p1, p2, p3;

  public Triangle(Point3D p1, Point3D p2, Point3D p3) {
    this.p1 = p1;
    this.p2 = p2;
    this.p3 = p3;
  }

  public Color computeColor(Point3D lightSource) {

    // Compute unit vector normal to triangle
    double x21 = p2.x - p1.x;
    double y21 = p2.y - p1.y;
    double z21 = p2.z - p1.z;
    double x31 = p3.x - p1.x;
    double y31 = p3.y - p1.y;
    double z31 = p3.z - p1.z;
    double xn = y21 * z31 - z21 * y31;
    double yn = z21 * x31 - x21 * z31;
    double zn = x21 * y31 - y21 * x31;
    double l = Math.sqrt(xn * xn + yn * yn + zn * zn);
    xn /= l;
    yn /= l;
    zn /= l;

    // Compute unit vector from p1 to the lightSource
    double xs = lightSource.x - p1.x;
    double ys = lightSource.y - p1.y;
    double zs = lightSource.z - p1.z;
    l = Math.sqrt(xs * xs + ys * ys + zs * zs);
    xs /= l;
    ys /= l;
    zs /= l;

    // Compute dot product of these two vectors
    double product = xn * xs + yn * ys + zn * zs;

    // Select color based on dot product
    int i = (int)(127 * ((product + 1)/2) + 128);
    return new Color(i, i, i);
  }
}