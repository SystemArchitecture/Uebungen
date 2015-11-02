package main.at.fhv.itb5.systemarchitecture.ue1.insys.filter;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.LinkedList;

import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.AbstractFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class WordSeperatorFilter extends AbstractFilter<String, LinkedList<String>> {
	public WordSeperatorFilter(Readable<String> input, Writeable<LinkedList<String>> output)
			throws InvalidParameterException {
		super(input, output);
	}
	
	public WordSeperatorFilter(Writeable<LinkedList<String>> output) {
		super(output);
	}
	
	//TODO: add input constructor

	@Override
	public LinkedList<String> read() throws StreamCorruptedException {
		// TODO implement other direction
		return null;
	}

	@Override
	public void write(String value) throws StreamCorruptedException {
		writeOutput(toWordList(value));
	}
	
	private LinkedList<String> toWordList(String line) {
		String reg = "\\s+";
		LinkedList<String> result = new LinkedList<>(Arrays.asList(line.split(reg)));
		return result;
	}
}
