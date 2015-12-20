/**
 * Controller for driving along a wall on the left side.
 * @author Daniel
 *
 */
public class ControllerWallLeft extends Controller {

	private int _minDistance;
	private int _maxDistance;

	public ControllerWallLeft(ControllerType type, int maxSpeed, int speedFactor, int maxDistance, int minDistance) {
		super(type, maxSpeed, speedFactor);
		_minDistance = minDistance;
		_maxDistance = maxDistance;
	}

	@Override
	protected void controlBangBang() {
		WheelsController wheelsController = (WheelsController) _motionManager.getActor(Actor.DIFFERENTIAL_WHEELS);
		
		wheelsController.driveStop();

		double valueLeft = ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_L)).getAverageValue();
		double front = (((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_LF)).getAverageValue()
				+ ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_RF)).getAverageValue()) / 2;

		if (_minDistance < front) {
			wheelsController.driveRight();
		} else {
			if ((_minDistance > valueLeft) && (valueLeft > _maxDistance)) {
				wheelsController.driveForward();
			} else {
				if (_minDistance < valueLeft) {
					wheelsController.driveRight();
				} else if (valueLeft < _maxDistance) {
					wheelsController.driveLeft();
				}
			}
		}
		
	}

	@Override
	protected double[][] getControlMatrix() {
		double[][] priorityMatrix = { { 0.05 , 0.2, 0}, 
									  { 0, -0.1, 0.65}};
		return priorityMatrix;
	}

	@Override
	protected double[] getSensorArray() {
		double distanceSensorL = ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_L)).getAverageValue();
		double front = (((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_LF)).getAverageValue()
				+ ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_RF)).getAverageValue()) / 2;
		double distanceSensorR = ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_R)).getAverageValue();
		
		double[] _distanceSensors = { distanceSensorL, front, distanceSensorR };

		return _distanceSensors;
	}

	@Override
	public void initializeSensors() {
		_sensorManager.initialize(Sensor.DIST_SENSOR_L);
		_sensorManager.initialize(Sensor.DIST_SENSOR_LF);
		_sensorManager.initialize(Sensor.DIST_SENSOR_R);
		_sensorManager.initialize(Sensor.DIST_SENSOR_RF);
	}

}
