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
            + "user_id INT PRIMARY KEY NOT NULL,"
            + "name VARCHAR(128),"
            + "password VARCHAR(128),"
            + "phone_num INT,"
            + "card_num INT,"
            + "address VARCHAR(128));";
            
            String createSUTable = "CREATE TABLE IF NOT EXISTS SuperUser("
            + "username VARCHAR(128) PRIMARY KEY NOT NULL,"
            + "name VARCHAR(128),"
            + "password VARCHAR(128));";
            
            connection = DriverManager.getConnection(hostLoc,user,password);
            statement = connection.createStatement();
            
            statement.executeUpdate(createDatabase);
            
            connection = DriverManager.getConnection(hostLoc+"NAServer",user,password);
            statement = connection.createStatement();
            
            statement.executeUpdate(createUserTable);
            statement.executeUpdate(createSUTable);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
