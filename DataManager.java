import java.sql.*;
import java.util.*;

public class DataManager{

    private static Connection connection;
    private static Statement statement;
    
    static{
        //connects to the database when loaded
        try{
            String hostLoc = "jdbc:mysql://localhost:3306/";
            String user = "root";
            String password = "3821";

            String createDatabase = "CREATE DATABASE IF NOT EXISTS NAserver;";

            String createUserAppTable = "CREATE TABLE IF NOT EXISTS User_Application("
            + "user_id VARCHAR(30) PRIMARY KEY NOT NULL,"
            + "first_name VARCHAR(30),"
            + "last_name VARCHAR(30),"
            + "address VARCHAR(30),"
            + "phone_num VARCHAR(30),"
            + "card_num VARCHAR (30));";

            String createUserTable = "CREATE TABLE IF NOT EXISTS user("
            + "user_id VARCHAR(30) PRIMARY KEY NOT NULL,"
            + "first_name VARCHAR(30),"
            + "last_name VARCHAR(30),"
            + "address VARCHAR(30),"
            + "phone_num VARCHAR(30),"
            + "card_num VARCHAR (30));";

            String createAdminTable = "CREATE TABLE IF NOT EXISTS super_user("
            + "username VARCHAR(30) PRIMARY KEY NOT NULL,"
            + "password VARCHAR(30),"
            + "name VARCHAR(30));";

            String createItemAppTable = "CREATE TABLE IF NOT EXISTS item_application("
            + "item_name VARCHAR(30) PRIMARY KEY NOT NULL,"
            + "seller VARCHAR(30) REFERENCES User(user_id),"
            + "price DECIMAL(10,2));";

            String createItemTable = "CREATE TABLE IF NOT EXISTS item("
            + "item_name VARCHAR(30) PRIMARY KEY NOT NULL,"
            + "price DECIMAL(10,2));";

            String insertAdmin = "INSERT IGNORE INTO super_user VALUES(\"admin\",\"password\", \"Super User\");";

            connection = DriverManager.getConnection(hostLoc,user,password);
            statement = connection.createStatement();

            statement.executeUpdate(createDatabase);

            connection = DriverManager.getConnection(hostLoc+"NAServer",user,password);
            statement = connection.createStatement();

            statement.executeUpdate(createUserAppTable);
            statement.executeUpdate(createUserTable);
            statement.executeUpdate(createAdminTable);
            statement.executeUpdate(createItemAppTable);
            statement.executeUpdate(createItemTable);
            statement.executeUpdate(insertAdmin);
        }

        catch(Exception e){
            e.printStackTrace();
        }
        
    }

    //for profile page
    public static String [] getPersonalInfo(String username){
        String [] personalInfo = {"","",""};

        try{
            String selectUserInfo = "SELECT user_id,first_name,last_name FROM user WHERE user_id=\"" +username+ "\";";

            ResultSet userInfo = statement.executeQuery(selectUserInfo);

            if(userInfo.next()){
                personalInfo[0] = userInfo.getString("user_id");
                personalInfo[1] = userInfo.getString("first_name");
                personalInfo[2] = userInfo.getString("last_name");

                userInfo.close();

                return personalInfo;
            }

        }catch(Exception expt){
            expt.printStackTrace();
        }

        return (personalInfo);
    }

    //create new user
    public static void createNewUser(String username, String firstName, String lastName, String address, String phoneNum,
                                     String creditNum){
        if(username.equals(""))
            return;
        try{
            String insertNewUser = "INSERT IGNORE INTO User_Application VALUES(\"" + username + "\",\"" + firstName + "\",\""
            + lastName + "\",\"" + address + "\",\"" + phoneNum + "\",\"" + creditNum + "\");";
            statement.executeUpdate(insertNewUser);
            
        }catch(Exception expt){
            expt.printStackTrace();
        }
    }

    //checks if username exists
    public static boolean isValidAdmin(String username, String password){
        try{
            int numberOfAdmins = 0;
            String countAdminQuery = "SELECT COUNT(1) FROM super_user WHERE username=\"" +username+ "\" AND password=\"" +password+ "\";";
            ResultSet countInfo = statement.executeQuery(countAdminQuery);

            if(countInfo.next()){
                numberOfAdmins = countInfo.getInt("COUNT(1)");
            }
            countInfo.close();
            return numberOfAdmins > 0;

        }catch(Exception expt){
            expt.printStackTrace();
        }
        return false;
    }

    public static boolean isValidAdminName(String username){
        if(username.equals(""))
            return false;
        try{
            int numberOfUsernames = 0;
            String countUsernames = "SELECT COUNT(1) FROM super_user WHERE username=\"" + username + "\";";
            ResultSet countInfo = statement.executeQuery(countUsernames);

            if(countInfo.next()){
                numberOfUsernames = countInfo.getInt("COUNT(1)");
            }

            return numberOfUsernames > 0;

        }catch(Exception expt){
            expt.printStackTrace();
        }
        return false;
    }

    public static boolean isValidUsername(String username){
        if(isValidAdminName(username))
            return true;
        if(username.equals(""))
            return false;
        try{
            int numberOfUsernames = 0;
            String countUsernames = "SELECT COUNT(1) FROM user WHERE user_id=\"" + username + "\";";
            ResultSet countInfo = statement.executeQuery(countUsernames);

            if(countInfo.next()){
                numberOfUsernames = countInfo.getInt("COUNT(1)");
            }

            return numberOfUsernames > 0;

        }catch(Exception expt){
            expt.printStackTrace();
        }
        return false;
    }

    //public static boolean isValidUser(String username, String password){
    public static boolean isValidUser(String username){
        try{
            int numberOfUsers = 0;
            //String countUsers = "SELECT COUNT(1) FROM user WHERE user_id=\"" +username+ "\" AND password= \"" +password+ "\";";
            String countUsers = "SELECT COUNT(1) FROM user WHERE user_id=\"" +username+ "\";";
            ResultSet countInfo = statement.executeQuery(countUsers);

            if(countInfo.next()){
                numberOfUsers = countInfo.getInt("COUNT(1)");
            }

            return numberOfUsers > 0;

        }catch(Exception expt){
            expt.printStackTrace();
        }

        return false;
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
