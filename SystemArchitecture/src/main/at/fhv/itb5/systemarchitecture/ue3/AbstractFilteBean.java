package main.at.fhv.itb5.systemarchitecture.ue3;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import java.io.StreamCorruptedException;
import javax.media.jai.PlanarImage;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue2.filter.imageFilter.PlanarImageFilter;

public abstract class AbstractFilteBean implements PropertyChangeListener, Serializable,  Readable<PlanarImage>{
	private static final long serialVersionUID = -4540017156231057453L;
	
	private static String PropertyName = "IMAGE";

	private PlanarImageFilter _planarImageFilter;
	
	private PlanarImage _image;
	
	private PropertyChangeSupport _propertyChangeSupport;
	
	public AbstractFilteBean() {
		_propertyChangeSupport = new PropertyChangeSupport(this);
	}
	
	public void setJaiPlanarImageFilter(PlanarImageFilter planarImageFilter) {
		_planarImageFilter = planarImageFilter;
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName() == PropertyName) {
			setImage((PlanarImage)evt.getNewValue());
		}
	}
	
	public void setImage(PlanarImage image) {
		PlanarImage old = _image;
		_image = image;
		try {
			_image = _planarImageFilter.read();
		} catch (StreamCorruptedException e1) {
			e1.printStackTrace();
		}
		
		_propertyChangeSupport.firePropertyChange(PropertyName, old,  _image);
	}

	public PlanarImage getImage() {
		return _image;
	}

	@Override
	public PlanarImage read() throws StreamCorruptedException {
		System.out.println("resived callback");
		return _image;
	}

	
	public void addPropertyChangeListener(PropertyChangeListener pcl)  {
		_propertyChangeSupport.addPropertyChangeListener(pcl);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		_propertyChangeSupport.removePropertyChangeListener(pcl);
	}
}
