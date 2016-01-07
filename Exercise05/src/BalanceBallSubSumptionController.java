import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class BalanceBallSubSumptionController extends SubSumptionController {

	private static final double MIN_DISTANCE = 0.01;

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

	private boolean active;
	@Override
	public boolean meetsActivationCondition() {
		HashMap<SensorType, Double> sensorValuePairs = getSensorValuePairs();
		System.out.println(sensorValuePairs.get(SensorType.DIST_SENSOR_LF) + " " + sensorValuePairs.get(SensorType.DIST_SENSOR_RF));
		if ((sensorValuePairs.get(SensorType.DIST_SENSOR_LF) > MIN_DISTANCE)
				|| (sensorValuePairs.get(SensorType.DIST_SENSOR_RF) > MIN_DISTANCE)) {
			active = true;
		}/* else {
			return false;
		}*/
		
		return active;
	}

	@Override
	public void activate() {
		double[] sensorVector = getSensorVector();

		double[][] sensorMatrix = { { 0.0, 0.0, 0.1, 0.2 }, { 0.2, 0.1, 0.0, 0.0 } };

		double[] resultVector = MatrixUtil.multiply(sensorMatrix, sensorVector);

		WheelsController wheels = (WheelsController) MotionManager.getInstance()
				.getActor(ActorTypes.DIFFERENTIAL_WHEELS);

		wheels.setSpeed((int) (resultVector[0] * MAX_SPEED * SPEED_MULTIPLIER), (int) (resultVector[1] * MAX_SPEED * SPEED_MULTIPLIER));

	}
}
