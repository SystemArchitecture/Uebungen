package main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.source;

import java.io.StreamCorruptedException;

import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.ActiveElement;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public abstract class SourceActive<T> extends ActiveElement implements Writeable<T> {
	protected Writeable<T> _successor;
	
	public SourceActive(Writeable<T> successor) {
		_successor = successor;
	}
	
	@Override
	public void write(T value) throws StreamCorruptedException {
		_successor.write(value);
	}
}
