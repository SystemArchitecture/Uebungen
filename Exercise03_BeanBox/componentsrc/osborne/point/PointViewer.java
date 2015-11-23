package point;
import java.awt.*;

public class PointViewer extends Canvas {
  private Point point;

  public PointViewer() {
    point = new Point(0, 0);
    setSize(200, 200);
  }

  public Point getPoint() {
    return point;
  }

  public void setPoint(Point point) {
    this.point = point;
    repaint();
  }

  public void paint(Graphics g) {
    Dimension d = getSize();
    g.drawRect(0, 0, d.width - 1, d.height - 1);
    g.fillRect(point.x - 3, point.y - 3, 6, 6);
  }
}
    