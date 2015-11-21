package main.at.fhv.itb5.systemarchitecture.ue2.filter;

import java.security.InvalidParameterException;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;
import main.at.fhv.itb5.systemarchitecture.ue2.filter.imageFilter.PlanarImageFilter;

public class SaveFastForwardFilter extends PlanarImageFilter{

	private String _outputFilePath;

	public SaveFastForwardFilter(String outputFilePath, Readable<PlanarImage> input) throws InvalidParameterException {
		super(input);
		_outputFilePath = outputFilePath;
	}
	
	public SaveFastForwardFilter(String outputFilePath, Writeable<PlanarImage> output) throws InvalidParameterException {
		super(output);
		_outputFilePath = outputFilePath;
	}
	
	public SaveFastForwardFilter(String outputFilePath, Readable<PlanarImage> input, Writeable<PlanarImage> output) throws InvalidParameterException {
		super(input, output);
		_outputFilePath = outputFilePath;
	}

	@Override
	protected PlanarImage process(PlanarImage input) {
		JAI.create("filestore", input, _outputFilePath, "jpeg");
		return input;
	}
}
