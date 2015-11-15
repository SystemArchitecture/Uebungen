package main.at.fhv.itb5.systemarchitecture.ue2;

import java.awt.Rectangle;
import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.LinkedList;

import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.EndOfStreamException;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.AbstractFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;
import main.at.fhv.itb5.systemarchitecture.ue2.dto.Coordinate;

public class CalcAbsolutPositionFilter extends AbstractFilter<LinkedList<Coordinate>, LinkedList<Coordinate>> {
	
	private Rectangle _roi;
	
	public CalcAbsolutPositionFilter(Rectangle roi, Writeable<LinkedList<Coordinate>> output) throws InvalidParameterException {
		super(output);
		_roi = roi;
	}

	@Override
	public LinkedList<Coordinate> read() throws StreamCorruptedException, EndOfStreamException {
		return calculateAbsolutPosition(readInput());
	}

	@Override
	public void write(LinkedList<Coordinate> value) throws StreamCorruptedException {
		writeOutput(calculateAbsolutPosition(value));
	}
	
	private LinkedList<Coordinate> calculateAbsolutPosition(LinkedList<Coordinate> input) {
		LinkedList<Coordinate> output = new LinkedList<>();
		
		for(Coordinate coordinate : input) {
			output.add(new Coordinate(coordinate._x + _roi.x, coordinate._y + _roi.y));
		}
		
		return output;
	}

}
