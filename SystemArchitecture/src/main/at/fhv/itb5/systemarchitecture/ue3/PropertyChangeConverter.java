package main.at.fhv.itb5.systemarchitecture.ue3;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class PropertyChangeConverter implements PropertyChangeListener  {

	private PropertyChangeSupport _propertyChangeSupport;
	private String _propertyChangeName;
	
	public PropertyChangeConverter() {
		_propertyChangeName = "Image";
		_propertyChangeSupport = new PropertyChangeSupport(this);
	}
	
	public String getPropertyChangeName() {
		return _propertyChangeName;
	}
	
	public void setPropertyChangeName(String propertyChangeName) {
		System.out.println("Set Property Change Name " + propertyChangeName);
		_propertyChangeName = propertyChangeName;
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println("fire event " + _propertyChangeName);
		_propertyChangeSupport.firePropertyChange(_propertyChangeName, evt.getOldValue(), evt.getNewValue());
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl) {
		_propertyChangeSupport.addPropertyChangeListener(pcl);
	}

	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		_propertyChangeSupport.removePropertyChangeListener(pcl);
	}
}
