/**
 * Manages controllers and the motion- / sensor-manager.
 * First add controllers with addControler method, then call runAll in control loop.
 */

import java.util.HashSet;
import java.util.Iterator;

import com.cyberbotics.webots.controller.DifferentialWheels;

public class ControllerManager {
	private HashSet<Controller> _controller;
	private DifferentialWheels _differentialWheels;
	private MotionManager _motionManager;
	private SensorManager _sensorManager;

	public ControllerManager(DifferentialWheels differentialWheels) {
		_controller = new HashSet<>();
		_differentialWheels = differentialWheels;
		_motionManager = new MotionManager(_differentialWheels);
		_sensorManager = new SensorManager(_differentialWheels);
	}
	
	public void addController(Controller controller) {
		controller.setMotionManager(_motionManager);
		controller.setSensorManager(_sensorManager);
		controller.initializeSensorsAndActors();
		_controller.add(controller);
	}

	public void runAll(){
		// TODO hierarchical controller calls
		Iterator<Controller> controllers = _controller.iterator();
		while(controllers.hasNext()){
			controllers.next().control();
		}
	}
}
