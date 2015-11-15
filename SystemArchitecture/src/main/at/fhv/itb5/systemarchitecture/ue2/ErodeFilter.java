package main.at.fhv.itb5.systemarchitecture.ue2;

import java.awt.image.renderable.ParameterBlock;
import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;

import javax.media.jai.JAI;
import javax.media.jai.KernelJAI;
import javax.media.jai.PlanarImage;

import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.EndOfStreamException;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.AbstractFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class ErodeFilter extends AbstractFilter<PlanarImage, PlanarImage> {

	public ErodeFilter(Readable<PlanarImage> input) throws InvalidParameterException {
		super(input);
	}
	
	public ErodeFilter(Writeable<PlanarImage> output) throws InvalidParameterException {
		super(output);
	}

	@Override
	public PlanarImage read() throws StreamCorruptedException, EndOfStreamException {
		return erode(readInput());
	}

	@Override
	public void write(PlanarImage value) throws StreamCorruptedException {
		writeOutput(erode(value));
	}

	private PlanarImage erode(PlanarImage input){
		float[] kernelMatrix = {
		        0, 0, 1, 0, 0,
		        0, 1, 1, 1, 0,
		        1, 1, 1, 1, 1,
		        0, 1, 1, 1, 0,
		        0, 0, 1, 0, 0
		    };
		
		// Create the kernel using the array.
		KernelJAI kernel = new KernelJAI(5,5,kernelMatrix);
		// Create a ParameterBlock with that kernel and image.
		ParameterBlock p = new ParameterBlock();

		p = new ParameterBlock();
		p.addSource(input);
		p.add(kernel);
		input = JAI.create("erode",p,null);
		
		p = new ParameterBlock();
		p.addSource(input);
		p.add(kernel);
		input = JAI.create("erode",p,null);
		
		p = new ParameterBlock();
		p.addSource(input);
		p.add(kernel);
		input = JAI.create("erode",p,null);
		
		// Return the pre-processed image.
		return input;
	}
}
