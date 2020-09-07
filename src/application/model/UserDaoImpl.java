package application.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import application.db.SQLite;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class UserDaoImpl implements UserDAO{
	
	@Override
	public int add(User user) {
		
		try (Connection connection = SQLite.getConnection(); 
			PreparedStatement statement = connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS); ) {
			
			statement.setString(1, user.getName());
			int result = statement.executeUpdate();
			if (result>0) { return statement.getGeneratedKeys().getInt(1); }
		}
		catch (SQLException | NullPointerException e) {
			//e.printStackTrace();
		}
		return 0;
	}
	
	
	@Override
	public boolean delete(User user) {
		
		try (Connection connection = SQLite.getConnection(); 
	        PreparedStatement statement = connection.prepareStatement(SQL_DELETE); ) {
			
			statement.setInt(1, user.getId());
			int result = statement.executeUpdate();
			if (result > 0) { return true; }
		}
		catch (SQLException | NullPointerException e) {
			//e.printStackTrace();
		}
		
		return false;
	}
	
	
	@Override
	public ObservableList<User> findAll() {
		
		ObservableList<User> userList = FXCollections.observableArrayList();
		
        try (Connection connection = SQLite.getConnection(); 
        	 PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL); 
        	 ResultSet rs = statement.executeQuery();) {
        	while(rs.next()) {
        		User user = new User();
        		user.setId(rs.getInt("id"));
        		user.setName(rs.getString("name"));
        		userList.add(user);
        	}
        } catch (SQLException | NullPointerException e) {
        	//e.printStackTrace();
        	return null;
        }
        return userList;
	}
	
	@Override
	public String getUserNameById(int id) {
		
		String userName = null;
		
		try (Connection connection = SQLite.getConnection(); 
	        	 PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_NAME_BY_ID);) {
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				userName = rs.getString("name");
			}
	        } 
		catch (SQLException | NullPointerException e) {
			//e.printStackTrace();
		}
		
		return userName;
		
	}
	
	@Override
	public int getUserIdByName(String userName) {
		
		int userId = 0;
		
		try (Connection connection = SQLite.getConnection(); 
				PreparedStatement statement = connection.prepareStatement(SQL_FIND_USER_BY_NAME);) {
			statement.setString(1, userName);
			ResultSet rs = statement.executeQuery();
			while(rs.next()) {
				userId = rs.getInt("id");
			}
		} 
		catch (SQLException | NullPointerException e) {
			//e.printStackTrace();
		}
		
		return userId;
		
	}

}
