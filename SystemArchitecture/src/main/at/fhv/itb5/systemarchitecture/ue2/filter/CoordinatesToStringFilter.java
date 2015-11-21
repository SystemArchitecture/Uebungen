package main.at.fhv.itb5.systemarchitecture.ue2.filter;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import java.util.LinkedList;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.AbstractFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;
import main.at.fhv.itb5.systemarchitecture.ue2.dto.Coordinate;

public class CoordinatesToStringFilter extends AbstractFilter<LinkedList<Coordinate>, String> implements Runnable {

	public CoordinatesToStringFilter(Readable<LinkedList<Coordinate>> input) throws InvalidParameterException {
		super(input);
	}

	public CoordinatesToStringFilter(Writeable<String> output) {
		super(output);
	}

	public CoordinatesToStringFilter(Readable<LinkedList<Coordinate>> input, Writeable<String> output) {
		super(input, output);
	}

	@Override
	public String read() throws StreamCorruptedException {
		return process(readInput());
	}

	@Override
	public void write(LinkedList<Coordinate> value) throws StreamCorruptedException {
		writeOutput(process(value));
	}

	protected String process(LinkedList<Coordinate> input) {
		StringBuilder output = new StringBuilder();
		for (Coordinate co : input) {
			output.append(co.toString());
		}
		return output.toString();
	}

	@Override
	public void run() {
		LinkedList<Coordinate> value;
		try {
			do {
				value = readInput();
				if (value != null) {
					try {
						writeOutput(process(value));
					} catch (StreamCorruptedException e) {
						value = null;
					}
				}
			} while (value != null);

			sendEndSignal();
		} catch (StreamCorruptedException e) {
			e.printStackTrace();
		}
	}
}
