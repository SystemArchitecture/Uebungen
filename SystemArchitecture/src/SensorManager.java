

import java.util.HashMap;

import com.cyberbotics.webots.controller.DifferentialWheels;

public class SensorManager {
	public static Sensor _types;
	private HashMap<Sensor, ISensor> _sensors;
	private DifferentialWheels _differentialWheels;
	
	public SensorManager(DifferentialWheels differentialWheels){
		_differentialWheels = differentialWheels;
		_sensors = new HashMap<>();
	}
	
	public void initialize(Sensor sensor){
		ISensor newSensor;
		if(sensor.toString().startsWith("ps")){
			newSensor = new DistanceSensorAdapter(_differentialWheels, sensor);
		} else if (sensor.toString().startsWith("ls")) {
			newSensor = new LightSensorAdapter(_differentialWheels, sensor);
		} else {
			throw new IllegalArgumentException("Unknown Sensor: " + sensor);
		}
		_sensors.put(sensor, newSensor);
	}
	
	public boolean isActive(Sensor sensor){
		return _sensors.containsKey(sensor);
	}
	
	public ISensor getSensor(Sensor sensor){
		return _sensors.get(sensor);
	}
}
