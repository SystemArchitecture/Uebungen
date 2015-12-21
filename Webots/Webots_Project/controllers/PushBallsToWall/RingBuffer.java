import java.util.ArrayList;
import java.util.Collection;

public class RingBuffer<T> {
	
	private ArrayList<T> _ringBuffer;
	private int _ringBufferIndex;
	private int _ringBufferSize;
	
	public RingBuffer(int size) {
		_ringBuffer = new ArrayList<>(size);
		_ringBufferSize = size;
	}
	
	public void put(T element) {
		if(_ringBuffer.size() < _ringBufferSize) {
			_ringBuffer.add(element);
		} else {
			_ringBuffer.add(_ringBufferIndex % _ringBufferSize, element);
		}
	}
	
	public Collection<T> getElements() {
		return new ArrayList<>(_ringBuffer);
	}
}
