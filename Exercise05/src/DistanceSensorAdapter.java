import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.DistanceSensor;

public class DistanceSensorAdapter extends NomalizedSensor {
	private DistanceSensor _distanceSensor;

	public DistanceSensorAdapter(DifferentialWheels differentialWheels, SensorType type) {
		super(100, 0, 4000);
		_distanceSensor = differentialWheels.getDistanceSensor(type.toString());
		_distanceSensor.enable(1);
	}

	@Override
	protected double readValue() {
		return _distanceSensor.getValue();
	}
}