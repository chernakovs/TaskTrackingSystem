package application.view;

import application.Main;
import application.model.Project;
import application.model.ProjectDaoImpl;
import application.model.Task;
import application.model.TaskDaoImpl;
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

public class ProjectOverviewController {
	
	private Main mainApp;
	
	private ProjectDaoImpl projectDB = new ProjectDaoImpl();
	private TaskDaoImpl taskDB = new TaskDaoImpl();
	private UserDaoImpl userDB = new UserDaoImpl();
	
	@FXML
	private ObservableList<Project> projects = FXCollections.observableArrayList();
	@FXML
	private ListView<Project> projectListView = new ListView<Project>();
	@FXML
	private TableView<Task> tasksTable;
	@FXML
	private TableColumn<Task, String> taskThemeColumn;
	@FXML
	private TableColumn<Task, String> taskTypeColumn;
	@FXML
	private TableColumn<Task, String> taskPriorityColumn;
	@FXML
	private TableColumn<Task, String> taskUserColumn;

	
	public ProjectOverviewController() {
	}
	
	@FXML
	private void initialize() {
		
		projects = projectDB.findAll();
		projectListView.setItems(projects);
		
		projectListView.getSelectionModel().selectedItemProperty().addListener(
				(observable, oldValue, newValue) -> showProjectTasks(newValue));
		
	}
	
	
	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
	}
	
	public void showProjectTasks(Project project) {
		
		if (project != null) {
			
			ObservableList<Task> tasks = taskDB.findTasksByProjectId(project.getId());
			tasksTable.setItems(tasks);
			taskThemeColumn.setCellValueFactory(cellData -> cellData.getValue().getThemeProperty());
			taskTypeColumn.setCellValueFactory(cellData -> cellData.getValue().getTypeProperty());
			taskPriorityColumn.setCellValueFactory(cellData -> cellData.getValue().getPriorityProperty());
			taskUserColumn.setCellValueFactory(cellData -> new SimpleStringProperty(userDB.getUserNameById(cellData.getValue().getUserId())));
			
		}
	}
	
	@FXML
	private void handleNewProject() {
		
		Project newProject = new Project();
		boolean okClicked = mainApp.showProjectAddDialog(newProject);
		if (okClicked) {
			int newProjectId = projectDB.add(newProject);
			if (newProjectId != 0) {
				newProject.setId(newProjectId);
				projects.add(newProject);
			}
		}
	}
	
	@FXML
	private void handleDeleteProject() {
		int selectedIndex = projectListView.getSelectionModel().getSelectedIndex();
		if (selectedIndex >= 0) {
			if (projectDB.delete(projectListView.getSelectionModel().getSelectedItem())) {
				projects.remove(selectedIndex);
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
