package main.at.fhv.itb5.systemarchitecture.ue2.sink;

import java.io.File;
import java.io.StreamCorruptedException;

import main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter.sink.file.FileSink;

public class FileSinkStringPassive extends FileSink<String> {

	public FileSinkStringPassive(File file) {
		super(file);
	}

	@Override
	public void write(String value) throws StreamCorruptedException {
		System.out.println(value);
		saveLine(value);
	}
}
