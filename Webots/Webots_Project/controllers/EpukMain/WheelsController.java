

import com.cyberbotics.webots.controller.DifferentialWheels;

public class WheelsController implements IActor {
	private DifferentialWheels _differantialWheels;
	private final int MIN_SPEED = 0;
	private int _maxSpeed;

	public WheelsController(DifferentialWheels differentialWheels) {
		_differantialWheels = differentialWheels;
		_maxSpeed = 1000;
	}

	public void setSpeed(int left, int right){
		_differantialWheels.setSpeed(left, right);
	}

	public int getMaxSpeed() {
		return _maxSpeed;
	}

	public void setMaxSpeed(int maxSpeed) {
		_maxSpeed = maxSpeed;
	}
	
	public void driveRight() {
		System.out.println("drive right");
		_differantialWheels.setSpeed(_maxSpeed, MIN_SPEED);
	}

	public void driveForward() {
		_differantialWheels.setSpeed(_maxSpeed, _maxSpeed);
	}

	public void driveLeft() {
		System.out.println("drive left");
		_differantialWheels.setSpeed(MIN_SPEED, _maxSpeed);
	}

	public void driveStop() {
		_differantialWheels.setSpeed(MIN_SPEED, MIN_SPEED);
	}
}
