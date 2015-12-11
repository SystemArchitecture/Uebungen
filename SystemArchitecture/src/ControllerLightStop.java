
public class ControllerLightStop extends Controller {
	public final int MIN_SPEED;
	public final int MAX_SPEED;
	public final int DIST_SENSOR_MAX;

	public ControllerLightStop(ControllerType type, int minSpeed, int maxSpeed, int distSensorMax) {
		super(type);
		MIN_SPEED = minSpeed;
		MAX_SPEED = maxSpeed;
		DIST_SENSOR_MAX = distSensorMax;
	}

	@Override
	protected void controlBangBang() {
		if ((((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_R)).getValue() > DIST_SENSOR_MAX
				|| ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_RF))
						.getValue() > DIST_SENSOR_MAX)
				&& (((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_L))
						.getValue() > DIST_SENSOR_MAX
						|| ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_LF))
								.getValue() > DIST_SENSOR_MAX)) {
			driveStop();
		} else if (getRightLightValue() < getLeftLightValue()) {
			driveRight();
		} else if (getLeftLightValue() < getRightLightValue()) {
			driveLeft();
		} else {
			driveForward();
		}
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

	private void driveStop() {
		((WheelsAdapter) _motionManager.getActor(Actor.DIFFERENTIAL_WHEELS)).setSpeed(MIN_SPEED, MIN_SPEED);
	}

	@Override
	public void init() {
		_sensorManager.initialize(Sensor.LIGHT_SENSOR_L);
		_sensorManager.initialize(Sensor.LIGHT_SENSOR_LF);
		_sensorManager.initialize(Sensor.LIGHT_SENSOR_R);
		_sensorManager.initialize(Sensor.LIGHT_SENSOR_RF);
		_sensorManager.initialize(Sensor.DIST_SENSOR_L);
		_sensorManager.initialize(Sensor.DIST_SENSOR_LF);
		_sensorManager.initialize(Sensor.DIST_SENSOR_R);
		_sensorManager.initialize(Sensor.DIST_SENSOR_RF);
		_motionManager.initialize(Actor.DIFFERENTIAL_WHEELS);
	}

}
