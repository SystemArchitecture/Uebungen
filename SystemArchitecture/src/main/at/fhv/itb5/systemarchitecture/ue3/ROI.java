package main.at.fhv.itb5.systemarchitecture.ue3;

import java.awt.Rectangle;

import main.at.fhv.itb5.systemarchitecture.ue2.filter.CutOutROIFilter;
import main.at.fhv.itb5.systemarchitecture.ue3.filter.AbstractFilterBean;

public class ROI extends AbstractFilterBean{
	private static final long serialVersionUID = 2468442030629806596L;
	
	private int _x;
	private int _y;
	
	private int _width;
	private int _height;
	
	public ROI() {
		_x = 0;
		_y = 0;
		
		_width = 0;
		_height = 0;
		
		setJaiPlanarImageFilter(new CutOutROIFilter(new Rectangle(_x, _y, _width, _height), this));
	}
	
	public int getX() {
		return _x;
	}
	
	public void setX(int x) {
		_x = x;
		setJaiPlanarImageFilter(new CutOutROIFilter(new Rectangle(_x, _y, _width, _height), this));
	}
	
	public int getY() {
		return _y;
	}
	
	public void setY(int y) {
		_y = y;
		setJaiPlanarImageFilter(new CutOutROIFilter(new Rectangle(_x, _y, _width, _height), this));
	}
	
	public int getWidth() {
		return _width;
	}
	
	public void setWidth(int width) {
		_width = width;
		setJaiPlanarImageFilter(new CutOutROIFilter(new Rectangle(_x, _y, _width, _height), this));
	}
	
	public int getHeight() {
		return _height;
	}
	
	public void setHeight(int height) {
		_height = height;
		setJaiPlanarImageFilter(new CutOutROIFilter(new Rectangle(_x, _y, _width, _height), this));
	}
}
