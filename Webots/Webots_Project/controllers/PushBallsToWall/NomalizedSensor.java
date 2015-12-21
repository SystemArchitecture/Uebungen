import java.util.ArrayList;
import java.util.Comparator;

public abstract class NomalizedSensor extends AbstractSensor {

	private RingBuffer<Double> _ringbuffer;

	public NomalizedSensor(int bufferSize) {
		_ringbuffer = new RingBuffer<>(bufferSize);
	}

	@Override
	public void update() {
		super.update();

		_ringbuffer.put(_value);

		ArrayList<Double> values = new ArrayList<Double>(_ringbuffer.getElements());
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
		
		_value = normalize(values.get(0), values.get(values.size() - 1), _value);
	}

	private double normalize(double min, double max, double value) {
		return (value - min) / (max - min);
	}
}
