package main.at.fhv.itb5.systemarchitecture.ue3;

import java.awt.image.renderable.ParameterBlock;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;

public class ImageLoader implements Serializable {
	private static final long serialVersionUID = -4738419983551340755L;
	
	private String _path;
	private PlanarImage _image;
	
	private PropertyChangeSupport _propertyChangeSupport;
	
	public ImageLoader() {
		_path = "C://Users//simon_000//git//fhv//SystemArchtitekur//Uebungen//SystemArchitecture//resources//loetstellen.jpg";
		_image = null;
		_propertyChangeSupport = new PropertyChangeSupport(this);
	}
	
	public void setImagePath(String imagePath) {
		_path = imagePath;
		
		ParameterBlock parameterBlock = new ParameterBlock();
		parameterBlock.add(imagePath);	
		setImage(JAI.create("fileload", parameterBlock));
	}
	
	public String getImagePath() {
		return _path;
	}
	
	public void setImage(PlanarImage image) {
		PlanarImage old = _image;
		_image = image;
		System.out.println("Fire property Changed");
		_propertyChangeSupport.firePropertyChange("Image", old, _image);
	}
	
	public PlanarImage getImage() {
		return _image;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl)  {
		_propertyChangeSupport.addPropertyChangeListener(pcl);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		_propertyChangeSupport.removePropertyChangeListener(pcl);
	}
	
}
