package main.at.fhv.itb5.systemarchitecture.ue2.source;

import java.io.StreamCorruptedException;
import javax.media.jai.PlanarImage;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.source.SourcePassive;

public class ImageSourcePassive implements SourcePassive<PlanarImage> {

	private PlanarImage _sourceImage;
	
	public ImageSourcePassive(PlanarImage sourceImage) {
		_sourceImage = sourceImage;
	}
	
	@Override
	public PlanarImage read() throws StreamCorruptedException {
		PlanarImage sourceImage = _sourceImage;
		_sourceImage = null;
		
		return sourceImage;
	}
	
	

}
