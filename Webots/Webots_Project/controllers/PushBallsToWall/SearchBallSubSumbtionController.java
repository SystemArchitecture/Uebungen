
/**
 * Controller for searching the next ball.
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class SearchBallSubSumbtionController extends SubSumptionController {

	@Override
	protected Collection<SensorType> getNeededSensors() {
		Collection<SensorType> sensors = new ArrayList<>();
		sensors.add(SensorType.DIST_SENSOR_LM);
		sensors.add(SensorType.DIST_SENSOR_LF);
		sensors.add(SensorType.DIST_SENSOR_RF);
		sensors.add(SensorType.DIST_SENSOR_RM);
		return sensors;
	}

	@Override
	protected Collection<ActorTypes> getNeededActors() {
		return Arrays.asList(ActorTypes.DIFFERENTIAL_WHEELS);
	}

	@Override
	public boolean meetsActivationCondition() {
		//lowest priority controller needs always to be activated
		return true;
	}

	@Override
	public void activate() {
		double[] sensorVector = getSensorVector();
		double[][] sensorMatrix = { { 0.0, 0.0, -1.0, -1.0 }, 
									{ 1.0, 1.0, 0.0, 0.0 } };
		
		double[] resultVector = MatrixUtil.multiply(sensorMatrix, sensorVector);
		
		WheelsController wheels = (WheelsController) MotionManager.getInstance().getActor(ActorTypes.DIFFERENTIAL_WHEELS);
		
		
		System.out.println(sensorVector[0] + " " + sensorVector[1] + " " + sensorVector[2] + " " + sensorVector[3] );
		wheels.setSpeed((int) (resultVector[0] * SPEED_MULTIPLIER), (int) (resultVector[1] * SPEED_MULTIPLIER));
	}

}
