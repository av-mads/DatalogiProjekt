package Bartinator.EmployeeModule;

import Bartinator.DataAccessObjects.UserDataAccessObject;
import Bartinator.Main;
import Bartinator.Model.Employee;
import Bartinator.Utility.AlertBoxes;
import javafx.beans.InvalidationListener;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class EmployeeManagementController implements Initializable {
    public TextField usernameField;
    public PasswordField passwordField;
    public TextField nameField;
    public CheckBox adminCheckBox;

    public TableView<Employee> employeeTable;
	public TableColumn<Employee, Integer> IdCol;
	public TableColumn<Employee, String> nameCol;
    public TableColumn<Employee, String> usernameCol;
	public TableColumn<Employee, Integer> passwordCol;
	public TableColumn<Employee, Boolean> adminCol;

	UserDataAccessObject mUserDAO;
    ObservableList<Employee> data;


    @Override public void initialize(URL location, ResourceBundle resources) {

        mUserDAO = UserDataAccessObject.getInstance();
		updateUserTableView();

    }

    public void handleSaveUser(ActionEvent actionEvent) {
		if (mUserDAO.userExists(usernameField.getText())) {
			AlertBoxes.displayErrorBox("Employee Allready exists", "The user, you are trying to create, already exists in the database. " +
															  "You can choose to update the existing user instead.");
		} else {
			Employee employee = new Employee(nameField.getText(),
					usernameField.getText(),
					passwordField.getText().hashCode(),
					adminCheckBox.isSelected());
			mUserDAO.saveUser(employee);
			updateUserTableView();
		}
    }

	public void handleUpdateUser(ActionEvent actionEvent) {
		if (mUserDAO.userExists(usernameField.getText())) {
			Employee employee = mUserDAO.fetchUserFromUsername(usernameField.getText());
			if(!(passwordField.getText().equals(""))){
				employee.setPassword(passwordField.getText().hashCode());
			}
			if(!(nameField.getText().equals(""))){
				employee.setName(nameField.getText());
			}
			employee.setAdminAccess(adminCheckBox.isSelected());
			mUserDAO.updateUser(employee);
		} else {
			AlertBoxes.displayErrorBox("Employee doesn't exist", "The user, you are trying to update, doesn't exist in the database. " +
															 "You can choose to create the new user by pressing the save button.");
		}
	}

	public void handleDelete(ActionEvent actionEvent) {
		if(mUserDAO.userExists(usernameField.getText())){
			mUserDAO.deleteUser(usernameField.getText());
		} else {
			AlertBoxes.displayErrorBox("Employee doesn't exist", "The username entered doesn't match any user in the database!");
		}
	}


	public void handleExit(ActionEvent actionEvent) {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/View/adminMenuView.fxml"));
			Main.theStage.setScene(new Scene(root, 800, 480));
		} catch (IOException e) {
			System.err.println("Failed to load adminMainMenu window!");
			e.printStackTrace();
		}
	}

	private void updateUserTableView() {
		try {
			//TODO: Fetcher metode returnere null???
			data = FXCollections.observableList(mUserDAO.fetchAllUsers());
		} catch (IOException e) {
			e.printStackTrace();
			AlertBoxes.displayErrorBox("Fetching Users", e.getMessage());
		}

		IdCol.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("id"));
		nameCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("name"));
		usernameCol.setCellValueFactory(new PropertyValueFactory<Employee, String>("username"));
		passwordCol.setCellValueFactory(new PropertyValueFactory<Employee, Integer>("password"));
		//adminCol.setCellValueFactory(new PropertyValueFactory<Employee, Boolean>("adminAccess"));
		adminCol.setCellValueFactory(param -> {
			param.getValue().setAdmin(param.getValue().isAdminAccess());
			return param.getValue().getAdmin();
		});
		adminCol.setEditable(true);
		adminCol.setCellFactory(p -> {
			CheckBoxTableCell<Employee, Boolean> checkBoxTableCell = new CheckBoxTableCell<>();
			checkBoxTableCell.setEditable(true);
			return checkBoxTableCell;
		});
		System.out.println(data.toString());
		employeeTable.setItems(data);
		employeeTable.setEditable(true);
	}
}
