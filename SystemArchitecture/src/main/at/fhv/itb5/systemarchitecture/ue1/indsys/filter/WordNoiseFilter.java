package main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;

import main.at.fhv.itb5.systemarchitecture.ue1.indsys.dao.WordLine;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.EndOfStreamException;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.AbstractFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class WordNoiseFilter extends AbstractFilter<WordLine, WordLine> {

	public WordNoiseFilter(Writeable<WordLine> output) throws InvalidParameterException {
		super(output);
	}

	public WordNoiseFilter(Readable<WordLine> input) {
		super(input);
	}

	@Override
	public WordLine read() throws StreamCorruptedException, EndOfStreamException {
		return clearUpList(readInput());
	}

	@Override
	public void write(WordLine value) throws StreamCorruptedException {
		if (value != ENDING_SIGNAL) {
			WordLine clearResult = clearUpList(value);

			if (clearResult != null) {
				writeOutput(clearResult);
			}
		} else {
			sendEndSignal();
		}
	}

	private WordLine clearUpList(WordLine input) {
		for (int i = 0; i < input.getWords().size(); ++i) {
			if (input.getWords().get(i).isEmpty() || input.getWords().get(i) == " ") {
				input.getWords().remove(i);
			}
		}
		
		if(input.getWords().isEmpty()) {
			return null;
		}

		return input;
	}
}