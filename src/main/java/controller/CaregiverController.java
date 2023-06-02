package controller;

import javafx.stage.Stage;
import model.Caregiver;

public class CaregiverController {

	private AllCaregiverController controller;
	private Stage stage;
	private Caregiver caregiver;

	/**
	 * Initializes the controller with the AllCaregiverController, the current stage, and the current caregiver.
	 *
	 * @param controller
	 * @param stage
	 * @param caregiver
	 */
	public void initializeController(AllCaregiverController controller, Stage stage, Caregiver caregiver) {
		this.stage = stage;
		this.controller = controller;
		this.caregiver = caregiver;
		showData();
	}

	/**
	 * Populates the window's FXML elements with the data from the caregiver.
	 */
	private void showData() {
		//TODO
	}

}
