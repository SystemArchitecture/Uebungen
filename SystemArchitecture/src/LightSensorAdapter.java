/**
 * Adapter for the LightSensor.
 */

import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.LightSensor;

public class LightSensorAdapter implements ISensor {
	private LightSensor _sensor;

	public LightSensorAdapter(DifferentialWheels differentialWheels, Sensor sensor) {
		_sensor = differentialWheels.getLightSensor(sensor.toString());
		_sensor.enable(10);
	}
	
	public double getValue(){
		return _sensor.getValue();
	}
}
