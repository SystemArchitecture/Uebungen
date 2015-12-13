
public class ControllerBalanceBall extends Controller {
	private WheelsController _wheeldController;
	private int _maxSpeed;

	public ControllerBalanceBall(ControllerType type, int maxSpeed) {
		super(type);
		_maxSpeed = maxSpeed;
	}

	@Override
	protected void controlBangBang() {
		if (getRightDistValue() > getLeftDistValue()) {
			_wheeldController.driveRight();
		} else if (getLeftDistValue() > getRightDistValue()) {
			_wheeldController.driveLeft();
		} else {
			_wheeldController.driveForward();
		}
	}

	@Override
	protected void controlProportional() {
		// TODO Auto-generated method stub

	}

	private double getLeftDistValue() {
		return ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_LF)).getValue() +
				 ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_LM)).getValue() +
				 ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_L)).getValue();
	}

	private double getRightDistValue() {
		return ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_RF)).getValue() +
				 ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_RM)).getValue() +
				 ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_R)).getValue();
	}

	@Override
	public void init() {
		_sensorManager.initialize(Sensor.DIST_SENSOR_L);
		_sensorManager.initialize(Sensor.DIST_SENSOR_LF);
		_sensorManager.initialize(Sensor.DIST_SENSOR_LM);
		_sensorManager.initialize(Sensor.DIST_SENSOR_R);
		_sensorManager.initialize(Sensor.DIST_SENSOR_RF);
		_sensorManager.initialize(Sensor.DIST_SENSOR_RM);
		_motionManager.initialize(Actor.DIFFERENTIAL_WHEELS);
		_wheeldController = ((WheelsController) _motionManager.getActor(Actor.DIFFERENTIAL_WHEELS));
		_wheeldController.setMaxSpeed(_maxSpeed);
	}

}
