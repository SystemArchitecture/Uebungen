package main.at.fhv.itb5.systemarchitecture.ue3.filter;

import java.io.StreamCorruptedException;

import javax.media.jai.PlanarImage;

import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;

public class ReadableDummy implements Readable<PlanarImage> {

	@Override
	public PlanarImage read() throws StreamCorruptedException {
		// TODO Auto-generated method stub
		return null;
	}

}
