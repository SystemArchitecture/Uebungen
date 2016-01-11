/*
 * Ring Buffer
 */

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Queue;

public class RingBuffer<T> {
	
	private Queue<T> _ringBuffer;
	private int _ringBufferIndex;
	private int _ringBufferSize;
	
	public RingBuffer(int size) {
		_ringBuffer = new LinkedList<>();
		_ringBufferSize = size;
	}
	
	public void put(T element) {
		if(_ringBuffer.size() < _ringBufferSize) {
			_ringBuffer.add(element);
		} else {
			_ringBuffer.poll();
			_ringBuffer.add(element);
		}
	}
	
	public Collection<T> getElements() {
		return new ArrayList<>(_ringBuffer);
	}
}
