package application.view;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

import application.Main;
import application.db.DbProperties;
import application.db.SQLite;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;

public class RootLayoutController {

	private Main mainApp;

	@FXML
	private TabPane rootTabs;

	private SingleSelectionModel<Tab> selectionModel;

	public void setMainApp(Main mainApp) {
		this.mainApp = mainApp;
		handleTaskTab();
	}
	
	@FXML
	private void handleProjectTab() {
		if (mainApp != null) {
			selectionModel = rootTabs.getSelectionModel();
			mainApp.showProjectOverview(selectionModel.getSelectedItem());
		}
	}

	@FXML
	private void handleUserTab() {
		if (mainApp != null) {
			selectionModel = rootTabs.getSelectionModel();
			mainApp.showUserOverview(selectionModel.getSelectedItem());
		}
	}

	@FXML
	private void handleTaskTab() {
		if (mainApp != null) {
			selectionModel = rootTabs.getSelectionModel();
			mainApp.showTaskOverview(selectionModel.getSelectedItem());
		}
	}
	
	@FXML
    private void handleExit() {
        System.exit(0);
    }
	
	@FXML
    private void handleNew() {
		
		
		  FileChooser fileChooser = new FileChooser(); 
		  FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter( "SQLite files (*.db)", "*.db");
		  fileChooser.getExtensionFilters().add(extFilter); 
		  File file = fileChooser.showSaveDialog(mainApp.getPrimaryStage());
		  
		  if (file != null) {
			  try {
				  file.createNewFile();
				  SQLite.createNewDB(file.getPath());
				  DbProperties.setDbPath(file.getPath());
				  
				  mainApp.checkDbFileCorrect();
				  handleTaskTab();
			  }
			  catch (IOException | SQLException | ClassNotFoundException e) {
				  Alert alert = new Alert(AlertType.ERROR);
		          alert.initOwner(mainApp.getPrimaryStage());
		          alert.setTitle("Ошибка");
		          alert.setHeaderText("Не получилось создать файл данных");
		          alert.setContentText(e.toString()); 
		            
		          alert.showAndWait();
			  }
		  } 		
    }
	
	@FXML
    private void handleOpen() {
		
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "SQLite files (*.db)", "*.db");
        fileChooser.getExtensionFilters().add(extFilter);
        
        File file = fileChooser.showOpenDialog(mainApp.getPrimaryStage());
        
        if (file != null) {
			  DbProperties.setDbPath(file.getPath());
			  mainApp.checkDbFileCorrect();
			  handleTaskTab();
        }
    }
}
