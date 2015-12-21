import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.LightSensor;

public class LightSensorAdapter extends AbstractSensor {
	private LightSensor _sensor;

	public LightSensorAdapter(DifferentialWheels differentialWheels, SensorTypes type) {
		_sensor = differentialWheels.getLightSensor(type.toString());
		_sensor.enable(10);
	}

	@Override
	protected double readValue() {
		return _sensor.getValue();
	}
}
