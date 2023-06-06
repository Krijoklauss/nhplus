package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;

/**
 *  The <code>Main</code> contains the basic launch functions to run the app
 *  It extends from the Application class which provides a basic launch function
 *  to run the app
 */
public class Main extends Application {

    private Stage primaryStage;

    /**
     * Overrides the start function from the Application class
     * This function is automatically run when launch is called
     * @param primaryStage
     */
    @Override
    public void start(Stage primaryStage) {
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("/nursing.png")));
        this.primaryStage = primaryStage;
        loginWindow();
    }

    /**
     *  Creates the main window of the app
     *  It is run by the overridden start function
     */

    public void loginWindow() {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/LoginWindow.fxml"));
            BorderPane pane = loader.load();
            Scene scene = new Scene(pane);

            this.primaryStage.setTitle("NHPlus - Login");
            this.primaryStage.setScene(scene);
            this.primaryStage.setResizable(false);
            this.primaryStage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Main function to launch the app via launch from the application class
     * @param args
     */
    public static void main(String[] args) {
        launch(args);
    }
}