package application.view;

import application.Main;
import application.model.ProjectDaoImpl;
import application.model.Task;
import application.model.TaskDaoImpl;
import application.model.User;
import application.model.UserDaoImpl;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;

public class UserOverviewController {
	
	private Main mainApp;
	
	private ProjectDaoImpl projectDB = new ProjectDaoImpl();
	private TaskDaoImpl taskDB = new TaskDaoImpl();
	private UserDaoImpl userDB = new UserDaoImpl();
	
	@FXML
	private ObservableList<User> users = FXCollections.observableArrayList();
	@FXML
	private ListView<User> userListView = new ListView<User>();
	@FXML
	private TableView<Task> tasksTable;
	@FXML
	private TableColumn<Task, String> taskThemeColumn;
	@FXML
	private TableColumn<Task, String> taskTypeColumn;
	@FXML
	private TableColumn<Task, String> taskPriorityColumn;
	@FXML
	private TableColumn<Task, String> taskProjectColumn;

	
	public UserOverviewController() {
	}
	
	@FXML
	private void initialize() {
		
		users = userDB.findAll();
		userListView.setItems(users);
		
		userListView.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showUserTasks(newValue));
		
	}
	
	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}
	
	public void showUserTasks(User user) {
		if (user != null) {
			ObservableList<Task> tasks = taskDB.findTasksByUserId(user.getId());
			tasksTable.setItems(tasks);
			taskThemeColumn.setCellValueFactory(cellData -> cellData.getValue().getThemeProperty());
			taskTypeColumn.setCellValueFactory(cellData -> cellData.getValue().getTypeProperty());
			taskPriorityColumn.setCellValueFactory(cellData -> cellData.getValue().getPriorityProperty());
			taskProjectColumn.setCellValueFactory(cellData -> new SimpleStringProperty(projectDB.getProjectNameById(cellData.getValue().getProjectId())));
		}
	}
	
	
	@FXML
	private void handleNewUser() {
		
		User newUser = new User();
		boolean okClicked = mainApp.showUserAddDialog(newUser);
		if (okClicked) {
			int newUserId = userDB.add(newUser);
			if (newUserId != 0) {
				newUser.setId(newUserId);
				users.add(newUser);
			}
		}
	}
	
	@FXML
	private void handleDeleteUser() {
		int selectedIndex = userListView.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			if (userDB.delete(userListView.getSelectionModel().getSelectedItem())) {
				users.remove(selectedIndex);
			}
		}
		else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Проект не выбран");
			alert.setHeaderText("Проект не выбран");
			alert.setContentText("Пожалуйста, выберите проект в списке");
			
			alert.showAndWait();
		}
	}

}
