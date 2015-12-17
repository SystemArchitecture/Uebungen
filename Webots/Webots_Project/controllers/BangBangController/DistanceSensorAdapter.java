

import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.DistanceSensor;

public class DistanceSensorAdapter implements ISensor {
	private DistanceSensor _sensor;
	private SensorFilter _filter;

	public DistanceSensorAdapter(DifferentialWheels differentialWheels, Sensor sensor) {
		_sensor = differentialWheels.getDistanceSensor(sensor.toString());
		_sensor.enable(10);
		_filter = new SensorFilter(5);
	}
	
	public double getValue(){
		return _filter.applyFilter(_sensor.getValue());
	}
}