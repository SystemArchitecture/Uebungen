

import com.cyberbotics.webots.controller.DifferentialWheels;

public class WheelsAdapter implements IActor {
	private DifferentialWheels _differantialWheels;

	public WheelsAdapter(DifferentialWheels differentialWheels) {
		_differantialWheels = differentialWheels;
	}

	public void setSpeed(int left, int right){
		_differantialWheels.setSpeed(left, right);
	}
}
