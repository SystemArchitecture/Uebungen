package main.at.fhv.itb5.systemarchitecture.ue2.filter.imageFilter;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import javax.media.jai.PlanarImage;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.DataTransformationFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public abstract class PlanarImageFilter extends DataTransformationFilter<PlanarImage>{
	public PlanarImageFilter(Readable<PlanarImage> input) throws InvalidParameterException {
		super(input);
	}

	public PlanarImageFilter(Writeable<PlanarImage> output) throws InvalidParameterException {
		super(output);
	}
	
	public PlanarImageFilter(Readable<PlanarImage> input, Writeable<PlanarImage> output)
			throws InvalidParameterException {
		super(input, output);
	}

	@Override
	public PlanarImage read() throws StreamCorruptedException {
		return process(readInput());
	}

	@Override
	public void write(PlanarImage value) throws StreamCorruptedException {
		writeOutput(process(value));
	}
}
