package main.at.fhv.itb5.systemarchitecture.ue2;

import java.awt.image.renderable.ParameterBlock;
import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.media.jai.operator.MedianFilterDescriptor;

import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.EndOfStreamException;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.AbstractFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class MedianFilter extends AbstractFilter<PlanarImage, PlanarImage> {

	public MedianFilter(Readable<PlanarImage> input) throws InvalidParameterException {
		super(input);
	}
	
	public MedianFilter(Writeable<PlanarImage> input) throws InvalidParameterException {
		super(input);
	}

	@Override
	public PlanarImage read() throws StreamCorruptedException, EndOfStreamException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write(PlanarImage value) throws StreamCorruptedException {
		writeOutput(median(value));
	}
	
	private PlanarImage median(PlanarImage input) {
		ParameterBlock pb = new ParameterBlock();
		pb.addSource(input);
		pb.add(MedianFilterDescriptor.MEDIAN_MASK_SQUARE);
		pb.add(4);
		PlanarImage result = JAI.create("medianfilter", pb);
		return result;
	}

}
