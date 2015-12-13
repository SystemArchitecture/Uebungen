
import java.util.HashMap;

import com.cyberbotics.webots.controller.DifferentialWheels;

public class MotionManager {
	public static Actor _types;
	private HashMap<Actor, IActor> _actors;
	private DifferentialWheels _differentialWheels;

	public MotionManager(DifferentialWheels differentialWheels) {
		_differentialWheels = differentialWheels;
		_actors = new HashMap<>();
	}

	public void initialize(Actor actor) {
		if (!isActive(actor)) {
			if (actor.equals(Actor.DIFFERENTIAL_WHEELS)) {
				_actors.put(actor, new WheelsController(_differentialWheels));
			} else {
				throw new IllegalArgumentException("Unknown Actor: " + actor);
			}
		}
	}

	public boolean isActive(Actor actor) {
		return _actors.containsKey(actor);
	}

	public IActor getActor(Actor actor) {
		return _actors.get(actor);
	}
}
