package application.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Task {
	
	public static final String TABLE_NAME = "tasks";
    public static final String ID_COLUMN = "id";
    public static final String PROJECT_ID_COLUMN = "project_id";
    public static final String THEME_COLUMN = "theme";
    public static final String TYPE_COLUMN = "type";
    public static final String PRIORITY_COLUMN = "priority";
    public static final String USER_ID_COLUMN = "user_id";
    public static final String DESCRIPTION_COLUMN = "description";

    private IntegerProperty id;
    private StringProperty theme;
    private IntegerProperty projectId;
    private StringProperty type;
    private StringProperty priority;
    private IntegerProperty userId;
    private StringProperty description;
    
    public Task() {
    	this(0, null, 0, null, null, 0, null);
    }
    
    public Task(int id, String theme, int projectId, String type,
    		String priority, int userId, String description) {
    	this.id = new SimpleIntegerProperty(id);
    	this.theme = new SimpleStringProperty(theme);
    	this.projectId = new SimpleIntegerProperty(projectId);
    	this.type = new SimpleStringProperty(type);
    	this.priority = new SimpleStringProperty(priority);
    	this.userId = new SimpleIntegerProperty(userId);
    	this.description = new SimpleStringProperty(description);
    }
    
    public int getId() {
    	return this.id.get();
    }
    
    public void setId(int id) {
    	this.id.set(id);
    }
    
    public IntegerProperty getIdProperty() {
    	return id;
    }
    
    public String getTheme() {
    	return this.theme.get();
    }
    
    public void setTheme(String theme) {
    	this.theme.set(theme);
    }
    
    public StringProperty getThemeProperty() {
    	return theme;
    }
    
    public int getProjectId() {
    	return this.projectId.get();
    }
    
    public void setProjectId(int projectId) {
    	this.projectId.set(projectId);
    }
    
    public IntegerProperty getProjectIdProperty() {
    	return projectId;
    }
    
    public String getType() {
    	return this.type.get();
    }
    
    public void setType(String type) {
    	this.type.set(type);
    }
    
    public StringProperty getTypeProperty() {
    	return type;
    }
    
    public String getPriority() {
    	return this.priority.get();
    }
    
    public void setPriority(String priority) {
    	this.priority.set(priority);
    }
    
    public StringProperty getPriorityProperty() {
    	return priority;
    }
    
    public int getUserId() {
    	return this.userId.get();
    }
    
    public void setUserId(int userId) {
    	this.userId.set(userId);
    }
    
    public IntegerProperty getUserIdProperty() {
    	return userId;
    }
    
    public String getDescription() {
    	return this.description.get();
    }
    
    public void setDescription(String description) {
    	this.description.set(description);
    }
    
    public StringProperty getDescriptionProperty() {
    	return description;
    }
    
    @Override
    public String toString() {
    	return this.getTheme();
    }

}
