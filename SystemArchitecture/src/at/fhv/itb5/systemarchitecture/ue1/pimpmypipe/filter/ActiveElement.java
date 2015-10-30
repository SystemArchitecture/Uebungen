package at.fhv.itb5.systemarchitecture.ue1.pimpmypipe.filter;

public abstract class ActiveElement implements Runnable {

	private boolean _isRunning;
	
	@Override
	public void run() {
		_isRunning = true;
		while(_isRunning) {
			process();
		}
	}
	
	public void stop() {
		_isRunning = false;
	}
	
	public abstract void process();
}
