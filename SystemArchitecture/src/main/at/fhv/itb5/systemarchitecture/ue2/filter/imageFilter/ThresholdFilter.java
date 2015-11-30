package main.at.fhv.itb5.systemarchitecture.ue2.filter.imageFilter;

import java.awt.image.renderable.ParameterBlock;
import java.security.InvalidParameterException;
import javax.media.jai.PlanarImage;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class ThresholdFilter extends JAIPlanarImageFilter{
	private double[] _min = {0, 0, 0};
	private double[] _max = {35, 35, 35};
	private double[] _cons = {255, 255, 255};

	public ThresholdFilter(Readable<PlanarImage> input, double[] min, double[] max, double[] cons)
			throws InvalidParameterException {
		super(input);
		_min = min;
		_max = max;
		_cons = cons;
	}

	public ThresholdFilter(Readable<PlanarImage> input) throws InvalidParameterException {
		super(input);
	}

	public ThresholdFilter(Writeable<PlanarImage> output) throws InvalidParameterException {
		super(output);
	}
	
	public ThresholdFilter(Readable<PlanarImage> input, Writeable<PlanarImage> output)
			throws InvalidParameterException {
		super(input, output);
	}

	@Override
	protected ParameterBlock getParameterBlock() {
		ParameterBlock pb = new ParameterBlock();
		pb.add(_min);
		pb.add(_max);
		pb.add(_cons);
		return pb;
	}

	@Override
	protected String getOperatorName() {
		return "threshold";
	}
}
