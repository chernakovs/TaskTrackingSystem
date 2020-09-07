package application.model;

import javafx.collections.ObservableList;

public interface UserDAO {
	
	public static final String SQL_FIND_ALL = "select * from " + User.TABLE_NAME;
    public static final String SQL_INSERT = "insert into " + User.TABLE_NAME + 
    		" (" + User.NAME_COLUMN + ") values (?)";
    public static final String SQL_DELETE = "delete from " + User.TABLE_NAME + 
    		" where " + User.ID_COLUMN + " = ?"; 
    public static final String SQL_FIND_USER_NAME_BY_ID = "select name from " + 
    		User.TABLE_NAME + " where " + User.ID_COLUMN + " = ? ";
    public static final String SQL_FIND_USER_BY_NAME = "select * from " + User.TABLE_NAME +
    		" where " + User.NAME_COLUMN + " = ?";
	
	int add(User user);

    boolean delete(User user);

    ObservableList<User> findAll();

    String getUserNameById(int id);
    
    int getUserIdByName(String userName);
}
