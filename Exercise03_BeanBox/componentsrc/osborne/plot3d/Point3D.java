package plot3d;

public class Point3D {
  public double x, y, z;

  public Point3D(double x, double y, double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }

  public void rotateZ(double theta) {
    double newx = x * Math.cos(theta) - y * Math.sin(theta);
    double newy = x * Math.sin(theta) + y * Math.cos(theta);
    x = newx;
    y = newy;
  }
}