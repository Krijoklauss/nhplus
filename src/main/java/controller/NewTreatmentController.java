package controller;

import datastorage.DAOFactory;
import datastorage.TreatmentDAO;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Patient;
import model.Treatment;
import utils.DateConverter;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * The <code>NewTreatmentController</code> contains . It determines which data is displayed and how to react to events.
 */
public class NewTreatmentController {
    @FXML
    private Label lblSurname;
    @FXML
    private Label lblFirstname;
    @FXML
    private TextField txtBegin;
    @FXML
    private TextField txtEnd;
    @FXML
    private TextField txtDescription;
    @FXML
    private TextArea taRemarks;
    @FXML
    private DatePicker datepicker;

    private AllTreatmentController controller;
    private Patient patient;
    private Stage stage;
    /**
     * Sets controller, patient and stage also use showPatientData()
     */
    public void initialize(AllTreatmentController controller, Stage stage, Patient patient) {
        this.controller= controller;
        this.patient = patient;
        this.stage = stage;
        showPatientData();
    }

    /**
     * Sets first and surname in label
     */
    private void showPatientData(){
        this.lblFirstname.setText(patient.getFirstName());
        this.lblSurname.setText(patient.getSurname());
    }

    /**
     * handles an add-click-event. Creates a treatment and calls the create method
     */
    @FXML
    public void handleAdd(){
        LocalDate date = this.datepicker.getValue();
        String s_begin = txtBegin.getText();
        LocalTime begin = DateConverter.convertStringToLocalTime(txtBegin.getText());
        LocalTime end = DateConverter.convertStringToLocalTime(txtEnd.getText());
        String description = txtDescription.getText();
        String remarks = taRemarks.getText();
        Treatment treatment = new Treatment(patient.getPid(), date,
                begin, end, description, remarks);
        createTreatment(treatment);
        stage.close();
    }

    /**
     * creates a new treatment and adds the treatment to the database table (treatment)
     * @param treatment
     */
    private void createTreatment(Treatment treatment) {
        TreatmentDAO dao = DAOFactory.getDAOFactory().createTreatmentDAO();
        try {
            dao.create(treatment);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * handles a cancel-click-event. cancels the adding process
     */
    @FXML
    public void handleCancel(){
        stage.close();
    }
}