package util;

import java.sql.Connection;
public class DBConnection {
static Connection connection;

public static Connection getConnection() 
{connection=null;
	try 
	{
	connection=PropertyUtil.getPropertyString();
     }
	catch(Exception e)
	{
		System.out.println("connection Error");
	}
	
	return connection;
}

}
