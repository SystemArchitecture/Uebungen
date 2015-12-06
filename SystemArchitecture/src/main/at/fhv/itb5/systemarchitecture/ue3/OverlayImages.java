package main.at.fhv.itb5.systemarchitecture.ue3;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;

public class OverlayImages implements PropertyChangeListener{
	private PlanarImage _base;
	private PlanarImage _overlay;
	private PlanarImage _result;
	
	private PropertyChangeSupport _propertyChangeSupport;

	public OverlayImages() {
		this(null, null);
	}

	public OverlayImages(PlanarImage base, PlanarImage overlay) {
		_base = base;
		_overlay = overlay;
		_propertyChangeSupport = new PropertyChangeSupport(this);
	}

	public PlanarImage getBase() {
		return _base;
	}

	public void setBase(PlanarImage base) {
		_base = base;
		overlayImage();
	}

	public PlanarImage getOverlay() {
		return _overlay;
	}

	public void setOverlay(PlanarImage overlay) {
		_overlay = overlay;
		overlayImage();
	}

	public PlanarImage getResult() {
		return _result;
	}

	private void overlayImage() {
		PlanarImage oldValue = _result;
		if (_base != null && _overlay != null) {
			_result = JAI.create("add", _base, _overlay);
		}
		_propertyChangeSupport.firePropertyChange("Image", oldValue, _result);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName().equals("Base")) {
			setBase((PlanarImage)evt.getNewValue());
		} else if(evt.getPropertyName().equals("Overlay")) {
			setOverlay((PlanarImage)evt.getNewValue());
		}
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		_propertyChangeSupport.addPropertyChangeListener(pcl);
	}

	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		_propertyChangeSupport.removePropertyChangeListener(pcl);
	}
}
