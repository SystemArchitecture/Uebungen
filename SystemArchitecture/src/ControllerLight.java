
public class ControllerLight extends Controller {
	public ControllerLight(ControllerType type, int maxSpeed) {
		super(type, maxSpeed);
	}

	protected void controlBangBang() {
		WheelsController wheelsController = (WheelsController) _motionManager.getActor(Actor.DIFFERENTIAL_WHEELS);
		
		if (getRightLightValue() < getLeftLightValue()) {
			wheelsController.driveRight();
		} else if (getLeftLightValue() < getRightLightValue()) {
			wheelsController.driveLeft();
		} else {
			wheelsController.driveForward();
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
	public void initializeSensors() {
		_sensorManager.initialize(Sensor.LIGHT_SENSOR_L);
		_sensorManager.initialize(Sensor.LIGHT_SENSOR_LM);
		_sensorManager.initialize(Sensor.LIGHT_SENSOR_LF);
		_sensorManager.initialize(Sensor.LIGHT_SENSOR_R);
		_sensorManager.initialize(Sensor.LIGHT_SENSOR_RM);
		_sensorManager.initialize(Sensor.LIGHT_SENSOR_RF);
		_motionManager.initialize(Actor.DIFFERENTIAL_WHEELS);
	}

	@Override
	protected double[][] getControlMatrix() {
		// lightSensorL, lightSensorLM, lightSensorLF, lightSensorRF lightSensorRM lightSensorR
		double[][] priorityMatrix = {{0.01, 0.01, 0.01, 0, 0, 0 }, 
									  { 0, 0, 0, 0.01, 0.01, 0.01 }};
		return priorityMatrix;
	}

	@Override
	protected double[] getSensorArray() {
		double[] _distanceSensors = { ((LightSensorAdapter) _sensorManager.getSensor(Sensor.LIGHT_SENSOR_L)).getValue(),
				((LightSensorAdapter) _sensorManager.getSensor(Sensor.LIGHT_SENSOR_LM)).getValue(),
				((LightSensorAdapter) _sensorManager.getSensor(Sensor.LIGHT_SENSOR_LF)).getValue(),
				((LightSensorAdapter) _sensorManager.getSensor(Sensor.LIGHT_SENSOR_R)).getValue(),
				((LightSensorAdapter) _sensorManager.getSensor(Sensor.LIGHT_SENSOR_RM)).getValue(),
				((LightSensorAdapter) _sensorManager.getSensor(Sensor.LIGHT_SENSOR_RF)).getValue()};

		return _distanceSensors;
	}

	@Override
	protected int applySpeedFactor(double sensorValue) {
		return (int) sensorValue * 2;
	}

}
