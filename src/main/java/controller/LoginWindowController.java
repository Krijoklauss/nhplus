package controller;

import datastorage.CaregiverDAO;
import datastorage.DAOFactory;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javafx.scene.control.TextField;
import model.Caregiver;

/**
 * The LoginWindowController contains the logic of the login view.
 * It determines which data is displayed and how to react to events.
 */
public class LoginWindowController {
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private TextField logUser;
    @FXML
    private PasswordField logPassword;

    private CaregiverDAO dao;

    /**
     * Initializes the corresponding fields.
     * Is called as soon as the corresponding FXML file is to be displayed.
     */
    public void initialize() {
        this.dao = DAOFactory.getDAOFactory().createCaregiverDAO();
    }

    /**
     * Button Click handler when a login is submitted
     * Checks the user input
     */
    @FXML
    private void handleLogin() {

        String username = logUser.getText();
        String password = logPassword.getText();
        if(inputIsEmpty()) {
            showErrorMessage("Bitte geben Sie Ihre Logindaten ein!");
            return;
        }

        // Check for correct login
        if(loginIsValid(username, password)) {
            passLogin();
        } else {
            showErrorMessage(
                    "Die von Ihnen eingegebenen Logindaten für den Benutzer " + username + " sind nicht korrekt!"
            );
        }
    }

    /**
     * Button Click handler when the program is exited
     */
    @FXML
    private void handleExit() {
        System.exit(0);
    }

    /**
     * Checks if the user input fields are empty
     * @return boolean
     */
    private boolean inputIsEmpty() {
        if(logUser == null || logUser.getText().trim().isEmpty() || logPassword == null || logPassword.getText().trim().isEmpty()) {
            return true;
        }
        return false;
    }

    /**
     * Checks if the entered user input is a valid login combination
     * @param username
     * @param password
     * @return boolean
     */
    private boolean loginIsValid(String username, String password) {
        List<Caregiver> allCaregiver;
        try {
            allCaregiver = this.dao.readAllNotArchived();
            for(Caregiver c : allCaregiver) {
                if(username.equals(c.getUsername()) && password.equals(c.getPassword())) {
                    // Sets the currently loggedIn user
                    LoginController.setLoggedInUser(c);
                    return true;
                }
            }
        } catch(SQLException err) {
            err.printStackTrace();
        }
        return false;
    }

    /**
     * Redirects to the MainWindowView after a successful login
     */
    private void passLogin() {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("/MainWindowView.fxml"));
        try {
            this.mainBorderPane.setCenter(loader.load());
            Stage mainStage = (Stage) mainBorderPane.getScene().getWindow();
            mainStage.setTitle("NHPlus");
        }
        catch (IOException exc) {
            exc.printStackTrace();
        }
    }

    /**
     * Shows an alert with the provided message
     * @param message
     */
    private void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("Fehler beim Login!");
        alert.setContentText(message);
        alert.showAndWait();
    }
}
