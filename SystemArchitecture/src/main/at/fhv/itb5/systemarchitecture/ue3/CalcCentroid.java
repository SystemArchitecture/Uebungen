package main.at.fhv.itb5.systemarchitecture.ue3;

import java.awt.Canvas;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.StreamCorruptedException;
import java.util.LinkedList;

import javax.media.jai.PlanarImage;

import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue2.dto.Coordinate;
import main.at.fhv.itb5.systemarchitecture.ue2.filter.CalcCentroidsFilter;

public class CalcCentroid extends Canvas implements PropertyChangeListener, Readable<PlanarImage> {

	private static final long serialVersionUID = -8389458104917228559L;
	
	private PlanarImage _image;
	private LinkedList<Coordinate> _coordinates;
	
	public CalcCentroid() {
		_coordinates = new LinkedList<>();
		_image = null;
		setSize(150, 10);
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName() == "Image") {
			_image = (PlanarImage)evt.getNewValue();
			
			CalcCentroidsFilter calcCentroidsFilter = new CalcCentroidsFilter(this);
			_coordinates = new LinkedList<>();
			_coordinates.clear();
			try {
				_coordinates.addAll(calcCentroidsFilter.read());
			} catch (StreamCorruptedException e) {
				System.out.println(e.getMessage());
			}
		}
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawString("Coordinates Count -> " + _coordinates.size(), 0, 15);
	}

	@Override
	public PlanarImage read() throws StreamCorruptedException {
		return _image;
	}
	
}
