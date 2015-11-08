package main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter.sink.consol;

import java.io.StreamCorruptedException;
import java.util.LinkedList;

import main.at.fhv.itb5.systemarchitecture.ue1.indsys.dto.WordLine;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.sink.SinkPassive;

public class ConsoleSinkPassive implements SinkPassive<LinkedList<WordLine>>{

	@Override
	public void write(LinkedList<WordLine> value) throws StreamCorruptedException {
		if(value != null) {
			for(WordLine wordline : value) {
				System.out.println(wordline);
			}
		}
	}
}
