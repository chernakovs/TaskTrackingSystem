package application.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.db.SQLite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProjectDaoImpl implements ProjectDAO {
	
	@Override
	public int add(Project project) {
		
		try (Connection connection = SQLite.getConnection(); 
			PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS); ) {
			
			statement.setString(1,project.getName());
			int result = statement.executeUpdate();
			if (result>0) { return statement.getGeneratedKeys().getInt(1); }
		}
		catch (SQLException | NullPointerException e) {
			//e.printStackTrace();
		}
		return 0;
	}
	
	
	@Override
	public boolean delete(Project project) {
		
		try (Connection connection = SQLite.getConnection(); 
	        PreparedStatement statement = connection.prepareStatement(SQL_DELETE); ) {
			
			statement.setInt(1, project.getId());
			int result = statement.executeUpdate();
			if (result > 0) { return true; }
		}
		catch (SQLException | NullPointerException e) {
			//e.printStackTrace();
		}
		
		return false;
	}
	
	
	@Override
	public ObservableList<Project> findAll() {
		
		ObservableList<Project> projectList = FXCollections.observableArrayList();
		
        try (Connection connection = SQLite.getConnection(); 
        	 PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL); 
        	 ResultSet rs = statement.executeQuery();) {
        	while(rs.next()) {
        		Project project = new Project();
        		project.setId(rs.getInt("id"));
        		project.setName(rs.getString("name"));
        		projectList.add(project);
        	}
        } 
        catch (SQLException | NullPointerException e) {
        	//e.printStackTrace();
        	return null;
        }
        return projectList;
	}
	
	@Override
	public String getProjectNameById(int id) {
		
		String projectName = null;
		
		try (Connection connection = SQLite.getConnection(); 
	        	 PreparedStatement statement = connection.prepareStatement(SQL_FIND_PROJECT_NAME_BY_ID);) {
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				projectName = rs.getString("name");
			}
		} 
		catch (SQLException | NullPointerException e) {
			//e.printStackTrace();
		}

		return projectName;
	}
	
	@Override
	public int getProjectIdByName(String projectName) {
		
		int projectId = 0;
		
		try (Connection connection = SQLite.getConnection(); 
	        	 PreparedStatement statement = connection.prepareStatement(SQL_FIND_PROJECT_BY_NAME);) {
			statement.setString(1, projectName);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				projectId = rs.getInt("id");
			}
		} 
		catch (SQLException | NullPointerException e) {
			//e.printStackTrace();
		}
		
		return projectId;
		
	}
	
}
