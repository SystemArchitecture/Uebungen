

import java.util.HashSet;
import java.util.Iterator;

import com.cyberbotics.webots.controller.DifferentialWheels;

public class ControllerManager {
	private HashSet<IController> _controller;
	private ControllerType _controllerType;
	private DifferentialWheels _differentialWheels;
	private MotionManager _motionManager;
	private SensorManager _sensorManager;

	public ControllerManager(ControllerType type, DifferentialWheels differentialWheels) {
		_controller = new HashSet<>();
		_controllerType = type;
		_differentialWheels = differentialWheels;
		_motionManager = new MotionManager(_differentialWheels);
		_sensorManager = new SensorManager(_differentialWheels);
	}
	
	public void addController(IController controller) {
		controller.setMotionManager(_motionManager);
		controller.setSensorManager(_sensorManager);
		controller.init();
		_controller.add(controller);
	}

	public void runAll(){
		Iterator<IController> it = _controller.iterator();
		while(it.hasNext()){
			it.next().control(_controllerType);
		}
	}
}
