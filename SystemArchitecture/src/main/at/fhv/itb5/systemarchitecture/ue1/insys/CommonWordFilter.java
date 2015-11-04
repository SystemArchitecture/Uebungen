package main.at.fhv.itb5.systemarchitecture.ue1.insys;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.LinkedList;

import main.at.fhv.itb5.systemarchitecture.ue1.insys.dao.WordLine;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.AbstractFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class CommonWordFilter extends AbstractFilter<LinkedList<WordLine>, LinkedList<WordLine>> {

	public CommonWordFilter(Writeable<LinkedList<WordLine>> output) throws InvalidParameterException {
		super(output);
	}

	@Override
	public LinkedList<WordLine> read() throws StreamCorruptedException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write(LinkedList<WordLine> value) throws StreamCorruptedException {
		//TODO: filter for common words
		writeOutput(value);
	}
}