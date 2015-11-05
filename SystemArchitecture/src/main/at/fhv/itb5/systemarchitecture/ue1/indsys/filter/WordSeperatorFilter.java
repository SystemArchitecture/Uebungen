package main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.LinkedList;

import main.at.fhv.itb5.systemarchitecture.ue1.indsys.dao.SimpleLine;
import main.at.fhv.itb5.systemarchitecture.ue1.indsys.dao.WordLine;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.AbstractFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class WordSeperatorFilter extends AbstractFilter<SimpleLine, WordLine> {
	public WordSeperatorFilter(Readable<SimpleLine> input, Writeable<WordLine> output)
			throws InvalidParameterException {
		super(input, output);
	}
	
	public WordSeperatorFilter(Writeable<WordLine> output) {
		super(output);
	}

	@Override
	public WordLine read() throws StreamCorruptedException {
		// TODO implement other direction
		return null;
	}

	@Override
	public void write(SimpleLine value) throws StreamCorruptedException {
		if(value != ENDING_SIGNAL) {
			writeOutput(toWordList(value));
		} else {
			sendEndSignal();
		}
	}
	
	private WordLine toWordList(SimpleLine line) {
		String reg = "\\s+";
		WordLine wordLine = new WordLine(new LinkedList<String>(Arrays.asList(line.getValue().split(reg))), line.getLinenumber());
		return wordLine;
	}
}
