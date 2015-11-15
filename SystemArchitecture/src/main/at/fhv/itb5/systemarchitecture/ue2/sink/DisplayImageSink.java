package main.at.fhv.itb5.systemarchitecture.ue2.sink;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.StreamCorruptedException;

import javax.media.jai.PlanarImage;
import javax.swing.JFrame;
import javax.swing.JScrollPane;

import com.sun.media.jai.widget.DisplayJAI;

import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.sink.SinkPassive;

public class DisplayImageSink implements SinkPassive<PlanarImage> { 
	private JFrame jFrame;
	
	public DisplayImageSink() {
		jFrame = new JFrame("Processed Image!");
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
	} 

	@Override
	public void write(PlanarImage value) throws StreamCorruptedException {
		System.out.println("Display Image!");
		if(value != null) {
			Container contentPane = jFrame.getContentPane();
			contentPane.removeAll();
			contentPane.setLayout(new BorderLayout());
			contentPane.add(new JScrollPane(new DisplayJAI(value)), BorderLayout.CENTER);
			jFrame.setSize(value.getWidth(), value.getHeight());
		}
	}
}
