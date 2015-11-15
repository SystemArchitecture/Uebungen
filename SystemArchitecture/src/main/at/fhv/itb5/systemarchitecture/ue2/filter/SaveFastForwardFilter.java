package main.at.fhv.itb5.systemarchitecture.ue2.filter;

import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;

import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.EndOfStreamException;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.AbstractFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class SaveFastForwardFilter extends AbstractFilter<PlanarImage, PlanarImage> {

	private String _outputFilePath;

	public SaveFastForwardFilter(String outputFilePath, Readable<PlanarImage> input) throws InvalidParameterException {
		super(input);
		_outputFilePath = outputFilePath;
	}
	
	public SaveFastForwardFilter(String outputFilePath, Writeable<PlanarImage> output) throws InvalidParameterException {
		super(output);
		_outputFilePath = outputFilePath;
	}

	@Override
	public PlanarImage read() throws StreamCorruptedException, EndOfStreamException {
		return saveFastForward(readInput());
	}

	@Override
	public void write(PlanarImage value) throws StreamCorruptedException {
		writeOutput(saveFastForward(value));
	}
	
	private PlanarImage saveFastForward(PlanarImage input){
		JAI.create("filestore", input, _outputFilePath, "jpeg");
		return input;
	}

}
