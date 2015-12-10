

import java.util.HashMap;

import com.cyberbotics.webots.controller.DifferentialWheels;

public class ControllerManager {
	private HashMap<String, IController> _controller;
	private ControllerType _controllerType;
	private DifferentialWheels _differentialWheels;
	private MotionManager _motionManager;
	private SensorManager _sensorManager;

	public ControllerManager(ControllerType type, DifferentialWheels differentialWheels) {
		_controller = new HashMap<>();
		_controllerType = type;
		_differentialWheels = differentialWheels;
		_motionManager = new MotionManager(_differentialWheels);
		_sensorManager = new SensorManager(_differentialWheels);
	}
	
	public void addController(IController controller) {
		controller.setMotionManager(_motionManager);
		controller.setSensorManager(_sensorManager);
		controller.init();
		_controller.put(controller.getClass().getName(), controller);
	}

	public void runAll(){
		for(IController c : _controller.values()){
			c.control(_controllerType);
		}
	}
}
