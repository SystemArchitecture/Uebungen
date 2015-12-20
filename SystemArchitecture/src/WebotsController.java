// File:          WebotsController.java 
// Author:        Daniel Griesser / Simon Angerer

import com.cyberbotics.webots.controller.DifferentialWheels;

// Here is the main class of your controller.
// This class defines how to initialize and how to run your controller.
// Note that this class derives Robot and so inherits all its functions
public class WebotsController extends DifferentialWheels {
  public static final int STEP_TIME = 16;
  private ControllerManager _controllerManager;
  
  private String _task = "1"; // 1 = proportional ,2 = bangbang
  private String _subtask = "a"; // a = light, b = light stop, c = balance ball, d = follow wall
  
  public WebotsController(){
    _controllerManager = new ControllerManager(this);
  }

  public void run() {
    ControllerType type = null;
    if(_task.equals("1")){
      type = ControllerType.PROPORTIONAL;
    } else if (_task.equals("2")) {
      type = ControllerType.BANGBANG;
    }
  
    Controller controller = null;
    if(_subtask.equals("a")){
      // ControllerLight(ControllerType, maxSpeed, speedFactor)
      controller = new ControllerLight(type, 1000, 1);
    } else if (_subtask.equals("b")){
      // ControllerLightStop(ControllerType, maxSpeed, speedFactor, maxDistanceSensor)
      controller = new ControllerLightStop(type, 1000, 1, 200);
    } else if (_subtask.equals("c")){
      // ControllerBalanceBall(ControllerType, maxSpeed, speedFactor)
      controller = new ControllerBalanceBall(type, 1000, 10);
    } else if (_subtask.equals("d")){
      // ControllerWallLeft(ControllerType, maxSpeed, speedFactor, maxDistance, minDistance)
      controller = new ControllerWallLeft(type, 1000, 30, 50, 60);
    }
  
    _controllerManager.addController(controller);
    
    while (step(STEP_TIME) != -1) {
      _controllerManager.runAll();
    }
  }
  
  public static void main(String[] args) {
    WebotsController controller = new WebotsController();
    controller.run();
  }
}
