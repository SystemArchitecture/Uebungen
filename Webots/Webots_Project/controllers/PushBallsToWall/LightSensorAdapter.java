import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.LightSensor;

public class LightSensorAdapter extends NomalizedSensor {
	private LightSensor _sensor;

	public LightSensorAdapter(DifferentialWheels differentialWheels, SensorType type) {
		super(10);
		_sensor = differentialWheels.getLightSensor(type.toString());
		_sensor.enable(10);
	}

	@Override
	protected double readValue() {
		double value = _sensor.getValue();
		return value;
	}
}
