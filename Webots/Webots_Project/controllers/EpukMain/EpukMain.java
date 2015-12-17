import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.Robot;

public class EpukMain extends Robot {
  public static final int STEP_TIME = 16;
  private ControllerManager _controllerManager;
  private DifferentialWheels _DifferentialWheels;
  
  public EpukMain(){
	_DifferentialWheels = new DifferentialWheels();
    _controllerManager = new ControllerManager(_DifferentialWheels);
  }

  public void run() {
    //_controllerManager.addController(new ControllerBoundaries(ControllerType.BANGBANG, 200, 1000));
    //_controllerManager.addController(new ControllerLight(ControllerType.PROPORTIONAL, 1000));
    //_controllerManager.addController(new ControllerLightStop(ControllerType.PROPORTIONAL, 1000, 200));
    _controllerManager.addController(new ControllerBalanceBall(ControllerType.PROPORTIONAL, 1000, 4000));
    //_controllerManager.addController(new ControllerWallLeft(ControllerType.BANGBANG, 1000, 500));
    
    while (step(STEP_TIME) != -1) {
      _controllerManager.runAll();
    }
  }
  
  public static void main(String[] args) {
    EpukMain controller = new EpukMain();
    controller.run();
  }
}
