package main.at.fhv.itb5.systemarchitecture.ue2.filter.imageFilter;

import java.awt.image.renderable.ParameterBlock;
import java.security.InvalidParameterException;
import javax.media.jai.KernelJAI;
import javax.media.jai.PlanarImage;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class ErodeFilter extends JAIPlanarImageFilter {

	public ErodeFilter(Readable<PlanarImage> input) throws InvalidParameterException {
		super(input);
	}
	
	public ErodeFilter(Writeable<PlanarImage> output) throws InvalidParameterException {
		super(output);
	}
	
	public ErodeFilter(Readable<PlanarImage> input, Writeable<PlanarImage> output)
			throws InvalidParameterException {
		super(input, output);
	}

	@Override
	protected ParameterBlock getParameterBlock() {
		float[] kernelMatrix = {
		        0, 0, 1, 1, 0, 0,
		        0, 1, 1, 1, 1, 0,
		        1, 1, 1, 1, 1, 1,
		        1, 1, 1, 1, 1, 1,
		        0, 1, 1, 1, 1, 0,
		        0, 0, 1, 1, 0, 0,
		    };
		
		KernelJAI kernel = new KernelJAI(6, 6, kernelMatrix);
		ParameterBlock parameterBlock = new ParameterBlock();
		parameterBlock.add(kernel);
		
		return parameterBlock;
	}

	@Override
	protected String getOperatorName() {
		return "erode";
	}
}
