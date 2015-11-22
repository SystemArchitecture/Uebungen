package main.at.fhv.itb5.systemarchitecture.ue2.filter;

import java.awt.Rectangle;
import java.awt.image.RenderedImage;
import javax.media.jai.PlanarImage;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;
import main.at.fhv.itb5.systemarchitecture.ue2.filter.imageFilter.PlanarImageFilter;

public class CutOutROIFilter extends PlanarImageFilter{

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
	protected PlanarImage process(PlanarImage input) {
			return PlanarImage
					.wrapRenderedImage((RenderedImage) input.getAsBufferedImage(_roi, input.getColorModel()));
	}
}
