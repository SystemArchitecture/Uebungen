

import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.DistanceSensor;

public class DistanceSensorAdapter implements ISensor {
	private DistanceSensor _sensor;

	public DistanceSensorAdapter(DifferentialWheels differentialWheels, Sensor sensor) {
		_sensor = differentialWheels.getDistanceSensor(sensor.toString());
		_sensor.enable(10);
	}
	
	public double getValue(){
		return _sensor.getValue();
	}
}