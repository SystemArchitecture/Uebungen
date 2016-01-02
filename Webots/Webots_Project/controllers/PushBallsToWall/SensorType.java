
public enum SensorType {
	LIGHT_SENSOR_L("ls5"),
	LIGHT_SENSOR_LM("ls6"),
	LIGHT_SENSOR_LF("ls7"),
	LIGHT_SENSOR_RF("ls0"),
	LIGHT_SENSOR_RM("ls1"),
	LIGHT_SENSOR_R("ls2"),
	DIST_SENSOR_L("ps5"),
	DIST_SENSOR_LM("ps6"),
	DIST_SENSOR_LF("ps7"),
	DIST_SENSOR_RF("ps0"),
	DIST_SENSOR_RM("ps1"),
	DIST_SENSOR_R("ps2");
	
	private final String _name;
	
	SensorType(String name){
		_name = name;
	}
	
	@Override
	public String toString(){
		return _name;
	}
}
