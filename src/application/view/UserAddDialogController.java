package application.view;

import application.model.User;
import application.model.UserDaoImpl;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class UserAddDialogController {
	
	private UserDaoImpl userDB = new UserDaoImpl();

	
	@FXML
	private TextField userNameField;
	
	private Stage dialogStage;
	private User user;
	private boolean okClicked = false;

	
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public boolean isOkClicked() {
		return okClicked;
	}
	
	@FXML
	private void handleOk() {
		if (isInputValid()) {
			user.setName(userNameField.getText());
			
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
		
		if (userNameField.getText() == null || userNameField.getText().length() == 0) {
			errorMessage += "Пожалуйста, введите имя пользователя.\n";
		}
		if (userDB.getUserIdByName(userNameField.getText()) != 0 ) {
			errorMessage += "Такой пользователь уже существует.\n";
		}
		

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Ошибка");
            alert.setHeaderText("Введите имя пользователя");
            alert.setContentText(errorMessage);
            
            alert.showAndWait();
            
            return false;
        }
	}
}
