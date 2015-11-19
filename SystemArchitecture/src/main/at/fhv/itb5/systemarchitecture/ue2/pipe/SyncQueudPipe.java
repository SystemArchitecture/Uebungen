package main.at.fhv.itb5.systemarchitecture.ue2.pipe;

import java.io.StreamCorruptedException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.IOable;

public class SyncQueudPipe<T> implements IOable<T, T> {
	private BlockingQueue<Object> _queue;
	
	private Object _endSentinal;
	
	public SyncQueudPipe(){
		_queue = new LinkedBlockingQueue<Object>();
		_endSentinal = new Object();
	}

	@SuppressWarnings("unchecked")
	@Override
	public T read() throws StreamCorruptedException {
		try {
			Object value = _queue.take();
			
			if(value.equals(_endSentinal)) {
				return null;
			} else {
				return (T) value;
			}
			
		} catch (InterruptedException e) {
			e.printStackTrace();
			throw new StreamCorruptedException();
		}
	}

	@Override
	public void write(T value) throws StreamCorruptedException {
		try {
			if(value == null) {
				_queue.put(_endSentinal);
			}
			_queue.put(value);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
