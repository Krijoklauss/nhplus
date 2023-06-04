package controller;

import datastorage.CaregiverDAO;
import datastorage.DAOFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Caregiver;

import java.sql.SQLException;
import java.util.List;

public class AllCaregiverController {
	@FXML
	private TableView<Caregiver> tableView;
	@FXML
	private TableColumn<Caregiver, Integer> colID;
	@FXML
	private TableColumn<Caregiver, String> colFirstName;
	@FXML
	private TableColumn<Caregiver, String> colSurname;
	@FXML
	private TableColumn<Caregiver, String> colTelephone;
	@FXML
	private Button btnDelete;
	@FXML
	private Button btnAdd;
	@FXML
	private TextField txfSurname;
	@FXML
	private TextField txfFirstname;
	@FXML
	private TextField txfTelephone;
	private ObservableList<Caregiver> tableviewContent =
		FXCollections.observableArrayList();

	private CaregiverDAO dao;

	/**
	 * Initializes the corresponding fields.
	 * Is called as soon as the corresponding FXML file is to be displayed.
	 */
	public void initialize() {
		readAllAndShowInTableView();
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
	 * Deletes the selected caregiver in the database
	 */
	@FXML
	public void handleDeleteRow() {
		Caregiver selectedItem = this.tableView.getSelectionModel().getSelectedItem();
		try {
			dao.deleteById(selectedItem.getCid());
			this.tableView.getItems().remove(selectedItem);
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
			e.printStackTrace();
		}
		readAllAndShowInTableView();
		clearTextfields();
	}

	/**
	 * removes content from all textfields
	 */
	private void clearTextfields() {
		this.txfFirstname.clear();
		this.txfSurname.clear();
		this.txfTelephone.clear();
	}

	/**
	 * updates a caregiver by calling the update-Method in the CaregiverDAO
	 *
	 * @param t row to be updated by the user (includes the caregiver)
	 */
	private void doUpdate(TableColumn.CellEditEvent<Caregiver, String> t) {
		try {
			dao.update(t.getRowValue());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * handles new firstname value
	 *
	 * @param event event including the value that a user entered into the cell
	 */
	@FXML
	public void handleOnEditFirstname(TableColumn.CellEditEvent<Caregiver, String> event) {
		event.getRowValue().setFirstName(event.getNewValue());
		doUpdate(event);
	}

	/**
	 * handles new surname value
	 *
	 * @param event event including the value that a user entered into the cell
	 */
	@FXML
	public void handleOnEditSurname(TableColumn.CellEditEvent<Caregiver, String> event) {
		event.getRowValue().setSurname(event.getNewValue());
		doUpdate(event);
	}

	/**
	 * handles new phonenumber value
	 *
	 * @param event event including the value that a user entered into the cell
	 */
	@FXML
	public void handleOnEditTelephone(TableColumn.CellEditEvent<Caregiver, String> event) {
		event.getRowValue().setPhoneNumber(event.getNewValue());
		doUpdate(event);
	}
}
