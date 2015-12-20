
public abstract class AbstractSensor {
	private double _value;
	
	protected abstract double readValue();
	
	public void update() {
		_value = readValue();
	}
	
	public double getValue() {
		return _value;
	}
}
