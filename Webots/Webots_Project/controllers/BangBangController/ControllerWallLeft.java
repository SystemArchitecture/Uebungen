
public class ControllerWallLeft extends Controller {

	private int _minDistance;
	private int _maxDistance;

	public ControllerWallLeft(ControllerType type, int maxSpeed, int maxDistance, int minDistance) {
		super(type, maxSpeed);
		_minDistance = minDistance;
		_maxDistance = maxDistance;
	}

	@Override
	protected void controlBangBang() {

		/*
		 * if (((DistanceSensorAdapter)
		 * _sensorManager.getSensor(Sensor.DIST_SENSOR_LF)).getValue() >
		 * _minDistance || ((DistanceSensorAdapter)
		 * _sensorManager.getSensor(Sensor.DIST_SENSOR_RF)) .getValue() >
		 * _minDistance) { _wheelsController.driveRight(); } else if
		 * (((DistanceSensorAdapter)
		 * _sensorManager.getSensor(Sensor.DIST_SENSOR_L)).getValue() <
		 * _minDistance) { _wheelsController.driveLeft(); } else if
		 * (((DistanceSensorAdapter)
		 * _sensorManager.getSensor(Sensor.DIST_SENSOR_L)).getValue() >
		 * _minDistance) { _wheelsController.driveRight(); }
		 */
		_wheelsController.driveStop();

		double valueLeft = ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_L)).getValue();
		double front = (((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_LF)).getValue()
				+ ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_RF)).getValue()) / 2;

		if (_minDistance < front) {
			_wheelsController.driveRight();
		} else {
			if ((_minDistance > valueLeft) && (valueLeft > _maxDistance)) {
				_wheelsController.driveForward();
			} else {
				if (_minDistance < valueLeft) {
					_wheelsController.driveRight();
				} else if (valueLeft < _maxDistance) {
					_wheelsController.driveLeft();
				}
			}
		}
		
		System.out.println(
				"Value " + ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_L)).getValue());
		System.out.println("Front " + front);
		System.out.println("Max " + _maxDistance);
		System.out.println("Min " + _minDistance);
		System.out.println();
		
	}

	@Override
	protected double[][] getControllMatrix() {
		double[][] priorityMatrix = { { 0.1 , 0.2, 0}, 
									  { 0, 0.1, 0.5}};
		return priorityMatrix;
	}

	@Override
	protected double[] getSensorArray() {
		double distanceSensorL = ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_L)).getValue();
		double front = (((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_LF)).getValue()
				+ ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_RF)).getValue()) / 2;
		double distanceSensorR = ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_R)).getValue();
		
		double[] _distanceSensors = { distanceSensorL, front, distanceSensorR };

		return _distanceSensors;
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
