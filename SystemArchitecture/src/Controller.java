
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

		int speedLeftWheel = (getSpeedFactor((int) result[0]) > _maxSpeed) ? _maxSpeed
				: getSpeedFactor((int) result[0]);
		int speedRightWheel = (getSpeedFactor((int) result[1]) > _maxSpeed) ? _maxSpeed
				: getSpeedFactor((int) result[1]);

		_wheelsController.setSpeed(speedLeftWheel, speedRightWheel);
	}

private int 
(double sensorValue) {
	return (int) sensorValue;// / (_maxDistanceSensor / _maxSpeed);
}

	public void setMotionManager(MotionManager motionManager) {
		_motionManager = motionManager;
	}

	public void setSensorManager(SensorManager sensorManager) {
		_sensorManager = sensorManager;
	}

	public abstract void init();
}
