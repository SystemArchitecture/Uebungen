package main.at.fhv.itb5.systemarchitecture.ue2.filter;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.LinkedList;
import java.util.List;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.AbstractFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;
import main.at.fhv.itb5.systemarchitecture.ue2.dto.Coordinate;

public class CoordiantErrorFilter extends AbstractFilter<LinkedList<Coordinate>, String>{

	public CoordiantErrorFilter(List<Coordinate> expectedValues, Writeable<String> output)
			throws InvalidParameterException {
		super(output);
		init(expectedValues);
	}
	
	public CoordiantErrorFilter(List<Coordinate> expectedValues, Readable<LinkedList<Coordinate>> input)
			throws InvalidParameterException {
		super(input);
		init(expectedValues);
	}
	
	public CoordiantErrorFilter(List<Coordinate> expectedValues, Readable<LinkedList<Coordinate>> input, Writeable<String> output)
			throws InvalidParameterException {
		super(input, output);
		init(expectedValues);
	}
	
	private List<Coordinate> _expectedValues;
	private void init(List<Coordinate> expectedValues) {
		_expectedValues = expectedValues;
	}

	@Override
	public String read() throws StreamCorruptedException {
		return process(readInput());
	}

	@Override
	public void write(LinkedList<Coordinate> value) throws StreamCorruptedException {
		writeOutput((process(value)));
	}
	
	public String process(LinkedList<Coordinate> input) {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("\n *** Board Evaluation *** \n");
		
		if(_expectedValues.size() != input.size()) {
			return "Not all sodering points were found";
		}
		
		for(int i = 0; i < input.size(); ++i) {
			stringBuilder.append("Actual: " + input.get(i) + "; Expected: " + _expectedValues.get(i) + " -> " + calculateCooridianteDistance(input.get(i), _expectedValues.get(i)) + "\n");
		}
		
		return stringBuilder.toString();
	}

	@Override
	public void run() {
		LinkedList<Coordinate> value = null;
	
		do {
			try {
				value = readInput();
				if(value != null) {
					writeOutput(process(value));
				}
			} catch (StreamCorruptedException e) {
				e.printStackTrace();
				value = null;
			}
		} while(value != null);
	}

	private double calculateCooridianteDistance(Coordinate coordinateA, Coordinate coordinateB) {
		return Math.sqrt(Math.pow(coordinateA._x - coordinateB._x, 2) + Math.pow(coordinateA._y - coordinateB._y,  2));
	}

}
