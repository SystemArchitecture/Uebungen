

import com.cyberbotics.webots.controller.DifferentialWheels;
import com.cyberbotics.webots.controller.DistanceSensor;

public class DistanceSensorAdapter implements ISensor {
	private DistanceSensor _distanceSensor;
	private SensorFilterAverage _averageFilter;

	public DistanceSensorAdapter(DifferentialWheels differentialWheels, Sensor sensor) {
		_distanceSensor = differentialWheels.getDistanceSensor(sensor.toString());
		_distanceSensor.enable(10);
		_averageFilter = new SensorFilterAverage(5); // set buffer size
	}
	
	public double getValue(){
		return _averageFilter.getAverageValue(_distanceSensor.getValue());
	}
}