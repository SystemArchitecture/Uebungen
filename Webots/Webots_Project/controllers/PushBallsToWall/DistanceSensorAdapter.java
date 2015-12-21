import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.DistanceSensor;

public class DistanceSensorAdapter extends AbstractSensor {
	private DistanceSensor _distanceSensor;

	public DistanceSensorAdapter(DifferentialWheels differentialWheels, SensorTypes type) {
		_distanceSensor = differentialWheels.getDistanceSensor(type.toString());
		_distanceSensor.enable(10);
	}

	@Override
	protected double readValue() {
		return _distanceSensor.getValue();
	}
}