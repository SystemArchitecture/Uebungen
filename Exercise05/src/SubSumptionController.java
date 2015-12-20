import java.util.Collection;

public abstract class SubSumptionController {
	
	public void init() {
		initSensors(getNeededSensor());
		initActors(getNeededActors());
	}
	
	private void initActors(Collection<ActorTypes> neededActors) {
		for(ActorTypes type : neededActors) {
			MotionManager.getInstance().getActor(type);
		}
	}

	private void initSensors(Collection<SensorTypes> neededSensor) {
		for(SensorTypes type : neededSensor) {
			SensorManager.getInstance().getSensor(type);
		}
	}

	protected abstract Collection<SensorTypes> getNeededSensor();
	
	protected abstract Collection<ActorTypes> getNeededActors();

	public abstract void readSensorInput();
	
	public abstract boolean meetsActivationCondition();
	
	public abstract void activate();
}
