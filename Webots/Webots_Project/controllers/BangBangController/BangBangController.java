// File:          BangBangController.java
// Date:          
// Description:   
// Author:        
// Modifications: 

// You may need to add other webots classes such as
//  import com.cyberbotics.webots.controller.DistanceSensor;
//  import com.cyberbotics.webots.controller.LED;
// or more simply:
//  import com.cyberbotics.webots.controller.*;
import com.cyberbotics.webots.controller.DifferentialWheels;

// Here is the main class of your controller.
// This class defines how to initialize and how to run your controller.
// Note that this class derives Robot and so inherits all its functions
public class BangBangController extends DifferentialWheels {
  public static final int STEP_TIME = 16;
  private ControllerManager _controllerManager;
  
  public BangBangController(){
    _controllerManager = new ControllerManager(ControllerType.BANGBANG, this);
  }

  public void run() {
    _controllerManager.addController(new ControllerBoundaries(200, 0, 1000));
    _controllerManager.addController(new ControllerLight(0, 1000));
    
    while (step(STEP_TIME) != -1) {
      _controllerManager.runAll();
    }
  }
  
  public static void main(String[] args) {
    BangBangController controller = new BangBangController();
    controller.run();
  }
}
