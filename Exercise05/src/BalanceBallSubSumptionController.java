import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class BalanceBallSubSumptionController extends SubSumptionController {

	private static final double MIN_DISTANCE = 0.1;
	
	@Override
	protected Collection<SensorType> getNeededSensors() {
		return Arrays.asList(SensorType.DIST_SENSOR_LM,
								SensorType.DIST_SENSOR_LF, 
								SensorType.DIST_SENSOR_RF,
								SensorType.DIST_SENSOR_RM);
	}

	@Override
	protected Collection<ActorTypes> getNeededActors() {
		return Arrays.asList(ActorTypes.DIFFERENTIAL_WHEELS);
	}

	@Override
	public boolean meetsActivationCondition() {
		HashMap<SensorType, Double> sensorValuePairs = getSensorValuePairs();
		if((sensorValuePairs.get(SensorType.DIST_SENSOR_LF) > MIN_DISTANCE) ||
				(sensorValuePairs.get(SensorType.DIST_SENSOR_RF) > MIN_DISTANCE))
		{
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void activate() {
		double[] sensorVector = getSensorVector();
		double[][] sensorMatrix = { {0.0, 0.0 , 0.1, 0.2}, 
									{0.2, 0.1, 0.0, 0.0}};
		
		double[] resultVector = MatrixUtil.multiply(sensorMatrix, sensorVector);
		
		WheelsController wheels = (WheelsController) MotionManager.getInstance().getActor(ActorTypes.DIFFERENTIAL_WHEELS);
		
		wheels.setSpeed((int) (resultVector[0] * SPEED_MULTIPLIER), (int) (resultVector[1] * SPEED_MULTIPLIER));
		
	}
}
