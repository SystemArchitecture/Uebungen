package main.at.fhv.itb5.systemarchitecture.ue2.sink;

import java.awt.BorderLayout;
import java.awt.Container;
import java.io.StreamCorruptedException;
import java.security.InvalidParameterException;
import javax.media.jai.PlanarImage;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import com.sun.media.jai.widget.DisplayJAI;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter.AbstractFilter;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Readable;
import main.at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.interfaces.Writeable;

public class DisplayImageSink extends AbstractFilter<PlanarImage, PlanarImage> implements Runnable{ 
	
	private JFrame jFrame;
	
	public DisplayImageSink(Writeable<PlanarImage> output) throws InvalidParameterException {
		super(output);
		init();
	}

	public DisplayImageSink(Readable<PlanarImage> input) throws InvalidParameterException {
		super(input);
		init();
	}

	private void init() {
		jFrame = new JFrame("Processed Image!");
		jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jFrame.setVisible(true);
	}

	public void toSink(PlanarImage value) {
		if(value != null) {
			Container contentPane = jFrame.getContentPane();
			contentPane.removeAll();
			contentPane.setLayout(new BorderLayout());
			contentPane.add(new JScrollPane(new DisplayJAI(value)), BorderLayout.CENTER);
			jFrame.setSize(value.getWidth(), value.getHeight());
			stop();
		}
	}
	
	@Override
	public void write(PlanarImage value) throws StreamCorruptedException {
		System.out.println("Display Image!");
		toSink(value);
	}

	@Override
	public PlanarImage read() throws StreamCorruptedException {
		return readInput();
	}

	private boolean _isRunning;
	@Override
	public void run() {
		_isRunning = true;
		while(_isRunning) {
			try {
				toSink(read());
			} catch (StreamCorruptedException e) {
				System.out.println(e.getMessage());
				stop();
			}
		}
	}
	
	public void stop() {
		_isRunning = false;
	}
}
