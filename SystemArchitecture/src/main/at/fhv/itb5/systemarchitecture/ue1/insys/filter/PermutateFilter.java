package main.at.fhv.itb5.systemarchitecture.ue1.insys.filter;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.LinkedList;

import main.at.fhv.itb5.systemarchitecture.ue1.insys.dao.WordLine;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.AbstractFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class PermutateFilter extends AbstractFilter<WordLine, LinkedList<WordLine>> {

	public PermutateFilter(Writeable<LinkedList<WordLine>> output) throws InvalidParameterException {
		super(output);
	}

	@Override
	public LinkedList<WordLine> read() throws StreamCorruptedException {
		// TODO Auto-generated method stub
		return null;
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
