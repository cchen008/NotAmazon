import java.sql.*;
import java.util.*;

public class DataManager{

	private static Connection connection;
    private static Statement statement;
    
    static{
    	//connects to the database when loaded
    	try{
    		//credentials
    		String host = "jdbc:mysql://localhost:3306/";
    		String user = "root";
    		String password = "3821";
    		//credentials
    		
    		//database & tables
    		 String createDatabase = "CREATE DATABASE IF NOT EXISTS NAserver;";

			String createUserTable = "CREATE TABLE IF NOT EXISTS User("
					+ "username VARCHAR(128) PRIMARY KEY NOT NULL,"
					+ "password VARCHAR(128),"
					+ "name VARCHAR(128),"
					+ "address VARCHAR(128),"
					+ "phonenum INT,"
					+ "credcard INT);";

			String createAdminTable = "CREATE TABLE IF NOT EXISTS Admin("
					+ "username VARCHAR(128) PRIMARY KEY NOT NULL,"
					+ "password VARCHAR(128),"
					+ "name VARCHAR(128));";

			String insertAdmin = "INSERT IGNORE INTO Admin VALUES(\"admin\",\"password\", \"Super User\");";
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    }

	public static void shutdown(){
		try{
			if(connection!= null)
				connection.close();
			if(connection!=null)
				statement.close();

		}catch(Exception expt){
			expt.printStackTrace();
		}
	}
}
