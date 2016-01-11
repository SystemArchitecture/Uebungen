/*
 * Motion Manager
 * Singleton, creates/initializes actors.
 */

import java.util.HashMap;

import com.cyberbotics.webots.controller.DifferentialWheels;

public class MotionManager {
	private HashMap<ActorTypes, IActor> _actors;
	private DifferentialWheels _differentialWheels;

	public MotionManager() {
		_actors = new HashMap<>();
	}

	private static MotionManager _instance;

	public static MotionManager getInstance() {
		if (_instance == null) {
			_instance = new MotionManager();
		}

		return _instance;
	}

	private boolean _isInitialized;

	public void init(DifferentialWheels differentialWheels) {
		if (!_isInitialized) {
			_differentialWheels = differentialWheels;
			_isInitialized = true;
		}
	}

	private IActor createActor(ActorTypes type) {
		IActor newActor = null;

		if (type.equals(ActorTypes.DIFFERENTIAL_WHEELS)) {
			newActor = new WheelsController(_differentialWheels);
		} else {
			throw new IllegalArgumentException("Unknown Actor: " + type);
		}

		return newActor;
	}

	public IActor getActor(ActorTypes type) {
		if (!_actors.containsKey(type)) {
			_actors.put(type, createActor(type));
		}

		return _actors.get(type);
	}
}
