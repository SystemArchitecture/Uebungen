package main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces;

import java.io.StreamCorruptedException;

import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.EndOfStreamException;

public interface Readable<T> {
	public T read() throws StreamCorruptedException, EndOfStreamException;
}
