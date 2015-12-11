

public interface IController {
	public void control(ControllerType type);

	public void setMotionManager(MotionManager motionManager);

	public void setSensorManager(SensorManager sensorManager);
	
	public void init();
}
