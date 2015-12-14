
public class ControllerLight extends Controller {
	private WheelsController _wheelsController;
	private int _maxSpeed;

	public ControllerLight(ControllerType type, int maxSpeed) {
		super(type);
		_maxSpeed = maxSpeed;
	}

	protected void controlBangBang() {
		if (getRightLightValue() < getLeftLightValue()) {
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
	protected void controlProportional() {
		double lightSensorL = 
				((LightSensorAdapter) _sensorManager.getSensor(Sensor.LIGHT_SENSOR_L)).getValue();
		double lightSensorLF = 
				((LightSensorAdapter) _sensorManager.getSensor(Sensor.LIGHT_SENSOR_LF)).getValue();
		double lightSensorR = 
				((LightSensorAdapter) _sensorManager.getSensor(Sensor.LIGHT_SENSOR_R)).getValue();
		double lightSensorRF = 
				((LightSensorAdapter) _sensorManager.getSensor(Sensor.LIGHT_SENSOR_RF)).getValue();
		double[] _distanceSensors = { lightSensorL, lightSensorLF, lightSensorRF, lightSensorR };
		
		System.out.println(_distanceSensors[0] + " - " + _distanceSensors[1] + " - " + _distanceSensors[2] + " - " + _distanceSensors[3]);

		double[][] priorityMatrix = { { 0.25, 1, -0.3, -0.2 }, { -0.2, -0.3 , 1, 0.25 } };

		double[] result = MatrixUtil.multiply(priorityMatrix, _distanceSensors);

		// double[] result = MatrixUtil.multiply(resultSensors,
		// _wheelSpeedVector);
		System.out.println(result.length);
		System.out.println(result[0] + " - " + result[1]);

		int speedLeftWheel = (getSpeedFactor((int) result[0]) > _maxSpeed) ? _maxSpeed : getSpeedFactor((int) result[0]);
		int speedRightWheel = (getSpeedFactor((int) result[1]) > _maxSpeed) ? _maxSpeed : getSpeedFactor((int) result[1]);

		_wheelsController.setSpeed(speedLeftWheel, speedRightWheel);
	}

	private int getSpeedFactor(double sensorValue) {
		return (int) sensorValue / 4;
	}

	@Override
	public void init() {
		_sensorManager.initialize(Sensor.LIGHT_SENSOR_L);
		_sensorManager.initialize(Sensor.LIGHT_SENSOR_LF);
		_sensorManager.initialize(Sensor.LIGHT_SENSOR_R);
		_sensorManager.initialize(Sensor.LIGHT_SENSOR_RF);
		_motionManager.initialize(Actor.DIFFERENTIAL_WHEELS);
		_wheelsController = ((WheelsController) _motionManager.getActor(Actor.DIFFERENTIAL_WHEELS));
		_wheelsController.setMaxSpeed(_maxSpeed);
	}

}
