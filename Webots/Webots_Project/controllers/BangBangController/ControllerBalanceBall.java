
public class ControllerBalanceBall extends Controller {
	private WheelsController _wheelsController;
	private int _maxSpeed;

	public ControllerBalanceBall(ControllerType type, int maxSpeed, int maxDistanceSensor) {
		super(type, maxSpeed);
		_maxSpeed = maxSpeed;
	}

	@Override
	protected void controlBangBang() {
		if (getRightDistValue() > getLeftDistValue()) {
			_wheelsController.driveRight();
		} else if (getLeftDistValue() > getRightDistValue()) {
			_wheelsController.driveLeft();
		} else {
			_wheelsController.driveForward();
		}
	}

	private double getLeftDistValue() {
		return ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_LF)).getValue()
				+ ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_LM)).getValue()
				+ ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_L)).getValue();
	}

	private double getRightDistValue() {
		return ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_RF)).getValue()
				+ ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_RM)).getValue()
				+ ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_R)).getValue();
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
		_wheelsController = ((WheelsController) _motionManager.getActor(Actor.DIFFERENTIAL_WHEELS));
		_wheelsController.setMaxSpeed(_maxSpeed);
	}

	@Override
	protected double[][] getControllMatrix() {
		double[][] priorityMatrix = { { 1, 2, 5, 0, 0, 0 }, { 0, 0, 0, 5, 2, 1 } };
		return priorityMatrix;
	}

	@Override
	protected double[] getSensorArray() {
		double distanceSensorL = ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_L)).getValue();
		double distanceSensorLM = ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_LM)).getValue();
		double distanceSensorLF = ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_LF)).getValue();
		double distanceSensorR = ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_R)).getValue();
		double distanceSensorRM = ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_RM)).getValue();
		double distanceSensorRF = ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_RF)).getValue();
		double[] _distanceSensors = { distanceSensorL, distanceSensorLM, distanceSensorLF, distanceSensorRF, distanceSensorRM,
				distanceSensorR };
		return _distanceSensors;
	}

}
