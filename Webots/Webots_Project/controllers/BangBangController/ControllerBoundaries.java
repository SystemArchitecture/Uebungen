

public class ControllerBoundaries implements IController {
	private final int MAX_SPEED;
	private final int MIN_SPEED;
	private MotionManager _motionManager;
	private SensorManager _sensorManager;
	private final int DIST_SENSOR_MAX;

	public ControllerBoundaries(int maxDist, int minSpeed, int maxSpeed) {
		DIST_SENSOR_MAX = maxDist;
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
		System.out.println("boundaries");
		if (((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_L)).getValue() > DIST_SENSOR_MAX
				|| ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_LF)).getValue() > DIST_SENSOR_MAX) {
			driveRight();
		} else if (((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_R)).getValue() > DIST_SENSOR_MAX
				|| ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_RF)).getValue() > DIST_SENSOR_MAX) {
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
		_sensorManager.initialize(Sensor.DIST_SENSOR_L);
		_sensorManager.initialize(Sensor.DIST_SENSOR_LF);
		_sensorManager.initialize(Sensor.DIST_SENSOR_R);
		_sensorManager.initialize(Sensor.DIST_SENSOR_RF);
		_motionManager.initialize(Actor.DIFFERENTIAL_WHEELS);
	}

}
