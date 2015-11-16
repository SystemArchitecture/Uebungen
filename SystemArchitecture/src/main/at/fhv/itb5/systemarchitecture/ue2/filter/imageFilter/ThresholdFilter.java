package main.at.fhv.itb5.systemarchitecture.ue2.filter.imageFilter;

import java.awt.image.renderable.ParameterBlock;
import java.security.InvalidParameterException;
import javax.media.jai.PlanarImage;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class ThresholdFilter extends PlanarImageFilter implements Runnable{

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
		double[] min = {0, 0, 0};
		double[] max = {35, 35, 35};
		double[] cons = {255, 255, 255};
		pb.add(min);
		pb.add(max);
		pb.add(cons);
		return pb;
	}

	@Override
	protected String getOperatorName() {
		return "threshold";
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
}
