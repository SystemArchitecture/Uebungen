import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;

public abstract class NomalizedSensor extends AbstractSensor {

	private RingBuffer<Double> _ringbuffer;
	private double _min;
	private double _max;
	
	private double _value;
	
	public NomalizedSensor(int bufferSize, double min, double max) {
		_ringbuffer = new RingBuffer<>(bufferSize);
	}

	@Override
	public void update() {
		super.update();
				
		if(_max < _rawValue) {
			_max = _rawValue;
		}
		
		if(_rawValue < _min) {
			_rawValue = _min;
		}
		
		_ringbuffer.put(_rawValue);

		LinkedList<Double> values = new LinkedList<Double>(_ringbuffer.getElements());
		values.sort(new Comparator<Double>() {

			@Override
			public int compare(Double d1, Double d2) {
				if (d1 < d2) {
					return -1;
				} else if (d1 > d2) {
					return 1;
				} else {
					return 0;
				}
			}
		});
		values.addFirst(_min);
		values.addLast(_max);
		//_value = normalize(values.get(0), values.get(values.size() - 1), _value);
		_value = normalize(_min, _max, _rawValue);
	}

	private double normalize(double min, double max, double value) {
		return (value - min) / (max - min);
	}
	
	@Override
	public double getValue() {
		return _value;
	}
}
