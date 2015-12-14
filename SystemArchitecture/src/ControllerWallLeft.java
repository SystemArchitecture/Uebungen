
public class ControllerWallLeft extends Controller {

	private int _maxDistance;
	private WheelsController _wheelsController;
	private int _maxSpeed;

	public ControllerWallLeft(ControllerType type, int maxSpeed, int maxDistance) {
		super(type);
		_maxDistance = maxDistance;
		_maxSpeed = maxSpeed;
	}

	@Override
	protected void controlBangBang() {

		if (((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_LF)).getValue() > _maxDistance
				|| ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_RF))
						.getValue() > _maxDistance) {
			_wheelsController.driveRight();
		} else if (((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_L)).getValue() < _maxDistance) {
			_wheelsController.driveLeft();
		} else if (((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_L)).getValue() > _maxDistance) {
			_wheelsController.driveRight();
		}
		
	}

	@Override
	protected void controlProportional() {
		// TODO Auto-generated method stub

	}

	@Override
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
