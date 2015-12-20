/**
 * WheelsController for driving into all directions and setting speed.
 */

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
		setSpeed(_maxSpeed, MIN_SPEED);
	}

	public void driveForward() {
		setSpeed(_maxSpeed, _maxSpeed);
	}

	public void driveLeft() {
		setSpeed(MIN_SPEED, _maxSpeed);
	}

	public void driveStop() {
		setSpeed(MIN_SPEED, MIN_SPEED);
	}
}
