
public class ControllerLight implements IController {
	private final int MAX_SPEED;
	private final int MIN_SPEED;
	private MotionManager _motionManager;
	private SensorManager _sensorManager;

	public ControllerLight(int minSpeed, int maxSpeed) {
		MIN_SPEED = minSpeed;
		MAX_SPEED = maxSpeed;
	}

	@Override
	public void control(ControllerType type) {
		if (type.equals(ControllerType.BANGBANG)) {
			controlBangBang();
		} else {
			throw new UnsupportedOperationException();
		}
	}

	private void controlBangBang() {
		if (getRightLightValue() < getLeftLightValue()) {
			driveRight();
		} else if (getLeftLightValue() < getRightLightValue()) {
			driveLeft();
		} else {
			driveForward();
		}
	}

	private void driveRight() {
		((WheelsAdapter) _motionManager.getActor(Actor.DIFFERENTIAL_WHEELS)).setSpeed(MAX_SPEED, MIN_SPEED);
	}

	private void driveForward() {
		((WheelsAdapter) _motionManager.getActor(Actor.DIFFERENTIAL_WHEELS)).setSpeed(MAX_SPEED, MAX_SPEED);
	}

	private void driveLeft() {
		((WheelsAdapter) _motionManager.getActor(Actor.DIFFERENTIAL_WHEELS)).setSpeed(MIN_SPEED, MAX_SPEED);
	}

	private double getLeftLightValue() {
		return ((LightSensorAdapter) _sensorManager.getSensor(Sensor.LIGHT_SENSOR_L)).getValue()
				+ ((LightSensorAdapter) _sensorManager.getSensor(Sensor.LIGHT_SENSOR_LF)).getValue();
	}

	private double getRightLightValue() {
		return ((LightSensorAdapter) _sensorManager.getSensor(Sensor.LIGHT_SENSOR_R)).getValue()
				+ ((LightSensorAdapter) _sensorManager.getSensor(Sensor.LIGHT_SENSOR_RF)).getValue();
	}

	@Override
	public void setMotionManager(MotionManager motionManager) {
		_motionManager = motionManager;
	}

	@Override
	public void setSensorManager(SensorManager sensorManager) {
		_sensorManager = sensorManager;
	}

	@Override
	public void init() {
		_sensorManager.initialize(Sensor.LIGHT_SENSOR_L);
		_sensorManager.initialize(Sensor.LIGHT_SENSOR_LF);
		_sensorManager.initialize(Sensor.LIGHT_SENSOR_R);
		_sensorManager.initialize(Sensor.LIGHT_SENSOR_RF);
		_motionManager.initialize(Actor.DIFFERENTIAL_WHEELS);
	}

}
