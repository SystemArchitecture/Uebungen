package main.at.fhv.itb5.systemarchitecture.ue3;

import java.awt.Canvas;
import java.io.Serializable;
import java.io.StreamCorruptedException;

import javax.media.jai.PlanarImage;

import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue2.filter.imageFilter.ThresholdFilter;

public class Threshold extends Canvas implements Readable<PlanarImage>, Serializable {
	private static final long serialVersionUID = 1L;
	
	private ThresholdFilter _filter;
	
	public PlanarImage _image;
	public double[] _min;
	public double[] _max;
	public double[] _cons;

	public Threshold() {
		super();
		_filter = new ThresholdFilter(this, _min, _max, _cons);
		_image = null;
	}

	@Override
	public PlanarImage read() throws StreamCorruptedException {
		return _image;
	}

	public PlanarImage getImage() {
		try {
			return _filter.read();
		} catch (StreamCorruptedException e) {
			System.err.println(e.getMessage());
			return null;
		}
	}

	public void setImage(PlanarImage image) {
		_image = image;
		_filter = new ThresholdFilter(this, _min, _max, _cons);
	}

	public double[] getMin() {
		return _min;
	}

	public void setMin(double[] min) {
		_min = min;
		_filter = new ThresholdFilter(this, _min, _max, _cons);
	}

	public double[] getMax() {
		return _max;
	}

	public void setMax(double[] max) {
		_max = max;
		_filter = new ThresholdFilter(this, _min, _max, _cons);
	}

	public double[] getCons() {
		return _cons;
	}

	public void setCons(double[] cons) {
		_cons = cons;
		_filter = new ThresholdFilter(this, _min, _max, _cons);
	}
	
}
