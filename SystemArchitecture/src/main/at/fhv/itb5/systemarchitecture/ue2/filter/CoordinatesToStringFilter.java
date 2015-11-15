package main.at.fhv.itb5.systemarchitecture.ue2.filter;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.LinkedList;

import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.EndOfStreamException;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.AbstractFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;
import main.at.fhv.itb5.systemarchitecture.ue2.dto.Coordinate;

public class CoordinatesToStringFilter extends AbstractFilter<LinkedList<Coordinate>, String> {

	public CoordinatesToStringFilter(Readable<LinkedList<Coordinate>> input) throws InvalidParameterException {
		super(input);
	}
	
	public CoordinatesToStringFilter(Writeable<String> output) {
		super(output);
	}

	@Override
	public String read() throws StreamCorruptedException, EndOfStreamException {
		return coordinatesToString(readInput());
	}

	@Override
	public void write(LinkedList<Coordinate> value) throws StreamCorruptedException {
		writeOutput(coordinatesToString(value));
	}

	private String coordinatesToString(LinkedList<Coordinate> input){
		StringBuilder output = new StringBuilder();
		for(Coordinate co : input){
			output.append(co.toString());
		}
		return output.toString();
	}
}
