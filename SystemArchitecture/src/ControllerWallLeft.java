
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
		double distanceSensorL = ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_L)).getValue();
		double distanceSensorLM = ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_LM)).getValue();
		double distanceSensorLF = ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_LF)).getValue();
		double distanceSensorR = ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_R)).getValue();
		double distanceSensorRM = ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_RM)).getValue();
		double distanceSensorRF = ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_RF)).getValue();
		double[] _distanceSensors = { distanceSensorL, distanceSensorLM, distanceSensorLF, distanceSensorRF, distanceSensorRM,
				distanceSensorR };

		double[][] priorityMatrix = { { 1, 0.75, 0.5, 0, 0, 0 }, { 0, 0, 0, -1, -0.75, -0.5 } };

		double[] result = MatrixUtil.multiply(priorityMatrix, _distanceSensors);

		int speedLeftWheel = (getSpeedFactor((int) result[0]) > _maxSpeed) ? _maxSpeed
				: getSpeedFactor((int) result[0]);
		int speedRightWheel = (getSpeedFactor((int) result[1]) > _maxSpeed) ? _maxSpeed
				: getSpeedFactor((int) result[1]);

		_wheelsController.setSpeed(speedLeftWheel, speedRightWheel);
	}

	private int getSpeedFactor(double sensorValue) {
		return (int) sensorValue;// / (_maxDistanceSensor / _maxSpeed);
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
