package application.model;

import javafx.collections.ObservableList;

public interface ProjectDAO {
	
	public static final String SQL_FIND_ALL = "select * from " + Project.TABLE_NAME;
    public static final String SQL_INSERT = "insert into " + Project.TABLE_NAME + 
    		" (" + Project.NAME_COLUMN + ") values (?)";
    public static final String SQL_DELETE = "delete from " + Project.TABLE_NAME + 
    		" where " + Project.ID_COLUMN + " = ?";   
    public static final String SQL_FIND_PROJECT_NAME_BY_ID = "select name from " + 
    		Project.TABLE_NAME + " where " + Project.ID_COLUMN + " = ? ";
    public static final String SQL_FIND_PROJECT_BY_NAME = "select * from " + Project.TABLE_NAME +
    		" where " + Project.NAME_COLUMN + " = ?";

	int add(Project project);

    boolean delete(Project project);

    ObservableList<Project> findAll();
    
    String getProjectNameById(int id);
    
    int getProjectIdByName(String projectName);
	
}
