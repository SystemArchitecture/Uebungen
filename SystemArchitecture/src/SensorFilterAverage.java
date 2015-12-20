/**
 * Calculates the average sensor value for smoother driving.
 */
import java.util.ArrayList;

public class SensorFilterAverage {

	private ArrayList<Double> _ringBuffer;
	private int _ringBufferIndex;
	private int _ringBufferSize;

	public SensorFilterAverage(int ringBufferSize) {
		_ringBuffer = new ArrayList<Double>(ringBufferSize);
		_ringBufferSize = ringBufferSize;
		for (int i = 0; i < ringBufferSize; ++i) {
			_ringBuffer.add(0.0);
		}
	}

	public Double getAverageValue(Double value) {
		if (value > 0) {
			_ringBuffer.set(_ringBufferIndex % _ringBufferSize, value);
			_ringBufferIndex++;
		}
		
		double sum = 0;
		for (double val : _ringBuffer) {
			sum += val;
		}
		return sum / _ringBufferSize;
	}
}
