

public class ControllerBoundaries extends Controller {
	private final int DIST_SENSOR_MAX;
	private WheelsController _wheelsController;
	private int _maxSpeed;

	public ControllerBoundaries(ControllerType type, int maxDist, int maxSpeed) {
		super(type);
		DIST_SENSOR_MAX = maxDist;
		_maxSpeed = maxSpeed;
	}

	protected void controlBangBang() {
		if (((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_L)).getValue() > DIST_SENSOR_MAX
				|| ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_LF)).getValue() > DIST_SENSOR_MAX) {
			_wheelsController.driveRight();
		} else if (((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_R)).getValue() > DIST_SENSOR_MAX
				|| ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_RF)).getValue() > DIST_SENSOR_MAX) {
			_wheelsController.driveLeft();
		} else {
			_wheelsController.driveForward();
		}
	}

	@Override
	protected void controlProportional() {
		// TODO Auto-generated method stub
		
	}

	public void init() {
		_sensorManager.initialize(Sensor.DIST_SENSOR_L);
		_sensorManager.initialize(Sensor.DIST_SENSOR_LF);
		_sensorManager.initialize(Sensor.DIST_SENSOR_R);
		_sensorManager.initialize(Sensor.DIST_SENSOR_RF);
		_motionManager.initialize(Actor.DIFFERENTIAL_WHEELS);
		_wheelsController = ((WheelsController) _motionManager.getActor(Actor.DIFFERENTIAL_WHEELS));
		_wheelsController.setMaxSpeed(_maxSpeed);
	}

}
