package main.at.fhv.itb5.systemarchitecture.ue2.pipe;

import java.io.StreamCorruptedException;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.EndOfStreamException;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.IOable;

public class SyncBufferPipe<T> implements IOable<T, T> {
	private BlockingQueue<T> _queue;
	
	public SyncBufferPipe(){
		_queue = new LinkedBlockingQueue<T>();
	}

	@Override
	public T read() throws StreamCorruptedException, EndOfStreamException {
		return _queue.poll();
	}

	@Override
	public void write(T value) throws StreamCorruptedException {
		try {
			_queue.put(value);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
