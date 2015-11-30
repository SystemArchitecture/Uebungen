package main.at.fhv.itb5.systemarchitecture.ue3;

import main.at.fhv.itb5.systemarchitecture.ue2.filter.imageFilter.ErodeFilter;

public class Erode extends AbstractFilterBean {
	private static final long serialVersionUID = 1L;

	public Erode() {
		super();
		setJaiPlanarImageFilter(new ErodeFilter(this));
	}
}
