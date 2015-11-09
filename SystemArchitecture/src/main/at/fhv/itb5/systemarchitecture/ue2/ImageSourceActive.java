package main.at.fhv.itb5.systemarchitecture.ue2;

import java.io.StreamCorruptedException;

import javax.media.jai.PlanarImage;

import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.source.SourceActive;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class ImageSourceActive extends SourceActive<PlanarImage> {

	private PlanarImage _sourceImage;

	public ImageSourceActive(PlanarImage sourceImage, Writeable<PlanarImage> successor) {
		super(successor);
		_sourceImage = sourceImage;
	}

	@Override
	public void process() {

		try {
			if (_sourceImage == null) {
				write(null);
			} else {
				System.out.println("Write Image");
				write(_sourceImage);
				_sourceImage = null;
				stop();
			}

		} catch (StreamCorruptedException e) {
			System.out.println(e.getMessage());
			stop();
		}
	}

}
