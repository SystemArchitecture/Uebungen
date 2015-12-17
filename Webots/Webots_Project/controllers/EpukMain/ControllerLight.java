
public class ControllerLight extends Controller {
	private WheelsController _wheelsController;

	public ControllerLight(ControllerType type, int maxSpeed) {
		super(type, maxSpeed);
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
	public void init() {
		_sensorManager.initialize(Sensor.LIGHT_SENSOR_L);
		_sensorManager.initialize(Sensor.LIGHT_SENSOR_LF);
		_sensorManager.initialize(Sensor.LIGHT_SENSOR_R);
		_sensorManager.initialize(Sensor.LIGHT_SENSOR_RF);
		_motionManager.initialize(Actor.DIFFERENTIAL_WHEELS);
		_wheelsController = ((WheelsController) _motionManager.getActor(Actor.DIFFERENTIAL_WHEELS));
		_wheelsController.setMaxSpeed(_maxSpeed);
	}

	@Override
	protected double[][] getControllMatrix() {
		double[][] priorityMatrix = { { 0.25, 1, -0.3, -0.2 }, { -0.2, -0.3 , 1, 0.25 } };
		return priorityMatrix;
	}

	@Override
	protected double[] getSensorArray() {
		double lightSensorL = 
				((LightSensorAdapter) _sensorManager.getSensor(Sensor.LIGHT_SENSOR_L)).getValue();
		double lightSensorLF = 
				((LightSensorAdapter) _sensorManager.getSensor(Sensor.LIGHT_SENSOR_LF)).getValue();
		double lightSensorR = 
				((LightSensorAdapter) _sensorManager.getSensor(Sensor.LIGHT_SENSOR_R)).getValue();
		double lightSensorRF = 
				((LightSensorAdapter) _sensorManager.getSensor(Sensor.LIGHT_SENSOR_RF)).getValue();
		double[] _distanceSensors = { lightSensorL, lightSensorLF, lightSensorRF, lightSensorR };
		
		return _distanceSensors;
	}
	

	@Override
	protected int applySpeedFactor(double sensorValue) {
		return (int) sensorValue / 4;
	}

}
