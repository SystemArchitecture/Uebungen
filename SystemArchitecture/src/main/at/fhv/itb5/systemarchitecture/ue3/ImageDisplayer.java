package main.at.fhv.itb5.systemarchitecture.ue3;

import java.awt.Canvas;
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.media.jai.PlanarImage;

public class ImageDisplayer extends Canvas implements PropertyChangeListener{
	private static final long serialVersionUID = 7355729544037986574L;
	private PlanarImage _image;

	public ImageDisplayer() {
		_image = null;
	}

	public void setImage(PlanarImage image) {
		_image = image;
		 repaint();
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
		}
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		if(evt.getPropertyName() == "Image") {
			setImage((PlanarImage)evt.getNewValue());
		}
	}
	
	
}
