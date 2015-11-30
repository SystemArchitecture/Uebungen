package main.at.fhv.itb5.systemarchitecture.ue3;

import main.at.fhv.itb5.systemarchitecture.ue2.filter.imageFilter.MedianFilter;

public class Median extends AbstractFilterBean {
	private static final long serialVersionUID = 1L;
	
	public int _size;

	public Median(){
		_size = 0;
		
		setJaiPlanarImageFilter(new MedianFilter(this, _size));
	}

	public int getSize() {
		return _size;
	}

	public void setSize(int size) {
		_size = size;
		setJaiPlanarImageFilter(new MedianFilter(this, _size));
	}
	
}
