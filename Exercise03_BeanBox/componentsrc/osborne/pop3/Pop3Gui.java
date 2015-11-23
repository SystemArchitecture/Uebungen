package pop3;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.reflect.*;
import java.util.*;

public class Pop3Gui extends Panel 
implements ActionListener, Pop3Source, TextListener {
  private Vector listeners;
  private TextField userTf, passTf, resultTf;
  private TextArea messageTa;
  private Button connectButton, deleteButton, retrieveButton;
  private transient int msgId;

  public Pop3Gui() {

    // Create listeners vector
    listeners = new Vector();

    // Create GUI elements
    Label title = new Label("POP3 INTERFACE", Label.CENTER);
    Label userLabel = new Label("User:");
    userTf = new TextField(20);
    userTf.addTextListener(this);
    Label passLabel = new Label("Password:");
    passTf = new TextField(20);
    passTf.setEchoChar('*');
    passTf.addTextListener(this);
    connectButton = new Button("Connect   ");
    connectButton.addActionListener(this);
    Label messageLabel = new Label("Message:");
    messageTa = new TextArea(5, 30);
    retrieveButton = new Button("Retrieve");
    retrieveButton.addActionListener(this);
    deleteButton = new Button("Delete");
    deleteButton.addActionListener(this);
    resultTf = new TextField(20);

    // Create and set layout manager
    GridBagLayout gbl = new GridBagLayout();
    setLayout(gbl);

    // Create and initialize GridBagConstraints object
    GridBagConstraints gbc = new GridBagConstraints();
    gbc.gridx = 0;
    gbc.gridwidth = 2;
    gbc.gridheight = 1;
    gbc.insets = new Insets(5, 5, 5, 5);

    // Add title to the GUI
    gbc.gridy = 0;
    gbl.setConstraints(title, gbc);
    add(title);

    // Add user label to the GUI
    gbc.gridy = 1;
    gbl.setConstraints(userLabel, gbc);
    add(userLabel);
 
    // Add user text field to the GUI
    gbc.gridy = 2;
    gbl.setConstraints(userTf, gbc);
    add(userTf);

    // Add password label to the GUI
    gbc.gridy = 3;
    gbl.setConstraints(passLabel, gbc);
    add(passLabel);

    // Add password text field to the GUI
    gbc.gridy = 4;
    gbl.setConstraints(passTf, gbc);
    add(passTf);

    // Add connect button to the GUI
    gbc.gridy = 5;
    gbl.setConstraints(connectButton, gbc);
    add(connectButton);

    // Add message label area to the GUI
    gbc.gridy = 6;
    gbl.setConstraints(messageLabel, gbc);
    add(messageLabel);

    // Add message text area to the GUI
    gbc.gridy = 7;
    gbl.setConstraints(messageTa, gbc);
    add(messageTa);

    // Add retrieve button to the GUI
    gbc.gridy = 8;
    gbc.gridwidth = 1;
    gbl.setConstraints(retrieveButton, gbc);
    add(retrieveButton);

    // Add delete button to the GUI
    gbc.gridx = 1;
    gbc.gridy = 8;
    gbc.gridwidth = 1;
    gbc.anchor = GridBagConstraints.EAST;
    gbl.setConstraints(deleteButton, gbc);
    add(deleteButton);

    // Add result text field to the GUI
    gbc.gridx = 0;
    gbc.gridy = 9;
    gbc.gridwidth = 2;
    gbc.anchor = GridBagConstraints.CENTER;
    gbl.setConstraints(resultTf, gbc);
    add(resultTf);
  }

  public void paint(Graphics g) {
    Dimension d = getSize();
    g.drawRect(0, 0, d.width - 1, d.height - 1);
  }

  public void textValueChanged(TextEvent te) {
    resultTf.setText("");
  }

  public void actionPerformed(ActionEvent ae) {
    messageTa.selectAll();
    messageTa.replaceRange("", 0, messageTa.getSelectionEnd());
    resultTf.setText("");
    Button button = (Button)ae.getSource();
    if(button == connectButton) {
      connectOrDisconnect();
    }
    else if(button == retrieveButton) {
      retrieve();
    }
    else if(button == deleteButton) {
      delete();
    }
  }

  private void connectOrDisconnect() {
    // Connect or disconnect based on button label
    if(connectButton.getLabel().equals("Connect   ")) {
      String user = userTf.getText();
      String pass = passTf.getText();
      Pop3Event pop3e = new Pop3Event(this, user, pass);
      fireMethod(pop3e, "connect");
    }
    else {
      Pop3Event pop3e = new Pop3Event(this);
      fireMethod(pop3e, "disconnect");
    }
  }

  private void retrieve() {
    Pop3Event pop3e = new Pop3Event(this, ++msgId);
    fireMethod(pop3e, "retr");
  }

  private void delete() {
    Pop3Event pop3e = new Pop3Event(this, msgId);
    fireMethod(pop3e, "dele");
  }

  public void addPop3Listener(Pop3Listener pop3l) {
    listeners.addElement(pop3l);
  }

  public void removePop3Listener(Pop3Listener pop3l) {
    listeners.removeElement(pop3l);
  }

  public void fireMethod(Pop3Event p3e, String mname) {   
 
    // Clone the listeners vector in a synchronized block
    Vector v;
    synchronized(this) {
      v = (Vector)listeners.clone();
    }

    // Obtain a Method object for the method named 'mname'
    // in the Pop3Listener interface which accepts one 
    // parameter of type Pop3Event
    Class cls = Pop3Listener.class;
    Class pTypes[] = { Pop3Event.class };
    Method method;
    try {
      method = cls.getMethod(mname, pTypes);
    }
    catch(Exception ex) {
      return;
    }

    // Initialize an array with the arguments for 
    // this method
    Object args[] = { p3e };

    // Invoke the method for all listeners
    for(int i = 0; i < v.size(); i++) {
      Pop3Listener p3l = (Pop3Listener)v.elementAt(i);
      try {
        method.invoke(p3l, args);
      }
      catch(Exception ex) {
      }
    }
  }

  public void respond(int code) {
    String str = "Pop3 Problem";
    if(code == Pop3Client.FAILEDCONNECT) {
      str = "Failed Connect";
      connectButton.setLabel("Connect   ");
    }
    else if(code == Pop3Client.FAILEDLOGIN) {
      str = "Failed Login";
      connectButton.setLabel("Connect   ");
    }
    else if(code == Pop3Client.CONNECTOK) {
      str = "Connected";
      connectButton.setLabel("Disconnect");
      msgId = 0;
    }
    else if(code == Pop3Client.RETRIEVEOK) {
      str = "Retrieved";
    }
    else if(code == Pop3Client.BADMSGINDEX) {
      str = "No message";
    }
    else if(code == Pop3Client.MSGDELETED) {
      str = "Message Deleted";
    }
    else if(code == Pop3Client.FAILEDRETRIEVE) {
      str = "Failed Retrieve";
    }
    else if(code == Pop3Client.DELETEOK) {
      str = "Deleted";
    }
    else if(code == Pop3Client.FAILEDDELETE) {
      str = "Failed Delete";
    }
    else if(code == Pop3Client.QUITOK) {
      str = "Quit";
      connectButton.setLabel("Connect   ");
    }
    else if(code == Pop3Client.FAILEDQUIT) {
      str = "Failed Quit";
      connectButton.setLabel("Connect   ");
    }
    else if(code == Pop3Client.TIMEOUT) {
      str = "Timeout";
      connectButton.setLabel("Connect   ");
    }
    resultTf.setText(str);
  }

  public void message(String s) {
    messageTa.selectAll();
    messageTa.replaceRange(s, 0, messageTa.getSelectionEnd());
  }

  private void readObject(ObjectInputStream ois) 
  throws ClassNotFoundException, IOException {

    // Read non-static and non-transient information from stream
    ois.defaultReadObject();

    // Clear all information from the GUI elements
    userTf.setText("");
    passTf.setText("");
    connectButton.setLabel("Connect   ");
    messageTa.selectAll();
    messageTa.replaceRange("", 0, messageTa.getSelectionEnd());
    resultTf.setText("");
  }
}