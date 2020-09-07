package application.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import application.model.ProjectDaoImpl;
import application.model.TaskDaoImpl;
import application.model.UserDaoImpl;

public class SQLite {
	
	
	public static Connection getConnection() {
		
		try {
			
			Class.forName("org.sqlite.JDBC");	
			String path = DbProperties.getDbPath();
			if (path != null) { return DriverManager.getConnection(path); }
		
		} 
		catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	
	
	public static void createNewDB(String filePath) throws SQLException, ClassNotFoundException {
		
		String pathDB = "jdbc:sqlite:\\";
		
		String SQL_CREATE_PROJECT_TABLE = "CREATE TABLE \"projects\" (\r\n" + 
				"	\"id\"	INTEGER UNIQUE,\r\n" + 
				"	\"name\"	TEXT,\r\n" + 
				"	PRIMARY KEY(\"id\" AUTOINCREMENT)\r\n" + 
				")";
		String SQL_CREATE_USER_TABLE = "CREATE TABLE \"users\" (\r\n" + 
				"	\"id\"	INTEGER UNIQUE,\r\n" + 
				"	\"name\"	TEXT UNIQUE,\r\n" + 
				"	PRIMARY KEY(\"id\" AUTOINCREMENT)\r\n" + 
				")";	
		String SQL_CREATE_TASK_TABLE = "CREATE TABLE \"tasks\" (\r\n" + 
				"	\"id\"	INTEGER UNIQUE,\r\n" + 
				"	\"theme\"	TEXT,\r\n" + 
				"	\"project_id\"	INTEGER,\r\n" + 
				"	\"type\"	TEXT,\r\n" + 
				"	\"priority\"	TEXT,\r\n" + 
				"	\"user_id\"	INTEGER,\r\n" + 
				"	\"description\"	TEXT,\r\n" + 
				"	FOREIGN KEY(\"project_id\") REFERENCES \"projects\"(\"id\"),\r\n" + 
				"	PRIMARY KEY(\"id\")\r\n" + 
				")";
		
			Class.forName("org.sqlite.JDBC");
						
			Connection connection = DriverManager.getConnection(pathDB + filePath);
			PreparedStatement statement = connection.prepareStatement(SQL_CREATE_PROJECT_TABLE, Statement.RETURN_GENERATED_KEYS);
			statement.executeUpdate();
			statement = connection.prepareStatement(SQL_CREATE_USER_TABLE, Statement.RETURN_GENERATED_KEYS);
			statement.executeUpdate();
			statement = connection.prepareStatement(SQL_CREATE_TASK_TABLE, Statement.RETURN_GENERATED_KEYS);
			statement.executeUpdate();
		
	}
	
	public static boolean checkDbFile() {
		
		try {
			
			ProjectDaoImpl projectDB = new ProjectDaoImpl();
			UserDaoImpl userDB = new UserDaoImpl();
			TaskDaoImpl taskDB = new TaskDaoImpl();
			
			if ( (projectDB.findAll() != null) & (userDB.findAll() != null) & (taskDB.findAll() != null) ) {
				return true;
			}
			
			return false;
			
		} catch (NullPointerException e) {
			//e.printStackTrace();
			return false;
		}
	}
}


