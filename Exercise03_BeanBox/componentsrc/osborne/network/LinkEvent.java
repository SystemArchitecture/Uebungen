package network;
import java.util.*;

public class LinkEvent extends EventObject {
  private int linkId;
  private boolean operational;

  public LinkEvent(NetworkServer source, 
  int linkId, boolean operational) {
    super(source);
    this.linkId = linkId;
    this.operational = operational;
  }

  public int getLinkId() {
    return linkId;
  }

  public boolean getOperational() {
    return operational;
  }
}