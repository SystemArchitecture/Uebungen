/**
 * Controller for driving towards a light source and stopping in front of it.
 * @author Daniel
 *
 */
public class ControllerLightStop extends Controller {
	public final int DIST_SENSOR_MAX;

	public ControllerLightStop(ControllerType type, int maxSpeed, int speedFactor, int distSensorMax) {
		super(type, maxSpeed, speedFactor);
		DIST_SENSOR_MAX = distSensorMax;
	}

	@Override
	protected void controlBangBang() {
		WheelsController wheelsController = (WheelsController) _motionManager.getActor(Actor.DIFFERENTIAL_WHEELS);
		
		if ((((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_RF))
						.getAverageValue() > DIST_SENSOR_MAX)
				&& (((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_LF))
								.getAverageValue() > DIST_SENSOR_MAX)) {
			wheelsController.driveStop();
		} else if (getRightLightValue() < getLeftLightValue()) {
			wheelsController.driveRight();
		} else if (getLeftLightValue() < getRightLightValue()) {
			wheelsController.driveLeft();
		} else {
			wheelsController.driveForward();
		}
	}

	private double getLeftLightValue() {
		return ((LightSensorAdapter) _sensorManager.getSensor(Sensor.LIGHT_SENSOR_L)).getValue()
				+ ((LightSensorAdapter) _sensorManager.getSensor(Sensor.LIGHT_SENSOR_LM)).getValue()
				+ ((LightSensorAdapter) _sensorManager.getSensor(Sensor.LIGHT_SENSOR_LF)).getValue();
	}

	private double getRightLightValue() {
		return ((LightSensorAdapter) _sensorManager.getSensor(Sensor.LIGHT_SENSOR_R)).getValue()
				+ ((LightSensorAdapter) _sensorManager.getSensor(Sensor.LIGHT_SENSOR_RM)).getValue()
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
		_sensorManager.initialize(Sensor.DIST_SENSOR_LF);
		_sensorManager.initialize(Sensor.DIST_SENSOR_RF);
	}

	@Override
	protected double[][] getControlMatrix() {
		// lightSensorL, lightSensorLM, lightSensorLF, lightSensorR
		// lightSensorRM lightSensorRF, distSensorLF, distSensorRF
		double[][] priorityMatrix = {{0.07, 0.07, 0.07, -0.02, -0.02, -0.02 , -1, -1}, 
				  					 {-0.02, -0.02, -0.02, 0.07, 0.07, 0.07, -1, -1 }};
		return priorityMatrix;
	}

	@Override
	protected double[] getSensorArray() {
		double[] _distanceSensors = { ((LightSensorAdapter) _sensorManager.getSensor(Sensor.LIGHT_SENSOR_L)).getValue(),
				((LightSensorAdapter) _sensorManager.getSensor(Sensor.LIGHT_SENSOR_LM)).getValue(),
				((LightSensorAdapter) _sensorManager.getSensor(Sensor.LIGHT_SENSOR_LF)).getValue(),
				((LightSensorAdapter) _sensorManager.getSensor(Sensor.LIGHT_SENSOR_R)).getValue(),
				((LightSensorAdapter) _sensorManager.getSensor(Sensor.LIGHT_SENSOR_RM)).getValue(),
				((LightSensorAdapter) _sensorManager.getSensor(Sensor.LIGHT_SENSOR_RF)).getValue(),
				((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_LF)).getAverageValue(),
				((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_RF)).getAverageValue()};

		return _distanceSensors;
	}


}
