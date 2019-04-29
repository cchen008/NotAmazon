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
            //database & tables
            
            String createUserTable = "CREATE TABLE IF NOT EXISTS User("
            + "username VARCHAR(128) PRIMARY KEY NOT NULL,"
            + "password VARCHAR(128),"
            + "name VARCHAR(128),"
            + "address VARCHAR(128));";
            
            connection = DriverManager.getConnection(hostLoc,user,password);
            statement = connection.createStatement();
            
            statement.executeUpdate(createDatabase);
            
            connection = DriverManager.getConnection(hostLoc+"NAServer",user,password);
            statement = connection.createStatement();
            
            statement.executeUpdate(createUserTable);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
