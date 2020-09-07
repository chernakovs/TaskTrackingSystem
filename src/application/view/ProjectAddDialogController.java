package application.view;

import application.model.Project;
import application.model.ProjectDaoImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ProjectAddDialogController {
	
	private ProjectDaoImpl projectDB = new ProjectDaoImpl();
	
	@FXML
	private TextField projectNameField;
	
	private Stage dialogStage;
	private Project project;
	private boolean okClicked = false;

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setProject(Project project) {
		this.project = project;
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}
	
	@FXML
	private void handleOk() {
		if (isInputValid()) {
			project.setName(projectNameField.getText());
			
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
			errorMessage += "Некорректное наименование проекта\n";
		}
		if (projectDB.getProjectIdByName(projectNameField.getText()) != 0 ) {
			errorMessage += "Проект уже существует.\n";
		}
		

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Введите наименование проекта");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
	}
}
