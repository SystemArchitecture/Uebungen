package main.at.fhv.itb5.systemarchitecture.ue1.indsys.sink.file.passive;

import java.io.File;
import java.io.StreamCorruptedException;

import main.at.fhv.itb5.systemarchitecture.ue1.indsys.sink.file.FileSink;

public class StringFileSinkPassive extends FileSink<String> {

	public StringFileSinkPassive(File file) {
		super(file);
	}

	@Override
	public void write(String value) throws StreamCorruptedException {
		saveLine(value);
	}
}
