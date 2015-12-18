
public class ControllerBalanceBall extends Controller {
	private int _maxSpeed;

	public ControllerBalanceBall(ControllerType type, int maxSpeed, int maxDistanceSensor) {
		super(type, maxSpeed);
		_maxSpeed = maxSpeed;
	}

	@Override
	protected void controlBangBang() {
		WheelsController wheelsController = (WheelsController) _motionManager.getActor(Actor.DIFFERENTIAL_WHEELS);
		
		if (getRightDistValue() > getLeftDistValue()) {
			wheelsController.driveRight();
		} else if (getLeftDistValue() > getRightDistValue()) {
			wheelsController.driveLeft();
		} else {
			wheelsController.driveForward();
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
	public void initializeSensorsAndActors() {
		_sensorManager.initialize(Sensor.DIST_SENSOR_LM);
		_sensorManager.initialize(Sensor.DIST_SENSOR_LF);
		_sensorManager.initialize(Sensor.DIST_SENSOR_RF);
		_sensorManager.initialize(Sensor.DIST_SENSOR_RM);
		_motionManager.initialize(Actor.DIFFERENTIAL_WHEELS);
		((WheelsController) _motionManager.getActor(Actor.DIFFERENTIAL_WHEELS)).setMaxSpeed(_maxSpeed);
	}

	@Override
	protected int applySpeedFactor(double sensorValue) {
		return (int) sensorValue;
	}

	@Override
	protected double[][] getControlMatrix() {
		double[][] priorityMatrix = { { 0, 0, 0.95, 1 }, 
										{1, 0.95, 0, 0} };
		return priorityMatrix;
	}

	@Override
	protected double[] getSensorArray() {
		double[] _distanceSensors = {((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_LM)).getValue(),
				((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_LF)).getValue(),
				((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_RF)).getValue(),
				((DistanceSensorAdapter) _sensorManager.getSensor(Sensor.DIST_SENSOR_RM)).getValue()};
		return _distanceSensors;
	}

}
