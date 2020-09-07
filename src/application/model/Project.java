package application.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Project {
	
	public static final String TABLE_NAME = "projects";
    public static final String ID_COLUMN = "id";
    public static final String NAME_COLUMN = "name";
    
    private IntegerProperty id;
    private StringProperty name;
    
    public Project() {
    	this(0, null);
    }

    public Project(int id, String name) {
    	this.id = new SimpleIntegerProperty(id);
    	this.name = new SimpleStringProperty(name);
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
    
    public String getName() {
    	return this.name.get();
    }
    
    public void setName(String name) {
    	this.name.set(name);
    }
    
    public StringProperty getNameProperty() {
    	return name;
    }
    
    @Override
    public String toString() {
    	return this.getName();
    }

}
