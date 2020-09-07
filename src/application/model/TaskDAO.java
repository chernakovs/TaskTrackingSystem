package application.model;

import javafx.collections.ObservableList;

public interface TaskDAO {
	
	public static final String SQL_FIND_ALL = "select * from " + Task.TABLE_NAME;
    public static final String SQL_INSERT = "insert into " + Task.TABLE_NAME + 
    		" (" + Task.THEME_COLUMN + ", " + Task.PROJECT_ID_COLUMN +  ", " +
    		Task.TYPE_COLUMN + ", " + Task.PRIORITY_COLUMN + ", " + Task.USER_ID_COLUMN + ", " + 
    		Task.DESCRIPTION_COLUMN + ") values (?, ?, ?, ?, ?, ?)";
    public static final String SQL_DELETE = "delete from " + Task.TABLE_NAME + 
    		" where " + Task.ID_COLUMN + " = ?"; 
    public static final String SQL_FIND_TASKS_BY_USER_ID = SQL_FIND_ALL + 
    		" where " + Task.USER_ID_COLUMN + " = ? ";
    public static final String SQL_FIND_TASKS_BY_PROJECT_ID = SQL_FIND_ALL + 
    		" where " + Task.PROJECT_ID_COLUMN + " = ? ";
	
	int add(Task task);

    boolean delete(Task task);

    ObservableList<Task> findAll();
    
    ObservableList<Task> findTasksByUserId(int userId);
    
    ObservableList<Task> findTasksByProjectId(int projectId);


}
