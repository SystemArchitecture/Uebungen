package main.at.fhv.itb5.systemarchitecture.ue1.insys.filter;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.LinkedList;

import main.at.fhv.itb5.systemarchitecture.ue1.insys.dao.WordLine;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.AbstractFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class PerlmutateFilter extends AbstractFilter<WordLine, LinkedList<WordLine>> {

	public PerlmutateFilter(Writeable<LinkedList<WordLine>> output) throws InvalidParameterException {
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
			writeOutput(perlmutate(value));
		} else {
			sendEndSignal();
		}
		
	}
	
	private LinkedList<WordLine> perlmutate(WordLine value) {
		LinkedList<WordLine> perlmutations = new LinkedList<>();
		perlmutations.add(value);
		
		//TODO: perlmutations
		
		return perlmutations;
	}

	

}
