
public class ControllerLight extends Controller {
	private final int MAX_SPEED;
	private final int MIN_SPEED;

	public ControllerLight(ControllerType type, int minSpeed, int maxSpeed) {
		super(type);
		MIN_SPEED = minSpeed;
		MAX_SPEED = maxSpeed;
	}
	
	protected void controlBangBang() {
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
	protected void controlProportional() {
		// TODO Auto-generated method stub
		
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
