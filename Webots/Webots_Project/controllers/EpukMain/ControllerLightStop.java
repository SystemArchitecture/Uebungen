
public class ControllerLightStop extends Controller {
	public final int DIST_SENSOR_MAX;
	private WheelsController _wheelsController;
	private int _maxSpeed;

	public ControllerLightStop(ControllerType type, int maxSpeed, int distSensorMax) {
		super(type, maxSpeed);
		DIST_SENSOR_MAX = distSensorMax;
		_maxSpeed = maxSpeed;
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
			_wheelsController.driveStop();
		} else if (getRightLightValue() < getLeftLightValue()) {
			_wheelsController.driveRight();
		} else if (getLeftLightValue() < getRightLightValue()) {
			_wheelsController.driveLeft();
		} else {
			_wheelsController.driveForward();
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
	protected int applySpeedFactor(double sensorValue) {
		return (int) sensorValue / 4;
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
		_wheelsController = ((WheelsController) _motionManager.getActor(Actor.DIFFERENTIAL_WHEELS));
		_wheelsController.setMaxSpeed(_maxSpeed);
	}

	@Override
	protected double[][] getControllMatrix() {
		double[][] priorityMatrix = { { 0.25, 1, -1, -1, -0.3, -0.2 }, { -0.2, -0.3, -1, -1, 1, 0.25 } };
		return priorityMatrix;
	}

	@Override
	protected double[] getSensorArray() {
		double lightSensorL = ((LightSensorAdapter) _sensorManager.getSensor(Sensor.LIGHT_SENSOR_L)).getValue();
		double lightSensorLF = ((LightSensorAdapter) _sensorManager.getSensor(Sensor.LIGHT_SENSOR_LF)).getValue();
		double lightSensorR = ((LightSensorAdapter) _sensorManager.getSensor(Sensor.LIGHT_SENSOR_R)).getValue();
		double lightSensorRF = ((LightSensorAdapter) _sensorManager.getSensor(Sensor.LIGHT_SENSOR_RF)).getValue();
		double distanceSensorLF = ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_LF)).getValue();
		double distanceSensorRF = ((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_RF)).getValue();
		double[] _sensors = { lightSensorL, lightSensorLF, distanceSensorLF, distanceSensorRF, lightSensorRF,
				lightSensorR };
		return _sensors;
	}

}
