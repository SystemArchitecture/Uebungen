package main.at.fhv.itb5.systemarchitecture.ue2.sink;

import javax.media.jai.PlanarImage;

import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.sink.SinkActive;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;

public class CoordinateToFileSinkPassive extends SinkActive<PlanarImage>{

	public CoordinateToFileSinkPassive(Readable<PlanarImage> predeseccor) {
		super(predeseccor);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
		
	}

}
