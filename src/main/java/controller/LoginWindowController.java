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

public class LoginWindowController {
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private TextField logUser;
    @FXML
    private PasswordField logPassword;

    private CaregiverDAO dao;

    public void initialize() {
        this.dao = DAOFactory.getDAOFactory().createCaregiverDAO();
    }

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

    @FXML
    private void handleExit() {
        System.out.println("Stopping application!");
        System.exit(0);
    }

    private boolean inputIsEmpty() {
        if(logUser == null || logUser.getText().trim().isEmpty() || logPassword == null || logPassword.getText().trim().isEmpty()) {
            return true;
        }
        return false;
    }

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

    private void showErrorMessage(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Error");
        alert.setHeaderText("Fehler beim Login!");
        alert.setContentText(message);
        alert.showAndWait();
    }

}
