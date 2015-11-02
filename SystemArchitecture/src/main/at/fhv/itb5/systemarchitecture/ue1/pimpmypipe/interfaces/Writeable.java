package main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces;

import java.io.StreamCorruptedException;

public interface Writeable<T> {
	public void write(T value) throws StreamCorruptedException;
}
