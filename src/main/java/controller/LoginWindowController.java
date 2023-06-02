package controller;



import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
import javafx.scene.control.TextField;
import org.w3c.dom.Text;

public class LoginWindowController {
    @FXML
    private BorderPane mainBorderPane;
    @FXML
    private TextField logUser;
    @FXML
    private TextField logPassword;
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
        return true;
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
