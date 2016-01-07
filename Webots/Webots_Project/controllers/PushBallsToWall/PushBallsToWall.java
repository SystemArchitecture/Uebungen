import com.cyberbotics.webots.controller.DifferentialWheels;

public class PushBallsToWall extends DifferentialWheels {
	
	private static final int STEP_TIME = 16;
	private SubSumptionControllerManager _controllerManager;
	
	public PushBallsToWall() {
		super();
		SensorManager.getInstance().init(this);
		MotionManager.getInstance().init(this);
		_controllerManager = new SubSumptionControllerManager();
		
		//_controllerManager.addController(new AvoidWallSubSumptionController());
		_controllerManager.addController(new BalanceBallSubSumptionController());
		_controllerManager.addController(new SearchBallSubSumptionController());
		//_controllerManager.addController(new BlankController());
	}

	public void run() {
		while (step(STEP_TIME) != -1) {
			SensorManager.getInstance().updateValues();
			
			try {
				_controllerManager.runController();
			} catch (NoControllerMeetsActivationContitionException e) {
				e.printStackTrace();
				break;
			}
		};
		System.out.println("Close");
	}

	public static void main(String[] args) {
		PushBallsToWall controller = new PushBallsToWall();
		controller.run();
	}
}
