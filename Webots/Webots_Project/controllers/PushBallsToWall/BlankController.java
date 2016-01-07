import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;

public class BlankController extends SubSumptionController {

	@Override
	protected Collection<SensorType> getNeededSensors() {
		return Arrays.asList(SensorType.DIST_SENSOR_LF, SensorType.DIST_SENSOR_RF);
	}

	@Override
	protected Collection<ActorTypes> getNeededActors() {
		// TODO Auto-generated method stub
		return new LinkedList<>();
	}

	@Override
	public boolean meetsActivationCondition() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void activate() {
		System.out.println(SensorManager.getInstance().getSensor(SensorType.DIST_SENSOR_LF).getRawValue() + " "
				+ SensorManager.getInstance().getSensor(SensorType.DIST_SENSOR_LF).getRawValue());
		System.out.println(getSensorVector()[0] + " " + getSensorVector()[1]);
		System.out.println("Blank Active!");
	}

}
