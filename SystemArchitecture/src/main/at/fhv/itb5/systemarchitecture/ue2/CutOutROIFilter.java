package main.at.fhv.itb5.systemarchitecture.ue2;

import java.awt.Rectangle;
import java.awt.image.RenderedImage;
import java.io.StreamCorruptedException;

import javax.media.jai.PlanarImage;

import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.EndOfStreamException;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.AbstractFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class CutOutROIFilter extends AbstractFilter<PlanarImage, PlanarImage> {

	private Rectangle _roi;

	public CutOutROIFilter(Rectangle roi, Readable<PlanarImage> input) {
		super(input);
		_roi = roi;
	}

	public CutOutROIFilter(Rectangle roi, Writeable<PlanarImage> output) {
		super(output);
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
}
