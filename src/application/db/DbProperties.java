package application.db;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

public class DbProperties {
		
	public static void setDbPath(String path) {
		
		try {
			
			OutputStream output = new FileOutputStream("config.properties");
			Properties properties = new Properties();
			properties.setProperty("db.path","jdbc:sqlite:\\" + path);
			properties.store(output, null);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static String getDbPath() {
		
		try (InputStream input = new FileInputStream("config.properties");) {
			
			Properties properties = new Properties();
			properties.load(input);
			return properties.getProperty("db.path");
			
						
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	

}
