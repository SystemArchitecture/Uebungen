package main.at.fhv.itb5.systemarchitecture.ue2;

import java.awt.image.renderable.ParameterBlock;
import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;

import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.EndOfStreamException;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.AbstractFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class ThresholdFilter extends AbstractFilter<PlanarImage, PlanarImage> {

	public ThresholdFilter(Readable<PlanarImage> input) throws InvalidParameterException {
		super(input);
	}

	public ThresholdFilter(Writeable<PlanarImage> output) throws InvalidParameterException {
		super(output);
	}

	@Override
	public PlanarImage read() throws StreamCorruptedException, EndOfStreamException {
		return threshold(readInput());
	}

	@Override
	public void write(PlanarImage value) throws StreamCorruptedException {
		writeOutput(threshold(value));
	}

	private PlanarImage threshold(PlanarImage input) {
		ParameterBlock pb = new ParameterBlock();
		double[] min = {0, 0, 0};
		double[] max = {50, 50, 50};
		double[] cons = {255, 255, 255};
		pb.addSource(input);
		pb.add(min);
		pb.add(max);
		pb.add(cons);
		PlanarImage result = JAI.create("threshold", pb);
		return result;
	}
}
