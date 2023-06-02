package controller;

import datastorage.CaregiverDAO;
import datastorage.DAOFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Caregiver;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AllCaregiverController {
	@FXML
	private TableColumn<Caregiver, Integer> colID;
	@FXML
	private TableColumn<Caregiver, String> colFirstName;
	@FXML
	private TableColumn<Caregiver, String> colSurname;
	@FXML
	private TableColumn<Caregiver, String> colTelephone;
	@FXML
	private TableView<Caregiver> tableView;
	@FXML
	private TextField txfSurname;
	@FXML
	private TextField txfFirstname;
	@FXML
	private TextField txfTelephone;
	private ObservableList<Caregiver> tableviewContent =
		FXCollections.observableArrayList();

	private CaregiverDAO dao;

	private Main main;

	/**
	 * Initializes the corresponding fields.
	 * Is called as soon as the corresponding FXML file is to be displayed.
	 */
	public void initialize() {
		readAllAndShowInTableView();
		this.main = main;
		this.colID.setCellValueFactory(new PropertyValueFactory<Caregiver, Integer>("cid"));
		this.colFirstName.setCellValueFactory(new PropertyValueFactory<Caregiver, String>("firstname"));
		this.colSurname.setCellValueFactory(new PropertyValueFactory<Caregiver, String>("surname"));
		this.colTelephone.setCellValueFactory(new PropertyValueFactory<Caregiver, String>("phonenumber"));
		this.tableView.setItems(this.tableviewContent);
	}

	/**
	 * calls readAll in CaregiverDAO and shows treatments in the table
	 */
	public void readAllAndShowInTableView() {
		this.tableviewContent.clear();
		this.dao = DAOFactory.getDAOFactory().createCaregiverDAO();
		List<Caregiver> allCaregivers;
		try {
			allCaregivers = dao.readAll();
			for (Caregiver caregiver : allCaregivers) {
				this.tableviewContent.add(caregiver);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Deletes a caregiver in the database by the selectedIndex
	 */
	@FXML
	public void handleDelete() {
		int index = this.tableView.getSelectionModel().getSelectedIndex();
		Caregiver c = this.tableviewContent.remove(index);
		CaregiverDAO dao = DAOFactory.getDAOFactory().createCaregiverDAO();
		try {
			dao.deleteById(c.getCid());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Creates a new caregiver
	 */
	@FXML
	public void handleNewCaregiver() {
		try {
			Caregiver caregiver = new Caregiver(txfFirstname.getText(), txfSurname.getText(), txfTelephone.getText());
			dao.create(caregiver);
		} catch (SQLException e) {
			Alert alert = new Alert(Alert.AlertType.ERROR);
			alert.setTitle("Fehler");
			alert.setHeaderText("Datenbankfehler");
			alert.setContentText("Fehler beim Anlegen des Pflegers in der Datenbank!");
			alert.showAndWait();
		}
	}

	/**
	 *  Handles mouseclick
	 */
	@FXML
	public void handleMouseClick(){
		tableView.setOnMouseClicked(event -> {
			if (event.getClickCount() == 2 && (tableView.getSelectionModel().getSelectedItem() != null)) {
				int index = this.tableView.getSelectionModel().getSelectedIndex();
				Caregiver caregiver = this.tableviewContent.get(index);
				caregiverWindow(caregiver);
			}
		});
	}

	/**
	 * Creates a new window brought to the front GUI
	 * @param caregiver
	 */
	public void caregiverWindow(Caregiver caregiver){
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource("/CaregiverView.fxml"));
			AnchorPane pane = loader.load();
			Scene scene = new Scene(pane);
			//da die primaryStage noch im Hintergrund bleiben soll
			Stage stage = new Stage();
			CaregiverController controller = loader.getController();

			controller.initializeController(this, stage, caregiver);

			stage.setScene(scene);
			stage.setResizable(false);
			stage.showAndWait();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
