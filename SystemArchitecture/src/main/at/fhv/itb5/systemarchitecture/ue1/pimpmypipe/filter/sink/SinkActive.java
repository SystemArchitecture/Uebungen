package main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.sink;

import java.io.StreamCorruptedException;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.ActiveElement;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;

public abstract class SinkActive<T> extends ActiveElement implements Readable<T>{
	protected Readable<T> _predeseccor;
	
	public Object ENDING_SIGNAL = null;
	
	public SinkActive(Readable<T> predeseccor) {
		_predeseccor = predeseccor;
	}
	
	@Override
	public T read() throws StreamCorruptedException {
		return _predeseccor.read();
	}
}
