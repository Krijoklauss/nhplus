package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import java.io.IOException;

/**
 * The MainWindowController contains the logic of the main view. It determines which data is displayed and how to react to events.
 */
public class MainWindowController {

    @FXML
    private BorderPane mainBorderPane;

    /**
     * try to set AllPatientView.fxml in the center of mainBorderPane
     * else catch IOException
     * @param e
     */
    @FXML
    private void handleShowAllPatient(ActionEvent e) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AllPatientView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        AllPatientController controller = loader.getController();
    }

    /**
     * try to set AllTreatmentView.fxml in the center of mainBorderPane
     * else catch IOException
     * @param e
     */
    @FXML
    private void handleShowAllTreatments(ActionEvent e) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AllTreatmentView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        AllTreatmentController controller = loader.getController();
    }

    /**
     * try to set AllCaregiverView.fxml in the center of mainBorderPane
     * else catch IOException
     * @param e
     */
    @FXML
    private void handleShowAllCaregivers(ActionEvent e) {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/AllCaregiverView.fxml"));
        try {
            mainBorderPane.setCenter(loader.load());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        AllCaregiverController controller = loader.getController();
    }
}
