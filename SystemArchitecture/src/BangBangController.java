import com.cyberbotics.webots.controller.DifferentialWheels;

public class BangBangController extends DifferentialWheels {
  public static final int STEP_TIME = 16;
  private ControllerManager _controllerManager;
  
  public BangBangController(){
    _controllerManager = new ControllerManager(this);
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
    BangBangController controller = new BangBangController();
    controller.run();
  }
}
