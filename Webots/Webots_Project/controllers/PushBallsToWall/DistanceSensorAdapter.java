import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.DistanceSensor;

public class DistanceSensorAdapter extends NomalizedSensor {
	private DistanceSensor _distanceSensor;

	public DistanceSensorAdapter(DifferentialWheels differentialWheels, SensorTypes type) {
		super(10);
		_distanceSensor = differentialWheels.getDistanceSensor(type.toString());
		_distanceSensor.enable(10);
	}

	@Override
	protected double readValue() {
		return _distanceSensor.getValue();
	}
}