/*
 * Controller for Avoiding Walls
 * Meets activation condition when accelerometer is 0.
 * Needs to drive away until distance sensors values are low enough.
 */

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public class AvoidWallSubSumptionController extends SubSumptionController {

	private static final double MAX_DISTANCE = 0.1;
	
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
		
		/*if ((sensorValuePairs.get(SensorType.DIST_SENSOR_LM) > MAX_DISTANCE)
				|| (sensorValuePairs.get(SensorType.DIST_SENSOR_RM) > MAX_DISTANCE)) {
			return true;
		} else {
			return false;
		}*/
		return false;
	}

	@Override
	public void activate() {
		
	}

}
