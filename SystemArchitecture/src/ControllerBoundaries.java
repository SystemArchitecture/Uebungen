/**
 * Controller for not driving into wall.
 * @author Daniel
 *
 */
public class ControllerBoundaries extends Controller {
	private final int DIST_SENSOR_MAX;
	private WheelsController _wheelsController;

	public ControllerBoundaries(ControllerType type, int maxSpeed, int speedFactor, int maxDist) {
		super(type, maxSpeed, speedFactor);
		DIST_SENSOR_MAX = maxDist;
	}

	protected void controlBangBang() {
		if (((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_L)).getAverageValue() > DIST_SENSOR_MAX
				|| ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_LF)).getAverageValue() > DIST_SENSOR_MAX) {
			_wheelsController.driveRight();
		} else if (((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_R)).getAverageValue() > DIST_SENSOR_MAX
				|| ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_RF)).getAverageValue() > DIST_SENSOR_MAX) {
			_wheelsController.driveLeft();
		} else {
			_wheelsController.driveForward();
		}
	}

	@Override
	protected void controlProportional() {
		// TODO Auto-generated method stub
		
	}

	public void initializeSensors() {
		_sensorManager.initialize(Sensor.DIST_SENSOR_L);
		_sensorManager.initialize(Sensor.DIST_SENSOR_LF);
		_sensorManager.initialize(Sensor.DIST_SENSOR_R);
		_sensorManager.initialize(Sensor.DIST_SENSOR_RF);
		_motionManager.initialize(Actor.DIFFERENTIAL_WHEELS);
	}

	@Override
	protected double[][] getControlMatrix() {
		return null;
	}

	@Override
	protected double[] getSensorArray() {
		return null;
	}

}
