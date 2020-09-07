package application;
	
import java.io.IOException;

import application.db.SQLite;
import application.model.Project;
import application.model.Task;
import application.model.User;
import application.view.ProjectAddDialogController;
import application.view.ProjectOverviewController;
import application.view.RootLayoutController;
import application.view.TaskAddDialogController;
import application.view.TaskOverviewController;
import application.view.UserAddDialogController;
import application.view.UserOverviewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	
	private Stage primaryStage;
	private BorderPane rootLayout; 
	
	private boolean isDbFileCorrect;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle("Task tracking system");
			
			initRootLayout();
		} 
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public void checkDbFileCorrect() {
		if (SQLite.checkDbFile()) {
			isDbFileCorrect = true;
		}
		else {
			isDbFileCorrect = false;
		}
	}
	
	public void initRootLayout() {
		try {
			
			checkDbFileCorrect();
			
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
						
			Scene scene = new Scene(rootLayout);
			primaryStage.setScene(scene);
			
			RootLayoutController controller = loader.getController();
	        controller.setMainApp(this);
	        
			
			primaryStage.show();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showProjectOverview(Tab tab) {
		if (isDbFileCorrect) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("view/ProjectOverview.fxml"));
				AnchorPane projectOverview = (AnchorPane) loader.load();
							
				tab.setContent(projectOverview);
				
				ProjectOverviewController controller = loader.getController();
				controller.setMainApp(this);
				
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean showProjectAddDialog(Project project) {
		try {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/ProjectAddDialog.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();
	        
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Новый проект");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
	        
	        ProjectAddDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setProject(project);
	        
	        dialogStage.showAndWait();

	        return controller.isOkClicked();
		}
		catch (IOException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public void showUserOverview(Tab tab) {
		if (isDbFileCorrect) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("view/UserOverview.fxml"));
				AnchorPane userOverview = (AnchorPane) loader.load();
							
				tab.setContent(userOverview);
				
				UserOverviewController controller = loader.getController();
				controller.setMainApp(this);
				
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean showUserAddDialog(User user) {
		try {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/UserAddDialog.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();
	        
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Новый пользователь");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
	        
	        UserAddDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setUser(user);
	        
	        dialogStage.showAndWait();

	        return controller.isOkClicked();
		}
		catch (IOException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public void showTaskOverview(Tab tab) {
		if (isDbFileCorrect) {
			try {
				FXMLLoader loader = new FXMLLoader();
				loader.setLocation(Main.class.getResource("view/TaskOverview.fxml"));
				AnchorPane taskOverview = (AnchorPane) loader.load();
							
				tab.setContent(taskOverview);
				
				TaskOverviewController controller = loader.getController();
				controller.setMainApp(this);
				
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean showTaskAddDialog(Task task) {
		try {
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("view/TaskAddDialog.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();
	        
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Новая задача");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
	        
	        TaskAddDialogController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setTask(task);
	        
	        dialogStage.showAndWait();

	        return controller.isOkClicked();
		}
		catch (IOException e){
			e.printStackTrace();
			return false;
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
