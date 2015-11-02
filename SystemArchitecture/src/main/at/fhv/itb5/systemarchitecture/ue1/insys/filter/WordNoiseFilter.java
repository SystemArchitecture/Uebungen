package main.at.fhv.itb5.systemarchitecture.ue1.insys.filter;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.LinkedList;

import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.AbstractFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class WordNoiseFilter extends AbstractFilter<LinkedList<String>, LinkedList<String>>{

	public WordNoiseFilter(Writeable<LinkedList<String>> output) throws InvalidParameterException {
		super(output);
	}
	
	//TODO: add read constructor

	@Override
	public LinkedList<String> read() throws StreamCorruptedException {
		// TODO implement other direction
		return null;
	}

	@Override
	public void write(LinkedList<String> value) throws StreamCorruptedException {
		LinkedList<String> clearResult = clearUpList(value);
		
		if(clearResult != null) {
			writeOutput(clearResult);
		}
	}
	
	private LinkedList<String> clearUpList(LinkedList<String> input) {
		for(int i = 0; i < input.size(); ++i) {
			if(input.get(i).isEmpty() || input.get(i) == " ") {
				input.remove(i);
			}
		}
		
		return input;
	}
}
