import java.util.Collection;
import java.util.LinkedList;

public class BlankController extends SubSumptionController {

	@Override
	protected Collection<SensorType> getNeededSensors() {
		// TODO Auto-generated method stub
		return new LinkedList<>();
	}

	@Override
	protected Collection<ActorTypes> getNeededActors() {
		// TODO Auto-generated method stub
		return new LinkedList<>();
	}

	@Override
	public boolean meetsActivationCondition() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void activate() {
		// TODO Auto-generated method stub
		System.out.println("Blank Active!");
	}

}
