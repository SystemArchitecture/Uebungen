package main.at.fhv.itb5.systemarchitecture.ue3;

import main.at.fhv.itb5.systemarchitecture.ue2.filter.imageFilter.ThresholdFilter;
import main.at.fhv.itb5.systemarchitecture.ue3.filter.AbstractFilterBean;

public class Threshold extends AbstractFilterBean {
	private static final long serialVersionUID = 1L;
	
	public double _min;
	public double _max;
	public double _cons;

	public Threshold() {
		super();
		_min = 0;
		_max = 0;
		_cons = 0;
		setJaiPlanarImageFilter(new ThresholdFilter(this, _min, _max, _cons));
	}

	public double getMin() {
		return _min;
	}

	public void setMin(double min) {
		_min = min;
		setJaiPlanarImageFilter(new ThresholdFilter(this, _min, _max, _cons));
	}

	public double getMax() {
		return _max;
	}

	public void setMax(double max) {
		_max = max;
		setJaiPlanarImageFilter(new ThresholdFilter(this, _min, _max, _cons));
	}

	public double getCons() {
		return _cons;
	}

	public void setCons(double cons) {
		_cons = cons;
		setJaiPlanarImageFilter(new ThresholdFilter(this, _min, _max, _cons));
	}
	
}
