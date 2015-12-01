package main.at.fhv.itb5.systemarchitecture.ue3.filter;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import javax.media.jai.PlanarImage;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue2.filter.imageFilter.PlanarImageFilter;

public abstract class AbstractFilterBean implements PropertyChangeListener, Serializable, Readable<PlanarImage> {
	private static final long serialVersionUID = -4540017156231057453L;

	private static String PropertyName = "Image";
	private PlanarImageFilter _planarImageFilter;
	private PlanarImage image;
	private PropertyChangeSupport _propertyChangeSupport;

	public AbstractFilterBean() {
		_propertyChangeSupport = new PropertyChangeSupport(this);
	}

	public void setJaiPlanarImageFilter(PlanarImageFilter planarImageFilter) {
		_planarImageFilter = planarImageFilter;
		setImage(_original);
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("recive callback");
		System.out.println(evt.getPropertyName());
		if (evt.getPropertyName().equals(PropertyName)) {
			setImage((PlanarImage) evt.getNewValue());
		}
	}

	protected PlanarImage _original;

	public void setImage(PlanarImage newImage) {
		System.out.println("Set Image");
		if (newImage != null) {
			System.out.println("set image");
			PlanarImage old = _original;
			_original = newImage;

			image = newImage;
			try {
				image = _planarImageFilter.read();
			} catch (StreamCorruptedException e1) {
				e1.printStackTrace();
			}

			System.out.println("Fire property Changed");
			_propertyChangeSupport.firePropertyChange(PropertyName, old, image);
		}
	}

	public PlanarImage getImage() {
		return image;
	}

	@Override
	public PlanarImage read() throws StreamCorruptedException {
		return image;
	}

	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		_propertyChangeSupport.addPropertyChangeListener(pcl);
	}

	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		_propertyChangeSupport.removePropertyChangeListener(pcl);
	}

}
