
/**
 * Controller for searching the next ball.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class SearchBallController extends SubSumptionController {
	private double _distanceSensorLeftMiddle;
	private double _distanceSensorLeftFront;
	private double _distanceSensorRightFront;
	private double _distanceSensorRightMiddle;

	public SearchBallController() {
	}

	@Override
	protected Collection<SensorTypes> getNeededSensor() {
		Collection<SensorTypes> sensors = new ArrayList<>();
		sensors.add(SensorTypes.DIST_SENSOR_LM);
		sensors.add(SensorTypes.DIST_SENSOR_LF);
		sensors.add(SensorTypes.DIST_SENSOR_RF);
		sensors.add(SensorTypes.DIST_SENSOR_RM);
		return sensors;
	}

	@Override
	protected Collection<ActorTypes> getNeededActors() {
		return Arrays.asList(ActorTypes.DIFFERENTIAL_WHEELS);
	}

	@Override
	public void readSensorInput() {
		_distanceSensorLeftMiddle = SensorManager.getInstance().getSensor(SensorTypes.DIST_SENSOR_LM).getValue();
		_distanceSensorLeftFront = SensorManager.getInstance().getSensor(SensorTypes.DIST_SENSOR_LF).getValue();
		_distanceSensorRightFront = SensorManager.getInstance().getSensor(SensorTypes.DIST_SENSOR_RF).getValue();
		_distanceSensorRightMiddle = SensorManager.getInstance().getSensor(SensorTypes.DIST_SENSOR_RM).getValue();
	}

	@Override
	public boolean meetsActivationCondition() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void activate() {
		double[] sensorVector = { _distanceSensorLeftMiddle, _distanceSensorLeftFront, _distanceSensorRightFront,
				_distanceSensorRightMiddle };
		double[][] sensorMatrix = { { 0, 0, -10, -10 }, 
									{ 10, 10, 0, 0 } };
		double[] resultVector = MatrixUtil.multiply(sensorMatrix, sensorVector);
		
		WheelsController wheels = (WheelsController) MotionManager.getInstance().getActor(ActorTypes.DIFFERENTIAL_WHEELS);
		
		
		System.out.println(sensorVector[0] + " " + sensorVector[1]);
		wheels.setSpeed((int) resultVector[0], (int) resultVector[1]);
	}

}
