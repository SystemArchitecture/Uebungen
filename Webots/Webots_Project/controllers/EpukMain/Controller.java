
public abstract class Controller {
	private ControllerType _type;
	protected MotionManager _motionManager;
	protected SensorManager _sensorManager;
	protected WheelsController _wheelsController;
	protected int _maxSpeed;

	public Controller(ControllerType type, int maxSpeed) {
		_type = type;
		_maxSpeed = maxSpeed;
	}

	public void control() {
		if (_type.equals(ControllerType.BANGBANG)) {
			controlBangBang();
		} else if (_type.equals(ControllerType.PROPORTIONAL)) {
			controlProportional();
		} else {
			throw new UnsupportedOperationException();
		}
	}

	protected abstract void controlBangBang();

	protected abstract double[][] getControllMatrix();

	protected abstract double[] getSensorArray();

	protected void controlProportional() {

		double[] result = MatrixUtil.multiply(getControllMatrix(), getSensorArray());

		int speedLeftWheel = (applySpeedFactor((int) result[0]) > _maxSpeed) ? _maxSpeed
				: applySpeedFactor((int) result[0]);
		int speedRightWheel = (applySpeedFactor((int) result[1]) > _maxSpeed) ? _maxSpeed
				: applySpeedFactor((int) result[1]);

		_wheelsController.setSpeed(speedLeftWheel, speedRightWheel);
	}

	protected int applySpeedFactor(double sensorValue) {
		return (int) sensorValue;
	}

	public void setMotionManager(MotionManager motionManager) {
		_motionManager = motionManager;
	}

	public void setSensorManager(SensorManager sensorManager) {
		_sensorManager = sensorManager;
	}

	public abstract void init();
}
