/**
 * Controller for searching the next ball.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class SearchBallSubSumptionController extends SubSumptionController {

	@Override
	protected Collection<SensorType> getNeededSensors() {
		Collection<SensorType> sensors = new ArrayList<>();
		sensors.add(SensorType.DIST_SENSOR_LM);
		sensors.add(SensorType.DIST_SENSOR_LF);
		sensors.add(SensorType.DIST_SENSOR_RF);
		sensors.add(SensorType.DIST_SENSOR_RM);
		return sensors;
	}

	@Override
	protected Collection<ActorTypes> getNeededActors() {
		return Arrays.asList(ActorTypes.DIFFERENTIAL_WHEELS);
	}

	@Override
	public boolean meetsActivationCondition() {
		//lowest priority controller needs always to be activated
		return true;
	}

	@Override
	public void activate() {
		WheelsController wheels = (WheelsController) MotionManager.getInstance().getActor(ActorTypes.DIFFERENTIAL_WHEELS);
		wheels.setSpeed((int) (SPEED_MULTIPLIER / 10), - (int) (SPEED_MULTIPLIER / 10));
	}

}
