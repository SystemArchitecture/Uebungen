

public class ControllerBoundaries extends Controller {
	private final int MAX_SPEED;
	private final int MIN_SPEED;
	private final int DIST_SENSOR_MAX;

	public ControllerBoundaries(ControllerType type, int maxDist, int minSpeed, int maxSpeed) {
		super(type);
		DIST_SENSOR_MAX = maxDist;
		MIN_SPEED = minSpeed;
		MAX_SPEED = maxSpeed;
	}

	protected void controlBangBang() {
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

	@Override
	protected void controlProportional() {
		// TODO Auto-generated method stub
		
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

	public void init() {
		_sensorManager.initialize(Sensor.DIST_SENSOR_L);
		_sensorManager.initialize(Sensor.DIST_SENSOR_LF);
		_sensorManager.initialize(Sensor.DIST_SENSOR_R);
		_sensorManager.initialize(Sensor.DIST_SENSOR_RF);
		_motionManager.initialize(Actor.DIFFERENTIAL_WHEELS);
	}

}
