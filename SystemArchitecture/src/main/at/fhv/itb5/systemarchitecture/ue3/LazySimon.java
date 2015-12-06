package main.at.fhv.itb5.systemarchitecture.ue3;

import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.awt.image.renderable.ParameterBlock;

import javax.media.jai.JAI;
import javax.media.jai.PlanarImage;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class LazySimon {
	private PlanarImage _imageOne;
	private PlanarImage _imageTwo;
	private PlanarImage _result;

	public LazySimon() {
		_imageOne = null;
		_imageTwo = null;
	}

	public LazySimon(PlanarImage imageOne, PlanarImage imageTwo) {
		_imageOne = imageOne;
		_imageTwo = imageTwo;
		overlayImage();
	}

	public PlanarImage getImageOne() {
		return _imageOne;
	}

	public void setImageOne(PlanarImage imageOne) {
		_imageOne = imageOne;
		overlayImage();
	}

	public PlanarImage getImageTwo() {
		return _imageTwo;
	}

	public void setImageTwo(PlanarImage imageTwo) {
		_imageTwo = imageTwo;
		overlayImage();
	}

	public PlanarImage getResult() {
		return _result;
	}

	private void overlayImage() {
		if (_imageOne != null && _imageTwo != null) {
			_result = JAI.create("add", _imageOne, _imageTwo);
		}
	}

	public static void main(String[] args) {
		LazySimon test = new LazySimon();

		ParameterBlock parameterBlock = new ParameterBlock();
		parameterBlock.add("C:/loetstellen.jpg");
		test.setImageOne(JAI.create("fileload", parameterBlock));

		parameterBlock = new ParameterBlock();
		parameterBlock.add("C:/loetstellen3.jpg");
		test.setImageTwo(JAI.create("fileload", parameterBlock));

		BufferedImage img = test.getResult().getAsBufferedImage();
		ImageIcon icon = new ImageIcon(img);
		JFrame frame = new JFrame();
		frame.setLayout(new FlowLayout());
		frame.setSize(400, 400);
		JLabel lbl = new JLabel();
		lbl.setIcon(icon);
		frame.add(lbl);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
