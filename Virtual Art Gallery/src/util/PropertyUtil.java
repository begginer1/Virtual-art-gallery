package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class PropertyUtil {
static Connection connection;

public static Connection getPropertyString() throws IOException, SQLException, ClassNotFoundException
{FileInputStream file;
	file=new FileInputStream("D:\\hexaware_aasignment\\java\\Virtual Art Gallery\\src\\util\\Database.property");
	Properties properties=new Properties();
	properties.load(file);
	String Username=properties.getProperty("jdbc.username");
	String Password=properties.getProperty("jdbc.password");
	String Driver=properties.getProperty("jdbc.driver");
	String Url=properties.getProperty("jdbc.url");
	
	//load driver
	Class.forName(Driver);
	
	//setting connection
	connection=DriverManager.getConnection(Url, Username, Password);
	return connection;
}


}
