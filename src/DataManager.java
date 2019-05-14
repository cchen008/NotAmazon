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

            String createUserTable = "CREATE TABLE IF NOT EXISTS user("
            + "user_id VARCHAR(30) PRIMARY KEY NOT NULL,"
            + "first_name VARCHAR(30),"
            + "last_name VARCHAR(30),"
            + "address VARCHAR(30),"
            + "phone_num VARCHAR(30),"
            + "card_num VARCHAR(16),"
            + "password VARCHAR(32));";

            String createAdminTable = "CREATE TABLE IF NOT EXISTS super_user("
            + "username VARCHAR(30) PRIMARY KEY NOT NULL,"
            + "password VARCHAR(30),"
            + "name VARCHAR(30));";

            String createItemAppTable = "CREATE TABLE IF NOT EXISTS item_application("
            + "item_name VARCHAR(30) PRIMARY KEY NOT NULL,"
            + "seller VARCHAR(30),"
            + "item_type BOOLEAN NOT NULL,"
            + "price DECIMAL(10,2),"
            + "item_condition VARCHAR(30) NOT NULL,"
            + "time INT(10),"
            + "FOREIGN KEY (seller) REFERENCES user(user_id));";

            String createItemTable = "CREATE TABLE IF NOT EXISTS item("
            + "item_name VARCHAR(30) PRIMARY KEY NOT NULL,"
            + "seller_id VARCHAR(30) NOT NULL,"
            + "item_type BOOLEAN NOT NULL,"
            + "price DECIMAL(10,2),"
            + "bidding_price DECIMAL(10,2),"
            + "item_condition VARCHAR(30) NOT NULL,"
            + "time INT(10),"
            + "FOREIGN KEY (seller_id) REFERENCES user(user_id));";
            
            String createtransactionTable = "CREATE TABLE IF NOT EXISTS transactions("
            + "item_name VARCHAR(30),"
            + "seller_id VARCHAR(30),"
            + "price DECIMAL(10,2),"
            + "buyer_id VARCHAR(30),"
            + "FOREIGN KEY (buyer_id) REFERENCES user(user_id),"
            + "FOREIGN KEY (seller_id) REFERENCES item(seller_id));";

            String createFriendReqTable = "CREATE TABLE IF NOT EXISTS friend("
            + "username VARCHAR(30),"
            + "friend_request VARCHAR(30) NOT NULL,"
            + "PRIMARY KEY(username,friend_request));";

            String createReportsTable = "CREATE TABLE IF NOT EXISTS reports("
            + "reported_user VARCHAR(30) PRIMARY KEY,"
            + "reported_by VARCHAR(30),"
            + "reason VARCHAR(30),"
            + "commnt VARCHAR(128),"
            + "FOREIGN KEY (reported_user) REFERENCES user(user_id),"
            + "FOREIGN KEY (reported_by) REFERENCES user(user_id));";

            String createRatingsTable = "CREATE TABLE IF NOT EXISTS ratings("
            + "user_rated VARCHAR(30) PRIMARY KEY,"
            + "rated_by VARCHAR(30),"
            + "rating DOUBLE,"
            + "commnt VARCHAR(128),"
            + "FOREIGN KEY (user_rated) REFERENCES user(user_id),"
            + "FOREIGN KEY (rated_by) REFERENCES user(user_id));";

            String createBlackListTable = "CREATE TABLE IF NOT EXISTS black_list("
            		+ "word VARCHAR(30) PRIMARY KEY);";

            String insertAdmin = "INSERT IGNORE INTO super_user VALUES(\"admin\",\"password\", \"Super User\");";
            String insertSecondAdmin = "INSERT IGNORE INTO super_user VALUES(\"steinsgate\",\"database\", \"Dai\");";

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
            statement.executeUpdate(createtransactionTable);
            statement.executeUpdate(insertAdmin);
            statement.executeUpdate(insertSecondAdmin);
            statement.executeUpdate(createFriendReqTable);
            statement.executeUpdate(createBlackListTable);
        }

        catch(Exception e){
            e.printStackTrace();
        }
        
    }
    //setup
    public static void itemApplication(String username, String item_name, int item_type, double price, String item_condition, int time) {
    	try {
    		String insertItemApp = "INSERT INTO item_application VALUES(\""
    				+item_name+"\",\""
    				+username+"\",\""
    				+item_type+"\",\""
    				+price+"\",\""
    				+item_condition+"\",\""
    				+time+"\");";
    		statement.executeUpdate(insertItemApp);
    	}catch(Exception expt) {
    		expt.printStackTrace();
    	}
    }

    public static String [] getItemAppInfo(String item){
        String [] itemAppInfo = {"","","",""};

        try{
            String selectItemInfo = "SELECT * FROM item_application WHERE item_name=\"" +item+ "\";";

            ResultSet thisItem = statement.executeQuery(selectItemInfo);
             if(thisItem.next()){
                 itemAppInfo[0] = thisItem.getString("item_name");
                 itemAppInfo[1] = thisItem.getString("price");
                 itemAppInfo[2] = thisItem.getString("item_condition");
                 itemAppInfo[3] = thisItem.getString("seller");

                 thisItem.close();

                 return itemAppInfo;
             }
        }catch(Exception expt){
            expt.printStackTrace();
        }

        return (itemAppInfo);
    }

    //sets user's password to the default password
    public static void defaultUserPass(String username) {
    	try {
    		String defaultPassword = "UPDATE user SET password = '" + username +"' WHERE user_id = \"" +username+ "\";";
    		statement.executeUpdate(defaultPassword);
    	}catch(Exception expt){
            expt.printStackTrace();
        }
    }
    //returns all of user's information from User_Application
    public static String[] getUserApp(String username) {
    	String [] userInfo = {"","","","","",""};
    	
    	try {
    		String retrieve_userAppInfo = "SELECT user_id,first_name,last_name,address,phone_num,card_num " +
                    "FROM User_Application WHERE user_id=\"" +username+ "\";";
    		
    		ResultSet results = statement.executeQuery(retrieve_userAppInfo);
    		
    		if(results.next()){
                userInfo[0] = results.getString("user_id");
                userInfo[1] = results.getString("first_name");
                userInfo[2] = results.getString("last_name");
                userInfo[3] = results.getString("address");
                userInfo[4] = results.getString("phone_num");
                userInfo[5] = results.getString("card_num");

                results.close();

                return userInfo;
            }
    	}
    	catch(Exception expt){
            expt.printStackTrace();
        }
    	return (userInfo);
    }

    //for profile page
    public static String [] getPersonalInfo(String username){
        String [] personalInfo = {"","","","","","","",""};

        try{
            String selectUserInfo = "SELECT user_id,first_name,last_name, address, phone_num, card_num, password" +
                    " FROM user WHERE user_id=\"" +username+ "\";";

            ResultSet userInfo = statement.executeQuery(selectUserInfo);

            if(userInfo.next()){
                personalInfo[0] = userInfo.getString("user_id");
                personalInfo[1] = userInfo.getString("first_name");
                personalInfo[2] = userInfo.getString("last_name");
                personalInfo[3] = userInfo.getString("address");
                personalInfo[4] = userInfo.getString("phone_num");
                personalInfo[5] = userInfo.getString("card_num");
                personalInfo[6] = userInfo.getString("password");
                //personalInfo[7] = userInfo.getString("ratings");

                userInfo.close();

                return personalInfo;
            }

        }catch(Exception expt){
            expt.printStackTrace();
        }

        return (personalInfo);
    }

    public static String [] getReportApp(String reportedUser){
        String [] reportApp = {"","",""};

        try {
            String getReportApp = "SELECT reported_user, reported_by, reason, commnt FROM reports WHERE report_user=\"" +reportedUser+ "\";";

            ResultSet results = statement.executeQuery(getReportApp);

            if(results.next()){
                reportApp[0] = results.getString("reported_user");
                reportApp[1] = results.getString("reported_by");
                reportApp[2] = results.getString("reason");
                reportApp[3] = results.getString("commnt");

                results.close();

                return reportApp;
            }
        }
        catch(Exception expt){
            expt.printStackTrace();
        }
        return (reportApp);
    }

    public static String [] getRatingsOf(String ratedUser){
        String [] ratings = {"","",""};

        try {
            String getRatings = "SELECT rated_user, rated_by, rating, commnt FROM ratings WHERE rated_user=\"" +ratedUser+ "\";";

            ResultSet results = statement.executeQuery(getRatings);

            if(results.next()){
                ratings[0] = results.getString("rated_user");
                ratings[1] = results.getString("rated_by");
                ratings[2] = results.getString("rating");
                ratings[3] = results.getString("comment");

                results.close();

                return ratings;
            }
        }
        catch(Exception expt){
            expt.printStackTrace();
        }
        return (ratings);
    }

    public static String [] getItemInfo(String item){
        String [] itemInfo = {"","","","","",""};

        try{
            String selectItemInfo = "SELECT item_name, seller_id, price, item_type, item_condition, time FROM item " +
                    "WHERE item_name=\"" +item+ "\";";

            ResultSet thisItem = statement.executeQuery(selectItemInfo);
             if(thisItem.next()){
                 itemInfo[0] = thisItem.getString("item_name");
                 itemInfo[1] = thisItem.getString("seller_id");
                 itemInfo[2] = thisItem.getString("price");
                 itemInfo[3] = thisItem.getString("item_type");
                 itemInfo[4] = thisItem.getString("item_condition");
                 itemInfo[5] = thisItem.getString("time");

                 thisItem.close();

                 return itemInfo;
             }
        }catch(Exception expt){
            expt.printStackTrace();
        }

        return (itemInfo);
    }

    public static void addNewItem(String item) {
    	try {
    		String addItem = "INSERT INTO item SELECT * FROM item_application WHERE item_name = \"" +item+ "\";";
    		statement.executeUpdate(addItem);
    	}catch(Exception expt) {
    		expt.printStackTrace();
    	}
    }

    public static void deleteItemApp(String item) {
    	try {
    		String deleteItem = "DELETE FROM Item_Application WHERE item_name = \"" +item+ "\";";
    		statement.executeUpdate(deleteItem);
    	}catch(Exception expt) {
    		expt.printStackTrace();
    	}
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
    
    public static void addNewUser(String username) {
    	try {
    		String addUser = "INSERT INTO user (user_id, first_name, last_name, address, phone_num, card_num) " +
                    "SELECT user_id, first_name, last_name, address, phone_num, card_num \n" +
    				"FROM user_application WHERE user_id = \"" +username+ "\";";
    		statement.executeUpdate(addUser);
    	}catch(Exception expt) {
    		expt.printStackTrace();
    	}
    }
      
    public static void deleteUserApp(String username) {
    	try {
    		String deleteUser = "DELETE FROM User_Application WHERE user_id = \"" +username+ "\";";
    		statement.executeUpdate(deleteUser);
    	}catch(Exception expt) {
    		expt.printStackTrace();
    	}
    }

    public static void addReport(String reportUser, String reported_by, String reasonBox, String reasonDetails){
        try{
            String insertNewReport = "INSERT IGNORE INTO reports VALUES(\"" +reportUser+ "\",\"" +reported_by+ "\",\""
                    +reasonBox+ "\",\"" +reasonDetails+ "\");";
            statement.executeUpdate(insertNewReport);

        }catch(Exception expt){
            expt.printStackTrace();
        }
    }

    public static void addRating(String rateUser, String rated_by, double rating, String commnt){
        try{
            String insertNewRate = "INSERT IGNORE INTO ratings VALUES(\"" +rateUser+ "\",\"" +rated_by+ "\",\""
                    +rating+ "\",\"" +commnt+ "\");";
            statement.executeUpdate(insertNewRate);

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

    public static boolean isValidUser(String username, String password){
        try{
            int numberOfUsers = 0;
            String countUsers = "SELECT COUNT(1) FROM user WHERE user_id=\"" +username+ "\" AND password =\"" +password+"\";";
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

    public static void updateUserName(String username, String newFirst, String newLast){
        try{
            String updateQuery = "UPDATE User SET first_name=\"" +newFirst+ "\", last_name=\"" +newLast+ "\" WHERE user_id=\"" +username+ "\";";
            statement.executeUpdate(updateQuery);

        }catch(Exception expt){
            expt.printStackTrace();
        }
    }

    public static void updateUserAddr(String username, String newAddr){
        try{
            String updateQuery = "UPDATE User SET address=\"" +newAddr+ "\" WHERE user_id=\"" +username+ "\";";
            statement.executeUpdate(updateQuery);

        }catch(Exception expt){
            expt.printStackTrace();
        }
    }

    public static void updateUserPhoneNum(String username, String newPhoneNum){
        try{
            String updateQuery = "UPDATE User SET phone_num=\"" +newPhoneNum+ "\" WHERE user_id=\"" +username+ "\";";
            statement.executeUpdate(updateQuery);

        }catch(Exception expt){
            expt.printStackTrace();
        }
    }

    public static void updateUserCCNum(String username, String newCCNum){
        try{
            String updateQuery = "UPDATE User SET card_num=\"" +newCCNum+ "\" WHERE user_id=\"" +username+ "\";";
            statement.executeUpdate(updateQuery);

        }catch(Exception expt){
            expt.printStackTrace();
        }
    }

    public static void updateUserPass(String username, String newPass){
        try{
            String updateQuery = "UPDATE User SET password=\"" +newPass+ "\" WHERE user_id=\"" +username+ "\";";
            statement.executeUpdate(updateQuery);

        }catch(Exception expt){
            expt.printStackTrace();
        }
    }

    public static void updateItemBid(String item, double bidPrice){
        try{
            String updateQuery = "UPDATE items SET price = \"" +bidPrice+ "\" WHERE item_name =\"" +item+ "\";";
            statement.executeUpdate(updateQuery);
        }catch(Exception expt){
            expt.printStackTrace();
        }
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
    
    public static ArrayList<String> getListOfItemApp(){
        ArrayList<String> listOfItemApp = new ArrayList<>();
        try{
            String selectItem = "SELECT item_name FROM item_application;";
            ResultSet itemAppInfo = statement.executeQuery(selectItem);

            while(itemAppInfo.next()){
                listOfItemApp.add(itemAppInfo.getString("item_name"));
            }
            itemAppInfo.close();
            
        }catch(Exception expt){
            expt.printStackTrace();
        }
        return listOfItemApp;
    }

    public static ArrayList<String> getListOfFriends(String username){
    	ArrayList<String> listOfFriends = new ArrayList<>();
    	try{
            String selectfriend = "SELECT * FROM friend WHERE username= \""+username+"\";";
            ResultSet friendInfo = statement.executeQuery(selectfriend);

            while(friendInfo.next()){
                listOfFriends.add(friendInfo.getString("friend_request"));
            }
           friendInfo.close();

        }catch(Exception expt){
            expt.printStackTrace();
        }
        return listOfFriends;
    }

    public static ArrayList<String> getListOfBListWords(){
    	ArrayList<String> listOfWords = new ArrayList<>();
    	try{
            String selectWords = "SELECT * FROM black_list;";
            ResultSet wordInfo = statement.executeQuery(selectWords);

            while(wordInfo.next()){
            	listOfWords.add(wordInfo.getString("word"));
            }
            wordInfo.close();

        }catch(Exception expt){
            expt.printStackTrace();
        }
        return listOfWords;
    }

    public static ArrayList<String> getListOfItems(String username){
    	ArrayList<String> listOfItems = new ArrayList<>();
    	try{
            String selectItems = "SELECT * FROM item WHERE seller_id= \""+username+"\";";
            ResultSet itemInfos = statement.executeQuery(selectItems);

            while(itemInfos.next()){
            	listOfItems.add(itemInfos.getString("item_name"));
            }
            itemInfos.close();

        }catch(Exception expt){
            expt.printStackTrace();
        }
        return listOfItems;
    }

    public static void addNewFriend(String username, String friend) {
        try {
            String addFriend = "INSERT INTO friend (username, friend_request) VALUES(\""+username+"\", \"" +friend+"\");";
            statement.executeUpdate(addFriend);
        }catch(Exception expt){
            expt.printStackTrace();
        }
    }

    public static boolean checkValidFriend(String username, String friend) {
    	try{
            int numberOfUsername = 0;
            String countUsername = "SELECT COUNT(1) FROM friend WHERE username=\""+ username +"\" AND friend_request= \""+friend+"\";";
            ResultSet countInfo = statement.executeQuery(countUsername);

            if(countInfo.next()){
                numberOfUsername = countInfo.getInt("COUNT(1)");
            }

            return numberOfUsername == 0;

        }catch(Exception expt){
            expt.printStackTrace();
        }
    	return false;
    }

    public static void addBListWord(String word) {
    	try {
            String addFriend = "INSERT INTO black_list (word) VALUES(\""+word+"\");";
            statement.executeUpdate(addFriend);
        }catch(Exception expt){
            expt.printStackTrace();
        }
    }

    public static void deleteBListWord(String word) {
    	try {
    		String deleteWord = "DELETE FROM black_list WHERE word = \"" +word+ "\";";
    		statement.executeUpdate(deleteWord);
    	}catch(Exception expt) {
    		expt.printStackTrace();
    	}
    }

    public static boolean checkValidBListWord(String word) {
    	try{
            int numberOfWords = 0;
            String countWord = "SELECT COUNT(1) FROM black_list WHERE word=\""+ word +"\";";
            ResultSet countInfo = statement.executeQuery(countWord);

            if(countInfo.next()){
            	numberOfWords = countInfo.getInt("COUNT(1)");
            }

            return numberOfWords == 0;

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