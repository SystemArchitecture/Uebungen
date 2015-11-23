package version;
import java.awt.*;
import java.io.*;

class Box implements Serializable {
  private int width, height, depth;

  public Box(int width, int height, int depth) {
    this.width = width;
    this.height = height;
    this.depth = depth;
  }

  public String toString() {
    return "[" + width + "," + height + "," + depth + "]";
  }
}
