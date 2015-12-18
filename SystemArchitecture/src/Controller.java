/**
 * Abstract Controller Class Stores the Motion- and SensorManager, manages the
 * different ControllerTypes and the proportional controller calculation.
 * 
 * @author Daniel
 *
 */
public abstract class Controller {
	private ControllerType _type;
	protected MotionManager _motionManager;
	protected SensorManager _sensorManager;
	protected int _maxSpeed;

	public Controller(ControllerType type, int maxSpeed) {
		_type = type;
		_maxSpeed = maxSpeed;
	}

	public void control() {
		if (isBangBang()) {
			controlBangBang();
		} else if (isProportional()) {
			controlProportional();
		} else {
			throw new UnsupportedOperationException();
		}
	}

	private boolean isProportional() {
		return _type.equals(ControllerType.PROPORTIONAL);
	}

	private boolean isBangBang() {
		return _type.equals(ControllerType.BANGBANG);
	}

	protected abstract void controlBangBang();

	protected void controlProportional() {

		double[] resultVector = MatrixUtil.multiply(getControlMatrix(), getSensorArray());

		int speedLeftWheel = applySpeedFactor((int) resultVector[0]);
		speedLeftWheel = (speedLeftWheel > _maxSpeed) ? _maxSpeed : speedLeftWheel;
		int speedRightWheel = applySpeedFactor((int) resultVector[1]);
		speedRightWheel = (speedRightWheel > _maxSpeed) ? _maxSpeed : speedRightWheel;

		WheelsController wheelsController = (WheelsController) _motionManager.getActor(Actor.DIFFERENTIAL_WHEELS);
		wheelsController.setSpeed(speedLeftWheel, speedRightWheel);
	}

	protected abstract double[][] getControlMatrix();

	protected abstract double[] getSensorArray();

	protected int applySpeedFactor(double sensorValue) {
		return (int) sensorValue;
	}

	public void setMotionManager(MotionManager motionManager) {
		_motionManager = motionManager;
	}

	public void setSensorManager(SensorManager sensorManager) {
		_sensorManager = sensorManager;
	}

	public abstract void initializeSensorsAndActors();
}
