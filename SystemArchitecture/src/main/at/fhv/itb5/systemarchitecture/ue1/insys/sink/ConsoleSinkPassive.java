package main.at.fhv.itb5.systemarchitecture.ue1.insys.sink;

import java.io.StreamCorruptedException;
import java.util.LinkedList;

import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.sink.SinkPassive;

public class ConsoleSinkPassive implements SinkPassive<LinkedList<String>>{

	@Override
	public void write(LinkedList<String> value) throws StreamCorruptedException {
		if(value != null) {
			System.out.println(value);
		}
	}
}
