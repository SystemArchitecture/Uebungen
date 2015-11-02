package main.at.fhv.itb5.systemarchitecture.ue1.insys.filter;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.HashSet;

import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.AbstractFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class CharacterFilter extends AbstractFilter<String, String> {

	private HashSet<Character> _toFilter;

	public CharacterFilter(Writeable<String> output) throws InvalidParameterException {
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
		_toFilter.add(Character.valueOf('\t'));
		_toFilter.add(Character.valueOf('\n'));
		_toFilter.add(Character.valueOf('\r'));
	}
	// add set of all unimportant characters

	@Override
	public String read() throws StreamCorruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write(String value) throws StreamCorruptedException {
		String filterLine = filterCharacters(value);
		if (filterLine != null) {
			writeOutput(filterCharacters(value));
		}
	}

	private String filterCharacters(String line) {
		if (line.isEmpty()) {
			return null;
		}

		boolean firstWord = false;

		StringBuilder stringBuilder = new StringBuilder();
		for (char c : line.toCharArray()) {
			if (firstWord) {
				if (!_toFilter.contains(c)) {
					stringBuilder.append(c);
				}
			}
		}

		return stringBuilder.toString();
	}
}
