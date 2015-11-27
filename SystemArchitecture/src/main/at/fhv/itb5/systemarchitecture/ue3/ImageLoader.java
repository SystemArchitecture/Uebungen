package main.at.fhv.itb5.systemarchitecture.ue3;

import java.awt.Canvas;
import java.awt.image.renderable.ParameterBlock;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;

public class ImageLoader extends Canvas{
	
	private String _path;
	private PlanarImage _image;
	
	public ImageLoader() {
		_path = "";
		_image = null;
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
		_image = image;
	}
	
	public PlanarImage getImage() {
		return _image;
	}
	
}
