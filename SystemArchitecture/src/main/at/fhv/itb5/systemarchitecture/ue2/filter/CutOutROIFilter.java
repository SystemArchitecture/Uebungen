package main.at.fhv.itb5.systemarchitecture.ue2.filter;

import java.awt.Rectangle;
import java.awt.image.RenderedImage;
import java.io.StreamCorruptedException;

import javax.media.jai.PlanarImage;

import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.EndOfStreamException;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.AbstractFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class CutOutROIFilter extends AbstractFilter<PlanarImage, PlanarImage> implements Runnable{

	private Rectangle _roi;

	public CutOutROIFilter(Rectangle roi, Readable<PlanarImage> input) {
		super(input);
		_roi = roi;
	}

	public CutOutROIFilter(Rectangle roi, Writeable<PlanarImage> output) {
		super(output);
		_roi = roi;
	}
	
	public CutOutROIFilter(Rectangle roi, Readable<PlanarImage> input, Writeable<PlanarImage> output) {
		super(input, output);
		_roi = roi;
	}

	@Override
	public PlanarImage read() throws StreamCorruptedException, EndOfStreamException {
		return cutOutROI(readInput());
	}

	@Override
	public void write(PlanarImage value) throws StreamCorruptedException {
		writeOutput(cutOutROI(value));
	}

	private PlanarImage cutOutROI(PlanarImage input) {
		return PlanarImage
				.wrapRenderedImage((RenderedImage) input.getAsBufferedImage(_roi, input.getColorModel()));
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
