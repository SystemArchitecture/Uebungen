
import java.util.HashMap;

import com.cyberbotics.webots.controller.DifferentialWheels;

public class SensorManager {
	private HashMap<SensorTypes, AbstractSensor> _sensors;
	private DifferentialWheels _differentialWheels;
	
	private static SensorManager _instance;
	public static SensorManager getInstance() {
		if(_instance == null) {
			_instance = new SensorManager();
		}
		return _instance;
	}
	
	public SensorManager() {
		_sensors = new HashMap<>();
	}
	
	private boolean isInitialized;
	
	public void init(DifferentialWheels differentialWheels) {
		if(!isInitialized) {
			_differentialWheels = differentialWheels;
			isInitialized = true;
		}
	}
	
	private static final String DISTANCE_SENSOR_PREFIX = "ps";
	private static final String LIGHT_SENSOR_PREFIX = "ls";
	
	private AbstractSensor createSensor(SensorTypes type) {
			AbstractSensor newSensor;
			if (type.toString().startsWith(DISTANCE_SENSOR_PREFIX)) {
				newSensor = new DistanceSensorAdapter(_differentialWheels, type);
			} else if (type.toString().startsWith(LIGHT_SENSOR_PREFIX)) {
				newSensor = new LightSensorAdapter(_differentialWheels, type);
			} else {
				throw new IllegalArgumentException("Unknown Sensor: " + type);
			}
			
			return newSensor;
	}

	public AbstractSensor getSensor(SensorTypes type) {
		if(!_sensors.containsKey(type)) {
			_sensors.put(type, createSensor(type));
		}
		
		return _sensors.get(type);
	}
	
	public void updateValues() {
		for(SensorTypes type : _sensors.keySet()) {
			getSensor(type).update();
		}
	}
}

