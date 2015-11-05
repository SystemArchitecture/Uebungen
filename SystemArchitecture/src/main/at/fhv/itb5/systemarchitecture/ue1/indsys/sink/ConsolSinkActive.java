package main.at.fhv.itb5.systemarchitecture.ue1.indsys.sink;

import java.io.StreamCorruptedException;
import java.util.LinkedList;

import main.at.fhv.itb5.systemarchitecture.ue1.indsys.dao.WordLine;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.EndOfStreamException;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.sink.SinkActive;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;

public class ConsolSinkActive extends SinkActive<LinkedList<WordLine>> {

	public ConsolSinkActive(Readable<LinkedList<WordLine>> predeseccor) {
		super(predeseccor);
	}

	@Override
	public void process() {

		try {
			LinkedList<WordLine> lines = read();
			if(lines != null) {
				for (WordLine wordLine : lines) {
					System.out.println(wordLine);
				}
			}
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
			stop();
		} catch (EndOfStreamException e) {
			e.printStackTrace();
			stop();
		}
	}

}
