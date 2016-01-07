
public abstract class AbstractSensor {
	protected double _rawValue;
	
	protected abstract double readValue();
	
	public void update() {
		_rawValue = readValue();
	}
	
	public double getRawValue(){
		return _rawValue;
	}
	
	public double getValue() {
		return _rawValue;
	}
}
