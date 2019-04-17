import java.sql.*;
import java.util.*;

public class DataManager{

	private static Connection connection;
    private static Statement statement;
    
    static{
    	//connects to the database when loaded
    	try{
    		//credentials
    		String hostLoc = "jdbc:mysql://localhost:3306/";
    		String user = "root";
    		String password = "@Fcrt39jiv9";
    		//credentials
    		
    		//database & tables
    		 String createDatabase = "CREATE DATABASE IF NOT EXISTS NAServer;";
    		//database & tables
    	}
    	catch(Exception e){
    		e.printStackTrace();
    	}
    }
}
