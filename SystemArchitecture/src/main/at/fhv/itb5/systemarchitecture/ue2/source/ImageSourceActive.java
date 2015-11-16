package main.at.fhv.itb5.systemarchitecture.ue2.source;

import java.io.StreamCorruptedException;
import javax.media.jai.PlanarImage;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.EndOfStreamException;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.AbstractFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.sink.SinkActive;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.source.SourceActive;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class ImageSourceActive extends SourceActive<PlanarImage> implements Runnable{
	private PlanarImage _sourceImage;

	public ImageSourceActive(PlanarImage sourceImage, Writeable<PlanarImage> successor) {
		super(successor);
		_sourceImage = sourceImage;
	}

	private boolean _isRunning;

	public void stop() {
		_isRunning = false;
	}

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

	@Override
	public void write(PlanarImage value) throws StreamCorruptedException {
		_successor.write(value);
	}

	@Override
	public void run() {
		_isRunning = true;
		while (_isRunning) {
			process();
		}
	}

}
