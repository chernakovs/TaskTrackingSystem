package application.view;

import application.Main;
import application.model.ProjectDaoImpl;
import application.model.Task;
import application.model.TaskDaoImpl;
import application.model.UserDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;

public class TaskOverviewController {

	private Main mainApp;
	
	private ProjectDaoImpl projectDB = new ProjectDaoImpl();
	private TaskDaoImpl taskDB = new TaskDaoImpl();
	private UserDaoImpl userDB = new UserDaoImpl();
	
	@FXML
	private ObservableList<Task> tasks = FXCollections.observableArrayList();
	@FXML
	private ListView<Task> taskListView = new ListView<Task>();
	@FXML
	private Label projectNameLabel;
	@FXML
	private Label themeLabel;
	@FXML
	private Label typeLabel;
	@FXML
	private Label priorityLabel;
	@FXML
	private Label userNameLabel;
	@FXML
	private TextArea descriptiTextArea;
	
	public TaskOverviewController() {
	}
	
	@FXML
	private void initialize() {
		
		try {
		tasks = taskDB.findAll();
		taskListView.setItems(tasks);
		
		taskListView.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showTaskDetails(newValue));
		}
		catch (NullPointerException e) {
			//e.printStackTrace();
		}
		
	}
	
	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}
	
	public void showTaskDetails(Task task) {
		
		if (task != null) {
			projectNameLabel.setText(projectDB.getProjectNameById(task.getProjectId()));
	        themeLabel.setText(task.getTheme());
	        typeLabel.setText(task.getType());
	        priorityLabel.setText(task.getPriority());
	        userNameLabel.setText(userDB.getUserNameById(task.getUserId()));
	        descriptiTextArea.setText(task.getDescription());
		}
		else {
			projectNameLabel.setText("");
			themeLabel.setText("");
			typeLabel.setText("");
			priorityLabel.setText("");
			userNameLabel.setText("");
			descriptiTextArea.setText("");
		}
	}
	
	
	@FXML
	private void handleNewTask() {
		
		Task newTask = new Task();
		boolean okClicked = mainApp.showTaskAddDialog(newTask);
		if (okClicked) {
			int newTaskId = taskDB.add(newTask);
			if (newTaskId != 0) {
				newTask.setId(newTaskId);
				tasks.add(newTask);
			}
		}
	}
	
	@FXML
	private void handleDeleteTask() {
		int selectedIndex = taskListView.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			if (taskDB.delete(taskListView.getSelectionModel().getSelectedItem())) {
				tasks.remove(selectedIndex);
			}
		}
		else {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Задача не выбрана");
			alert.setHeaderText("Задача не выбрана");
			alert.setContentText("Пожалуйста, выберите задачу в списке");
			
			alert.showAndWait();
		}
	}
	
}
