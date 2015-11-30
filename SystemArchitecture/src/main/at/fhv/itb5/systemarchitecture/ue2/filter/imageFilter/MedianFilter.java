package main.at.fhv.itb5.systemarchitecture.ue2.filter.imageFilter;

import java.awt.image.renderable.ParameterBlock;
import java.security.InvalidParameterException;
import javax.media.jai.PlanarImage;
import javax.media.jai.operator.MedianFilterDescriptor;
import javax.media.jai.operator.MedianFilterShape;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class MedianFilter extends JAIPlanarImageFilter {
	private int _maskSize = 6;

	public MedianFilter(Readable<PlanarImage> input, int maskSize) throws InvalidParameterException {
		super(input);
		_maskSize = maskSize;
	}

	public MedianFilter(Readable<PlanarImage> input) throws InvalidParameterException {
		super(input);
	}

	public MedianFilter(Writeable<PlanarImage> input) throws InvalidParameterException {
		super(input);
	}

	public MedianFilter(Readable<PlanarImage> input, Writeable<PlanarImage> output) throws InvalidParameterException {
		super(input, output);
	}

	@Override
	protected ParameterBlock getParameterBlock() {
		MedianFilterShape medianFilterDescriptor = MedianFilterDescriptor.MEDIAN_MASK_SQUARE;

		ParameterBlock parameterBlock = new ParameterBlock();
		parameterBlock.add(medianFilterDescriptor);
		parameterBlock.add(_maskSize);
		return parameterBlock;
	}

	@Override
	protected String getOperatorName() {
		return "medianfilter";
	}
}
