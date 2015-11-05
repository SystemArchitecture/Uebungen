package main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.LinkedList;

import main.at.fhv.itb5.systemarchitecture.ue1.indsys.dao.WordLine;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.EndOfStreamException;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.AbstractFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class PermutateFilter extends AbstractFilter<WordLine, LinkedList<WordLine>> {

	public PermutateFilter(Writeable<LinkedList<WordLine>> output) throws InvalidParameterException {
		super(output);
	}

	public PermutateFilter(Readable<WordLine> input) {
		super(input);
	}
	
	@Override
	public LinkedList<WordLine> read() throws StreamCorruptedException, EndOfStreamException {
		return permutate(readInput());
	}
	
	@Override
	public void write(WordLine value) throws StreamCorruptedException {
		if(value != ENDING_SIGNAL) {
			writeOutput(permutate(value));
		} else {
			sendEndSignal();
		}
		
	}
	
	private LinkedList<WordLine> permutate(WordLine value) {
		LinkedList<WordLine> permutations = new LinkedList<>();
		WordLine newValue = value;
		permutations.add(newValue);
		
		for(int i = 0; i<value.getWords().size()-1; i++){
			newValue = (WordLine) newValue.clone();
			newValue.getWords().add(newValue.getWords().removeFirst());
			permutations.add(newValue);
		}
		
		return permutations;
	}

	

}
