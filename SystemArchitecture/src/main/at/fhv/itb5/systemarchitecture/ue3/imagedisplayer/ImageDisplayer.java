package main.at.fhv.itb5.systemarchitecture.ue3.imagedisplayer;

import java.awt.Canvas;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

import javax.media.jai.PlanarImage;

public class ImageDisplayer extends Canvas implements PropertyChangeListener{
	private static final long serialVersionUID = 7355729544037986574L;
	private PlanarImage _image;
	
	private PropertyChangeSupport _propertyChangeSupport;

	public ImageDisplayer() {
		_image = null;
		_propertyChangeSupport = new PropertyChangeSupport(this);
		setSize(100, 50);
	}

	public void setImage(PlanarImage image) {
		PlanarImage old = _image;
		_image = image;
		 repaint();
		 _propertyChangeSupport.firePropertyChange("Image", old, _image);
	}

	public PlanarImage getImage() {
		return _image;
	}

	public void update(Graphics g) {
		paint(g);
	}

	public void paint(Graphics g) {
		if(_image != null) {
			g.drawImage(_image.getAsBufferedImage(), 0, 0, null);
			setSize(_image.getWidth(), _image.getHeight());
		} else {
			g.drawString("ImageDisplayer", 0, 15);
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		System.out.println(evt.getPropertyName());
		if(evt.getPropertyName().equals("Image")) {
			System.out.println("Resived Event Image");
			setImage((PlanarImage)evt.getNewValue());
			
		}
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl)  {
		_propertyChangeSupport.addPropertyChangeListener(pcl);
	}
	
	public void removePropertyChangeListener(PropertyChangeListener pcl) {
		_propertyChangeSupport.removePropertyChangeListener(pcl);
	} 
	
	
}
