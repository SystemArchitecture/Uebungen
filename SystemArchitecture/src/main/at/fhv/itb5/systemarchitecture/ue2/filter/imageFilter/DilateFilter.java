package main.at.fhv.itb5.systemarchitecture.ue2.filter.imageFilter;

import java.awt.image.renderable.ParameterBlock;
import java.security.InvalidParameterException;
import javax.media.jai.KernelJAI;
import javax.media.jai.PlanarImage;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class DilateFilter extends PlanarImageFilter {

	public DilateFilter(Readable<PlanarImage> input) throws InvalidParameterException {
		super(input);
	}
	
	public DilateFilter(Writeable<PlanarImage> output) throws InvalidParameterException {
		super(output);
	}
	
	public DilateFilter(Readable<PlanarImage> input, Writeable<PlanarImage> output)
			throws InvalidParameterException {
		super(input, output);
	}

	@Override
	protected ParameterBlock getParameterBlock() {
		float[] kernelMatrix = {
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1
		    };
		
		KernelJAI kernel = new KernelJAI(5,5,kernelMatrix);
		ParameterBlock parameterblock = new ParameterBlock();
		parameterblock.add(kernel);
		
		return parameterblock;
	}

	@Override
	protected String getOperatorName() {
		return "dilate";
	}
}
