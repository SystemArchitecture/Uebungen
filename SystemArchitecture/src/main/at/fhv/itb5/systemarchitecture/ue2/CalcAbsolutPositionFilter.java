package main.at.fhv.itb5.systemarchitecture.ue2;

import java.awt.Rectangle;
import java.security.InvalidParameterException;
import java.util.LinkedList;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.DataTransformationFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;
import main.at.fhv.itb5.systemarchitecture.ue2.dto.Coordinate;

public class CalcAbsolutPositionFilter extends DataTransformationFilter<LinkedList<Coordinate>> {
	
	private Rectangle _roi;
	
	public CalcAbsolutPositionFilter(Rectangle roi, Readable<LinkedList<Coordinate>> input) throws InvalidParameterException {
		super(input);
		_roi = roi;
	}
	
	public CalcAbsolutPositionFilter(Rectangle roi, Writeable<LinkedList<Coordinate>> output) throws InvalidParameterException {
		super(output);
		_roi = roi;
	}
	
	public CalcAbsolutPositionFilter(Rectangle roi, Readable<LinkedList<Coordinate>> input, Writeable<LinkedList<Coordinate>> output) throws InvalidParameterException {
		super(input, output);
		_roi = roi;
	}
	
	protected LinkedList<Coordinate> process(LinkedList<Coordinate> input) {
		LinkedList<Coordinate> output = new LinkedList<>();
		
		for(Coordinate coordinate : input) {
			output.add(new Coordinate(coordinate._x + _roi.x, coordinate._y + _roi.y));
		}
		System.out.println("Calculate Absolut Positon");
		return output;
	}
}
