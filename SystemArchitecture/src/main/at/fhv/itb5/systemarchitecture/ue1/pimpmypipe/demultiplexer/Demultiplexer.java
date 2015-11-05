package main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.demultiplexer;

import java.io.StreamCorruptedException;
import java.util.List;

import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class Demultiplexer<T> implements Writeable<T> {

	private List<Writeable<T>> _sucessors;
	
	public Demultiplexer(List<Writeable<T>> successors) {
		_sucessors = successors;
	}
	
	@Override
	public void write(T value) throws StreamCorruptedException {
		for(Writeable<T> sucessor : _sucessors) {
			sucessor.write(value);
		}
	}

}
