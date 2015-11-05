package main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.HashSet;

import main.at.fhv.itb5.systemarchitecture.ue1.indsys.dao.SimpleLine;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.EndOfStreamException;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.AbstractFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class CharacterFilter extends AbstractFilter<SimpleLine, SimpleLine> {

	private HashSet<Character> _toFilter;

	public CharacterFilter(Writeable<SimpleLine> output) throws InvalidParameterException {
		super(output);
		_toFilter = new HashSet<>();
		_toFilter.add(',');
		_toFilter.add('!');
		_toFilter.add('?');
		_toFilter.add('.');
		_toFilter.add('-');
		_toFilter.add('"');
		_toFilter.add('.');
		_toFilter.add('(');
		_toFilter.add(')');
		_toFilter.add('*');
		_toFilter.add(';');
		_toFilter.add(':');
		_toFilter.add(Character.valueOf('\t'));
		_toFilter.add(Character.valueOf('\n'));
		_toFilter.add(Character.valueOf('\r'));
	}
	// add set of all unimportant characters

	@Override
	public SimpleLine read() throws StreamCorruptedException, EndOfStreamException {
		return filterCharacters(readInput());
	}

	@Override
	public void write(SimpleLine value) throws StreamCorruptedException {
		if(value != ENDING_SIGNAL) {
			SimpleLine filterLine = filterCharacters(value);
			if (filterLine != null) {
				writeOutput(filterLine);
			} 
		} else {
			sendEndSignal();
		}
		
	}

	private SimpleLine filterCharacters(SimpleLine line) {
		if (line.isEmpty()) {
			return null;
		}

		StringBuilder stringBuilder = new StringBuilder();
		for (char c : line.getValue().toCharArray()) {

			if (!_toFilter.contains(c)) {
				stringBuilder.append(c);
			}
		}

		return new SimpleLine(stringBuilder.toString(), line.getLinenumber());
	}
}
