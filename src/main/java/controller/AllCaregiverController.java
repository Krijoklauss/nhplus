package controller;

import datastorage.CaregiverDAO;
import datastorage.DAOFactory;
import datastorage.RoleDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import model.Caregiver;
import model.Role;
import java.sql.SQLException;
import java.util.List;

/**
 * 	The <code>AllCaregiverController</code> contains the entire logic of the caregiver view.
 * 	It determines which data is displayed and how to react to events.
 */
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
	private TableColumn<Caregiver, String> colUsername;
	@FXML
	private TableColumn<Caregiver, String> colPassword;
	@FXML
	private TableColumn<Caregiver, String> colTelephone;
	@FXML
	ComboBox<String> comboBox;
	@FXML
	Button btnDelete;
	@FXML
	Button btnAdd;
	@FXML
	TextField txfSurname;
	@FXML
	TextField txfFirstname;
	@FXML
	TextField txfTelephone;
	@FXML
	TextField txfUsername;
	@FXML
	PasswordField txfPassword;
	private ObservableList<Caregiver> tableviewContent =
		FXCollections.observableArrayList();
	private ObservableList<String> myComboBoxData =
			FXCollections.observableArrayList();

	private CaregiverDAO dao;
	private RoleDAO roleDAO;

	/**
	 * Initializes the corresponding fields.
	 * Is called as soon as the corresponding FXML file is to be displayed.
	 */
	public void initialize() {
		readAllAndShowInTableView();
		this.comboBox.setItems(myComboBoxData);
		this.colID.setCellValueFactory(new PropertyValueFactory<Caregiver, Integer>("cid"));
		this.colFirstName.setCellFactory(TextFieldTableCell.forTableColumn());
		this.colFirstName.setCellValueFactory(new PropertyValueFactory<Caregiver, String>("name"));
		this.colFirstName.setCellValueFactory(new PropertyValueFactory<Caregiver, String>("firstName"));
		this.colFirstName.setCellFactory(TextFieldTableCell.forTableColumn());
		this.colSurname.setCellValueFactory(new PropertyValueFactory<Caregiver, String>("surname"));
		this.colSurname.setCellFactory(TextFieldTableCell.forTableColumn());
		this.colUsername.setCellValueFactory(new PropertyValueFactory<Caregiver, String>("username"));
		this.colUsername.setCellFactory(TextFieldTableCell.forTableColumn());
		this.colTelephone.setCellValueFactory(new PropertyValueFactory<Caregiver, String>("phoneNumber"));
		this.colTelephone.setCellFactory(TextFieldTableCell.forTableColumn());
		autoDelete();
		loadComboboxData();
		this.comboBox.getSelectionModel().select(0);

		if(!LoginController.isAdmin()) {
			disableFeatures();
		}
		this.tableView.setItems(this.tableviewContent);
	}

	/**
	 * Loads the required Combobox data
	 */
	private void loadComboboxData() {
		List<Role> roles;
		try {
			roles = this.roleDAO.readAllNotArchived();
			for(Role r : roles) {
				this.myComboBoxData.add(r.getName());
			}
		}
		catch(SQLException err) {
			err.printStackTrace();
		}
	}

	/**
	 * Disables all GUI features that are only viewable by admin users
	 */
	private void disableFeatures() {
		// Hide input fields
		btnAdd.setVisible(false);
		btnDelete.setVisible(false);
		txfFirstname.setVisible(false);
		txfSurname.setVisible(false);
		txfTelephone.setVisible(false);
		txfPassword.setVisible(false);
		txfUsername.setVisible(false);
		comboBox.setVisible(false);

		// Disable editing
		colFirstName.setEditable(false);
		colSurname.setEditable(false);
		colTelephone.setEditable(false);
		colUsername.setEditable(false);
		colID.setEditable(false);
	}

	/**
	 * Calls readAll in CaregiverDAO and shows treatments in the table
	 */
	public void readAllAndShowInTableView() {
		this.tableviewContent.clear();
		this.dao = DAOFactory.getDAOFactory().createCaregiverDAO();
		this.roleDAO = DAOFactory.getDAOFactory().createRoleDAO();
		List<Caregiver> allCaregivers;
		try {
			allCaregivers = dao.readAllNotArchived();
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
	public void handleArchiveRow() {
		Caregiver selectedItem = this.tableView.getSelectionModel().getSelectedItem();
		try {
			dao.archiveById(selectedItem.getCid());
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
			Role r = new Role(0, "User", 1);
			Caregiver caregiver = new Caregiver(txfFirstname.getText(), txfSurname.getText(), txfUsername.getText(), txfPassword.getText(), txfTelephone.getText(), r, false, null);
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
		this.txfUsername.clear();
		this.txfPassword.clear();
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

	@FXML
	public void handleOnEditUsername(TableColumn.CellEditEvent<Caregiver, String> event) {
		event.getRowValue().setUsername(event.getNewValue());
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

	/**
	 * checks if there is any caregiver that is archived for 10 years and deletes it
	 */
	private void autoDelete() {
		try {
			dao.delete();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
