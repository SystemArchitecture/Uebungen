import java.util.LinkedList;

/*
 * This class handles SubSumption Controllers. The Controllers are sorted in an ArrayList
 * with the first being the Controller with the highest priority. 
 */

public class SubSumptionControllerManager {
	private LinkedList<SubSumptionController> _controllers;
	
	public SubSumptionControllerManager() {
		_controllers = new LinkedList<>();
	}
	
	public void runController() throws NoControllerMeetsActivationContitionException {
		controllersReadInput();
		
		SubSumptionController controller = findActiveController();
		if(controller == null) {
			throw new NoControllerMeetsActivationContitionException();
		} else {
			System.out.println(controller.getClass().getName());
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
			++i;
		}
		
		return controller;
	}

	/*
	 * Adds an controller to the controll structure.
	 * First controller added has the highest priority
	 */
	public void addController(SubSumptionController subSumptionController) {
		_controllers.addLast(subSumptionController);
	}
}
