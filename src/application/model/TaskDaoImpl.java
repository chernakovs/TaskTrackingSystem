package application.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.db.SQLite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TaskDaoImpl implements TaskDAO{
	
	@Override
	public int add(Task task) {
		
		try (Connection connection = SQLite.getConnection(); 
			PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS); ) {
			
			statement.setString(1,task.getTheme());
			statement.setInt(2,task.getProjectId());
			statement.setString(3,task.getType());
			statement.setString(4,task.getPriority());
			statement.setInt(5,task.getUserId());
			statement.setString(6,task.getDescription());
			int result = statement.executeUpdate();
			if (result>0) {return statement.getGeneratedKeys().getInt(1); }
		}
		catch (SQLException | NullPointerException  e) {
			//e.printStackTrace();
		}
		return 0;
	}
	
	@Override
	public boolean delete(Task task) {
		
		try (Connection connection = SQLite.getConnection(); 
	        PreparedStatement statement = connection.prepareStatement(SQL_DELETE); ) {
			
			statement.setInt(1, task.getId());
			int result = statement.executeUpdate();
			if (result > 0) { return true; }
		}
		catch (SQLException | NullPointerException e) {
			//e.printStackTrace();
		}
		
		return false;
	}
	
	@Override
	public ObservableList<Task> findAll() {
		
		ObservableList<Task> taskList = FXCollections.observableArrayList();
		
        try (Connection connection = SQLite.getConnection(); 
        	 PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL); 
        	 ResultSet rs = statement.executeQuery();) {
        	while(rs.next()) {
        		Task task = new Task();
        		task.setId(rs.getInt("id"));
        		task.setTheme(rs.getString("theme"));
        		task.setProjectId(rs.getInt("project_id"));
        		task.setType(rs.getString("type"));
        		task.setPriority(rs.getString("priority"));
        		task.setUserId(rs.getInt("user_id"));
        		task.setDescription(rs.getString("description"));
        		taskList.add(task);
        	}
        } 
        catch (SQLException | NullPointerException e) {
        	//e.printStackTrace();
        	return null;
        }
        return taskList;
	}
	
	@Override
	public ObservableList<Task> findTasksByUserId(int userId) {
		
		ObservableList<Task> taskList = FXCollections.observableArrayList();
		
		try (Connection connection = SQLite.getConnection(); 
			PreparedStatement statement = connection.prepareStatement(SQL_FIND_TASKS_BY_USER_ID); ) {
			
			statement.setInt(1, userId);
			ResultSet rs = statement.executeQuery();
				
			while(rs.next()) {
				Task task = new Task();
				task.setId(rs.getInt("id"));
				task.setTheme(rs.getString("theme"));
				task.setProjectId(rs.getInt("project_id"));
				task.setType(rs.getString("type"));
				task.setPriority(rs.getString("priority"));
				task.setUserId(rs.getInt("user_id"));
				task.setDescription(rs.getString("description"));
				taskList.add(task);
			}
		} 
		catch (SQLException | NullPointerException e) {
			//e.printStackTrace();
		}
		return taskList;

	}
	
	
	@Override
	public ObservableList<Task> findTasksByProjectId(int projectId) {
		
		ObservableList<Task> taskList = FXCollections.observableArrayList();
		
		try (Connection connection = SQLite.getConnection(); 
			PreparedStatement statement = connection.prepareStatement(SQL_FIND_TASKS_BY_PROJECT_ID); ) {
			
			statement.setInt(1, projectId);
			ResultSet rs = statement.executeQuery();
				
			while(rs.next()) {
				Task task = new Task();
				task.setId(rs.getInt("id"));
				task.setTheme(rs.getString("theme"));
				task.setProjectId(rs.getInt("project_id"));
				task.setType(rs.getString("type"));
				task.setPriority(rs.getString("priority"));
				task.setUserId(rs.getInt("user_id"));
				task.setDescription(rs.getString("description"));
				taskList.add(task);
			}
		} catch (SQLException | NullPointerException e) {
			//e.printStackTrace();
		}
		return taskList;
		
	}

}
