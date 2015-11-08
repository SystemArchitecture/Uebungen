package main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;

import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.EndOfStreamException;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.AbstractFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class WordConstructorFilter extends AbstractFilter<Character, String> {

	public WordConstructorFilter(Writeable<String> output) throws InvalidParameterException {
		super(output);
	}
	
	public WordConstructorFilter(Readable<Character> input) {
		super(input);
	}

	@Override
	public String read() throws StreamCorruptedException, EndOfStreamException {
		// TODO Auto-generated method stub
		return null;
	}

	private StringBuilder wordBuilder;

	@Override
	public void write(Character value) throws StreamCorruptedException {
		if (value != ENDING_SIGNAL) {
			if (wordBuilder == null) {
				wordBuilder = new StringBuilder();
			}

			// filter out '\n'
			if (value != '\n') {
				if (value != ' ') {
					wordBuilder.append(value);
				} else if (wordBuilder.length() != 0) {
					String word = wordBuilder.toString();
					wordBuilder = null;
					writeOutput(word);

				}
			}
		} else {
			sendEndSignal();
		}

	}

}
