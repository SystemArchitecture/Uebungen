package main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces;

import java.io.StreamCorruptedException;

public interface Readable<T>  {
	public T read() throws StreamCorruptedException;
}
