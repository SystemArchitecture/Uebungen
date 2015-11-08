package main.at.fhv.itb5.systemarchitecture.ue1.indsys.filter;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;

import main.at.fhv.itb5.systemarchitecture.ue1.indsys.dto.SimpleLine;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.EndOfStreamException;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.AbstractFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class StreamToFileFilter extends AbstractFilter<SimpleLine, String> {

	public StreamToFileFilter(Writeable<String> output) throws InvalidParameterException {
		super(output);
	}
	
	public StreamToFileFilter(Readable<SimpleLine> input) {
		super(input);
	}

	@Override
	public String read() throws StreamCorruptedException, EndOfStreamException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write(SimpleLine value) throws StreamCorruptedException {
		// TODO Auto-generated method stub
		
	}

}
