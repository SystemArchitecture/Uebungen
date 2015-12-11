
public abstract class Controller {
	private ControllerType _type;
	protected MotionManager _motionManager;
	protected SensorManager _sensorManager;
	
	public Controller(ControllerType type) {
		_type = type;
	}
	
	public void control() {
		if (_type.equals(ControllerType.BANGBANG)) {
			controlBangBang();
		} else if (_type.equals(ControllerType.PROPORTIONAL)) {
			controlProportional();
		} else {
			throw new UnsupportedOperationException();
		}
	}
	
	protected abstract void controlBangBang();
	protected abstract void controlProportional();
	
	public void setMotionManager(MotionManager motionManager) {
		_motionManager = motionManager;
	}

	public void setSensorManager(SensorManager sensorManager) {
		_sensorManager = sensorManager;
	}
	
	public abstract void init();
}
