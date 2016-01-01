import java.util.ArrayList;

/*
 * This class handles SubSumption Controllers. The Controllers are sorted in an ArrayList
 * with the first being the Controller with the highest priority. 
 */

public class SubSumptionControllerManager {
	private ArrayList<SubSumptionController> _controllers;
	
	public SubSumptionControllerManager() {
		_controllers = new ArrayList<>();
	}
	
	public void runController() throws NoControllerMeetsActivationContitionException {
		controllersReadInput();
		
		SubSumptionController controller = findActiveController();
		if(controller == null) {
			throw new NoControllerMeetsActivationContitionException();
		} else {
			controller.activate();
		}
	}
	
	private void controllersReadInput() {
		for(SubSumptionController controller : _controllers) {
			controller.readSensorInput();
		}
	}
	
	private SubSumptionController findActiveController() {
		SubSumptionController controller = null;
		
		int i = 0;
		while((controller == null) && (i < _controllers.size())) {
			if(_controllers.get(i).meetsActivationCondition()) {
				controller = _controllers.get(i);
			}
		}
		
		return controller;
	}

	public void addController(SubSumptionController subSumptionController) {
		_controllers.add(subSumptionController);
	}
}
