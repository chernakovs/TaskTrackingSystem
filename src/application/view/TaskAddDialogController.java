package application.view;

import application.model.ProjectDaoImpl;
import application.model.Task;
import application.model.UserDaoImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class TaskAddDialogController {
	
	private ProjectDaoImpl projectDB = new ProjectDaoImpl();
	private UserDaoImpl userDB = new UserDaoImpl();
	
	@FXML
	private TextField projectNameField; 
	@FXML
	private TextField themeField;
	@FXML
	private TextField typeField;
	@FXML
	private TextField priorityField;
	@FXML
	private TextField userNameField;
	@FXML
	private TextField descriptionField;
	
	private Stage dialogStage;
	private Task task;
	private boolean okClicked = false;
	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setTask(Task task) {
		this.task = task;
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}
	
	@FXML
	private void handleOk() {
		if (isInputValid()) {
			task.setProjectId(projectDB.getProjectIdByName(projectNameField.getText()));
			task.setTheme(themeField.getText());
			task.setType(typeField.getText());
			task.setPriority(priorityField.getText());
			task.setUserId(userDB.getUserIdByName(userNameField.getText()));
			task.setDescription(descriptionField.getText());
			
			okClicked = true;
			dialogStage.close();
		}
	}
	
	@FXML
	private void handleCancel() {
		dialogStage.close();
	}
	
private boolean isInputValid() {
		
		String errorMessage = "";
		
		if (projectNameField.getText() == null || projectNameField.getText().length() == 0) {
			errorMessage += "������������ ������������ �������\n";
		}
		if (themeField.getText() == null || themeField.getText().length() == 0) {
			errorMessage += "������������ ������������ ����\n";
		}
		if (typeField.getText() == null || typeField.getText().length() == 0) {
			errorMessage += "������������ ������������ ���� ������\n";
		}
		if (priorityField.getText() == null || priorityField.getText().length() == 0) {
			errorMessage += "������������ ������������ ����������\n";
		}
		if (userNameField.getText() == null || userNameField.getText().length() == 0) {
			errorMessage += "������������ ������������ ����� �����������\n";
		}
		if (descriptionField.getText() == null || descriptionField.getText().length() == 0) {
			errorMessage += "������������ �������� ������\n";
		}
		if (projectDB.getProjectIdByName(projectNameField.getText()) == 0 ) {
			errorMessage += "����� ������ �� ����������\n";
		}
		if (userDB.getUserIdByName(userNameField.getText()) == 0 ) {
			errorMessage += "������ ������������ �� ����������\n";
		}
		

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("������");
            alert.setHeaderText("������ ����� ������");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
	}

}
