package main.at.fhv.itb5.systemarchitecture.ue2.filter.imageFilter;

import java.awt.image.renderable.ParameterBlock;
import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.EndOfStreamException;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.AbstractFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public abstract class PlanarImageFilter extends AbstractFilter<PlanarImage, PlanarImage>{
	
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
	public PlanarImage read() throws StreamCorruptedException, EndOfStreamException {
		return processImage(readInput());
	}

	@Override
	public void write(PlanarImage value) throws StreamCorruptedException {
		writeOutput(processImage(value));
	}
	
	private PlanarImage processImage(PlanarImage input) {
		ParameterBlock parameterBlock = getParameterBlock();
		parameterBlock.addSource(input);
		
		return JAI.create(getOperatorName(), parameterBlock);
	}
	
	protected abstract ParameterBlock getParameterBlock();
	
	protected abstract String getOperatorName();

}
