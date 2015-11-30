package main.at.fhv.itb5.systemarchitecture.ue3;

import main.at.fhv.itb5.systemarchitecture.ue2.filter.imageFilter.DilateFilter;

public class Dilate extends AbstractFilterBean {
	private static final long serialVersionUID = 1L;

	public Dilate() {
		super();
		setJaiPlanarImageFilter(new DilateFilter(this));
	}
}