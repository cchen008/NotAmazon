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
            String password = "3821";
            //credentials

            //database & tables
            String createDatabase = "CREATE DATABASE IF NOT EXISTS NAserver;";

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
    public static void createNewUser(String username, String password, String name, String address, String phonenum,
                                     String credcard){
        if(username.equals(""))
            return;
        try{
            String insertNewUser = "INSERT IGNORE INTO User VALUES(\"" + username + "\",\"" + password + "\",\""
                    + name + "\",\"" + address + "\",\"" + phonenum + "\",\"" + credcard + "\");";
            statement.executeUpdate(insertNewUser);

        }catch(Exception expt){
            expt.printStackTrace();
        }
    }

    //checks if username exists

    public static boolean isValidAdmin(String username, String password){
        try{
            int numberOfAdmins = 0;
            String countAdminQuery = "SELECT COUNT(1) FROM Admin WHERE username=\"" +username+ "\" AND password=\"" +password+ "\";";
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
            String countUsernames = "SELECT COUNT(1) FROM Admin WHERE username=\"" + username + "\";";
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
            String countUsernames = "SELECT COUNT(1) FROM User WHERE username=\"" + username + "\";";
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
