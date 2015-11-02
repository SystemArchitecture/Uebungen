package main.at.fhv.itb5.systemarchitecture.ue1.insys.source;

import java.io.StreamCorruptedException;

import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.sink.SinkPassive;

public class ConsoleSinkPassive implements SinkPassive<String>{

	@Override
	public void write(String value) throws StreamCorruptedException {
		if(value != null) {
			System.out.println(value);
		}
	}
}
