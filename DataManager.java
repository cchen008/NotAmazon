import java.sql.*;
import java.util.*;

public class DataManager{

	private static Connection connection;
    private static Statement statement;
    
    public static void main(String[] args){
        //connects to the database when loaded
        try{
            //credentials
            String hostLoc = "jdbc:mysql://localhost:3306/";
            String user = "root";
            String password = "cody1234";
            //credentials

            //database & tables
            String createDatabase = "CREATE DATABASE IF NOT EXISTS NAServer;";

            String createUserTable = "CREATE TABLE IF NOT EXISTS User("
                    + "username VARCHAR(128) PRIMARY KEY NOT NULL,"
                    + "password VARCHAR(128),"
                    + "name VARCHAR(128),"
                    + "address VARCHAR(128)"
                    + "phonenum INT,"
                    + "credcard INT);";

            String createAdminTable = "CREATE TABLE IF NOT EXISTS Admin("
                    + "username VARCHAR(128) PRIMARY KEY NOT NULL,"
                    + "password VARCHAR(128),"
                    + "name VARCHAR(128));";

            String insertAdmin = "INSERT IGNORE INTO Admin VALUES(\"admin\",\"password\", \"Super User\");";

            connection = DriverManager.getConnection(hostLoc,user,password);
            statement = connection.createStatement();

            statement.executeUpdate(createDatabase);

            connection = DriverManager.getConnection(hostLoc+"NAServer",user,password);
            statement = connection.createStatement();

            statement.executeUpdate(createUserTable);
            statement.executeUpdate(createAdminTable);
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
