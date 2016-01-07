import java.util.Collection;
import java.util.HashMap;

public abstract class SubSumptionController {

	protected static final double MAX_SPEED = 1000.0;
	protected static final double SPEED_MULTIPLIER = 7.5;
	
	protected HashMap<SensorType, Double> _sensorValues;

	public void init() {
		initSensors(getNeededSensors());
		initActors(getNeededActors());
	}

	private void initActors(Collection<ActorTypes> neededActors) {
		for (ActorTypes type : neededActors) {
			MotionManager.getInstance().getActor(type);
		}
	}

	private void initSensors(Collection<SensorType> neededSensor) {
		for (SensorType type : neededSensor) {
			SensorManager.getInstance().getSensor(type);
		}
	}

	protected HashMap<SensorType, Double> getSensorValuePairs() {
		return _sensorValues;
	}
	
	protected double[] getSensorVector() {
		return toPrimitiveArray(_sensorValues.values().toArray(new Double[_sensorValues.size()]));
	}
	
	private double[] toPrimitiveArray(Double[] values) {
		double[] primitveValues = new double[values.length];
		
		for(int i = 0; i < values.length; ++i) {
			primitveValues[i] = values[i];
		}
		
		return primitveValues;
	}

	protected abstract Collection<SensorType> getNeededSensors();

	protected abstract Collection<ActorTypes> getNeededActors();

	public void readSensorInput() {
		_sensorValues = new HashMap<>();
		
		for(SensorType type : getNeededSensors()) {
			_sensorValues.put(type, SensorManager.getInstance().getSensor(type).getValue());
		}
	}

	public abstract boolean meetsActivationCondition();

	public abstract void activate();
}
