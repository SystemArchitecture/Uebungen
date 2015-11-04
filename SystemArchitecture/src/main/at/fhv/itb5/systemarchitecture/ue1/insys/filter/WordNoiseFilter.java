package main.at.fhv.itb5.systemarchitecture.ue1.insys.filter;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;

import main.at.fhv.itb5.systemarchitecture.ue1.insys.dao.WordLine;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.AbstractFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class WordNoiseFilter extends AbstractFilter<WordLine, WordLine> {

	public WordNoiseFilter(Writeable<WordLine> output) throws InvalidParameterException {
		super(output);
	}

	// TODO: add read constructor

	@Override
	public WordLine read() throws StreamCorruptedException {
		// TODO implement other direction
		return null;
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

		return input;
	}
}
