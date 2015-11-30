package main.at.fhv.itb5.systemarchitecture.ue3;

import java.awt.Canvas;
import java.awt.Rectangle;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import javax.media.jai.PlanarImage;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue2.filter.CutOutROIFilter;

public class ROIFilter extends Canvas implements Readable<PlanarImage>, Serializable{
	private static final long serialVersionUID = 2468442030629806596L;
	
	public int _x;
	public int _y;
	
	public int _width;
	public int _height;
	public PlanarImage _image;
	
	public CutOutROIFilter _cutOutRoiFilter;
	
	public ROIFilter() {
		_x = 0;
		_y = 0;
		
		_width = 0;
		_height = 0;
		
		_image = null;
		_cutOutRoiFilter = new CutOutROIFilter(new Rectangle(_x, _y, _width, _height), this);
	}
	
	public int getX() {
		return _x;
	}
	
	public void setX(int x) {
		_x = x;
		_cutOutRoiFilter = new CutOutROIFilter(new Rectangle(_x, _y, _width, _height), this);
	}
	
	public int getY() {
		return _y;
	}
	
	public void setY(int y) {
		_y = y;
		_cutOutRoiFilter = new CutOutROIFilter(new Rectangle(_x, _y, _width, _height), this);
	}
	
	public int getWidth() {
		return _width;
	}
	
	public void setWidth(int width) {
		_width = width;
		_cutOutRoiFilter = new CutOutROIFilter(new Rectangle(_x, _y, _width, _height), this);
	}
	
	public int getHeight() {
		return _height;
	}
	
	public void setHeight(int height) {
		_height = height;
		_cutOutRoiFilter = new CutOutROIFilter(new Rectangle(_x, _y, _width, _height), this);
	}
	
	public PlanarImage getImage() {
		try {
			return _cutOutRoiFilter.read();
		} catch (StreamCorruptedException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}
	
	public void setImage(PlanarImage image) {
		_image = image;
	}

	@Override
	public PlanarImage read() throws StreamCorruptedException {
		return _image;
	}
}
