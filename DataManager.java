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
            String password = "cody1234";

            String createDatabase = "CREATE DATABASE IF NOT EXISTS NAserver;";

            String createUserAppTable = "CREATE TABLE IF NOT EXISTS User_Application("
            + "user_id VARCHAR(30) PRIMARY KEY NOT NULL,"
            + "first_name VARCHAR(30),"
            + "last_name VARCHAR(30),"
            + "address VARCHAR(30),"
            + "phone_num VARCHAR(30),"
            + "card_num VARCHAR (16));";

            String createUserTable = "CREATE TABLE IF NOT EXISTS User("
            + "user_id VARCHAR(30) PRIMARY KEY NOT NULL,"
            + "first_name VARCHAR(30),"
            + "last_name VARCHAR(30),"
            + "address VARCHAR(30),"
            + "phone_num VARCHAR(30),"
            + "card_num VARCHAR (16));";

            String createAdminTable = "CREATE TABLE IF NOT EXISTS Super_User("
            + "username VARCHAR(30) PRIMARY KEY NOT NULL,"
            + "password VARCHAR(30),"
            + "name VARCHAR(30));";

            String createItemAppTable = "CREATE TABLE IF NOT EXISTS Item_Application("
            + "item_name VARCHAR(30) PRIMARY KEY NOT NULL,"
            + "seller VARCHAR(30) REFERENCES User(user_id),"
            + "price DECIMAL(10,2));";

            String createItemTable = "CREATE TABLE IF NOT EXISTS Item("
            + "item_name VARCHAR(30) PRIMARY KEY NOT NULL,"
            + "seller_id VARCHAR(30) NOT NULL,"
            + "item_type BOOLEAN NOT NULL,"
            + "price DECIMAL(10,2),"
            + "time INT(10),"
            + "FOREIGN KEY (seller_id) REFERENCES USER(user_id));";

            String insertAdmin = "INSERT IGNORE INTO Super_User VALUES(\"admin\",\"password\", \"Super User\");";

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
        String [] personalInfo = {"",""};

        try{
            String selectUserInfo = "SELECT name,address FROM User WHERE username=\"" +username+ "\";";

            ResultSet userInfo = statement.executeQuery(selectUserInfo);

            if(userInfo.next()){
                personalInfo[0] = userInfo.getString("name");
                personalInfo[1] = userInfo.getString("address");

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
            String countAdminQuery = "SELECT COUNT(1) FROM Super_User WHERE username=\"" +username+ "\" AND password=\"" +password+ "\";";
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
            String countUsernames = "SELECT COUNT(1) FROM User WHERE user_id=\"" + username + "\";";
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

    public static boolean isValidUser(String username, String password){
        try{
            int numberOfUsers = 0;
            String countUsers = "SELECT COUNT(1) FROM User WHERE username=\"" +username+ "\" AND password= \"" +password+ "\";";
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

    public static ArrayList<String> getListOfApp(){
        ArrayList<String> listOfApplicants = new ArrayList<>();
        try{
            String selectUsers = "SELECT user_id FROM user_application;";
            ResultSet userAppInfo = statement.executeQuery(selectUsers);

            while(userAppInfo.next()){
                listOfApplicants.add(userAppInfo.getString("user_id"));
            }
            userAppInfo.close();

        }catch(Exception expt){
            expt.printStackTrace();
        }

        return listOfApplicants;
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