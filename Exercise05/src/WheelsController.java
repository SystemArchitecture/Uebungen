

import com.cyberbotics.webots.controller.DifferentialWheels;

public class WheelsController implements IActor {
	private DifferentialWheels _differantialWheels;

	public WheelsController(DifferentialWheels differentialWheels) {
		_differantialWheels = differentialWheels;
	}

	public void setSpeed(int left, int right){
		_differantialWheels.setSpeed(left, right);
	}
}
