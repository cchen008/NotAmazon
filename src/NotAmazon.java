import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.*;
import javafx.scene.layout.GridPane;
import javafx.scene.*;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.*;
import javafx.collections.*;
import javafx.scene.control.*;
import javafx.scene.control.Alert.*;
import javafx.scene.control.Dialog.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.*;
import javafx.scene.input.MouseEvent;


public class NotAmazon extends Application{
    
    private Stage window;
    private GUMainPage guMainScene;
    private LoginPage loginScene;
    private SignupPage signupScene;
    private OUMainPage ouMainScene;
    private TransactionPage transScene;
    private MyProfilePage myProfileScene;
    private GUSearchItemPage guSearchItemScene;
    private OUSearchItemPage ouSearchItemScene;
    private SUMainPage suMainScene;
    private PendAppPage pendAppScene;
    private PendItemPage pendItemScene;
    private ReportPage pendReportScene;
    private BlackListPage bListScene;
    private SellPage sellScene;
    
    private String thisUser;
    private String thisAdmin;
    private String currentApp;
    
    public static void main(String[]args){
        launch(args);
    }
    
    @Override
    public void start(Stage stage){
        window = stage;
        
        initialize();
        window.setScene(guMainScene);
        window.show();
    }
    
    public void initialize(){
        //sets up values for variables
        thisUser = "";
        thisAdmin = "";
        guMainScene = new GUMainPage();
        loginScene = new LoginPage();
        signupScene = new SignupPage();
        ouMainScene = new OUMainPage();
        transScene = new TransactionPage();
        myProfileScene = new MyProfilePage();
        guSearchItemScene = new GUSearchItemPage();
        ouSearchItemScene = new OUSearchItemPage();
        suMainScene = new SUMainPage();
        pendAppScene = new PendAppPage();
        pendItemScene = new PendItemPage();
        pendReportScene = new ReportPage();
        bListScene = new BlackListPage();
    }
    
    @Override
    public void stop() {
        DataManager.shutdown();
    }
    
    class GUMainPage extends Scene{
        GridPane layout;
        Text sceneTitle;
        Text recItemTitle;
        Text popItemTitle;
        TextField searchBar;
        Button searchBtn;
        Button loginBtn;
        Button signUpBtn;
        

        public GUMainPage() {
            super(new GridPane(),700,700);

            layout = (GridPane)this.getRoot();
            window.setTitle("Not Amazon");
            sceneTitle = new Text("<banner>This is the main page of Not Amazon<banner>");
            recItemTitle = new Text("Recommended");
            popItemTitle = new Text("Popular");
            searchBar = new TextField();

            recItemTitle.setFont(Font.font("Segoe UI Bold",25));
            popItemTitle.setFont(Font.font("Segoe UI Bold",25));

            searchBtn = new Button("Search");
            loginBtn = new Button("Login");
            signUpBtn = new Button("Sign Up");

            searchBtn.setOnAction(event ->{
                guSearchItemScene = new GUSearchItemPage();
                window.setScene(guSearchItemScene);
            });

            loginBtn.setOnAction(e -> {
                loginScene = new LoginPage();
                window.setScene(loginScene);
            });

            signUpBtn.setOnAction(e -> {
                signupScene = new SignupPage();
                window.setScene(signupScene);
            });

            layout.setAlignment(Pos.BASELINE_CENTER);
            layout.setHgap(10);
            layout.setVgap(10);
            layout.setPadding(new Insets(25, 25, 25, 25));
            //placing objects into scene
            layout.add(sceneTitle, 0, 0, 2, 1);
            layout.add(searchBar, 0, 1, 2, 1);
            layout.add(searchBtn, 2, 1, 2, 1);
            layout.add(loginBtn,4,1,2,1);
            layout.add(signUpBtn,6,1,2,1);
            layout.add(recItemTitle, 0, 3, 2, 1);
            layout.add(popItemTitle, 0, 26, 2, 1);
        }
    }
    
    class LoginPage extends Scene{
        GridPane layout;
        Label login;
        Label user;
        Label pass;
        TextField usr_TextField;
        PasswordField pass_TextField;
        Button loginBtn;
        Button cancelBtn;
        HBox aBtn;
        HBox cBtn;
        
        public LoginPage() {
            super(new GridPane(),320,200);
            layout = (GridPane)this.getRoot();
            
            login = new Label("Login");
            user = new Label("Username:");
            pass = new Label("Password:");
            
            usr_TextField = new TextField();
            pass_TextField = new PasswordField();
            
            loginBtn = new Button("Login");
            cancelBtn = new Button("Cancel");
            aBtn = new HBox(5);
            cBtn = new HBox(5);
            
            login.setFont(Font.font("Segoe UI Bold",25));
            
            aBtn.setAlignment(Pos.BOTTOM_RIGHT);
            aBtn.getChildren().add(loginBtn);

            loginBtn.setOnAction(event ->{
                String tempUsername = usr_TextField.getText();
                String tempPassword = pass_TextField.getText();
                if(DataManager.isValidAdmin(tempUsername,tempPassword)){
                	thisAdmin = tempUsername;
                    usr_TextField.setText("");
                    pass_TextField.setText("");
                    suMainScene = new SUMainPage();
                    window.setScene(suMainScene);
                }
                if(DataManager.isValidUser(tempUsername,tempPassword)){
                	thisUser = tempUsername;
                    usr_TextField.setText("");
                    pass_TextField.setText("");
                    suMainScene = new SUMainPage();
                    window.setScene(ouMainScene);
                }
            });

            cBtn.setAlignment(Pos.BOTTOM_LEFT);
            cBtn.getChildren().add(cancelBtn);
            cancelBtn.setOnAction(e -> window.setScene(guMainScene));
            
            
            // action event
            EventHandler<ActionEvent> pressEnter = new EventHandler<ActionEvent>(){
                public void handle(ActionEvent e)
                {
                    String tempUsername = usr_TextField.getText();
                    String tempPassword = pass_TextField.getText();
                    if(DataManager.isValidUser(tempUsername,tempPassword)){
                        thisUser = tempUsername;
                        usr_TextField.setText("");
                        pass_TextField.setText("");
                        ouMainScene = new OUMainPage();
                        window.setScene(ouMainScene);
                    }
                    if(DataManager.isValidAdmin(tempUsername,tempPassword)){
                    	thisAdmin = tempUsername;
                        usr_TextField.setText("");
                        pass_TextField.setText("");
                        suMainScene = new SUMainPage();
                        window.setScene(suMainScene);
                    }
                }
            };
            

            // when enter is pressed
            pass_TextField.setOnAction(pressEnter);
            

            cancelBtn.setOnAction(e -> window.setScene(guMainScene));

            layout.setAlignment(Pos.BASELINE_LEFT);
            layout.setHgap(10);
            layout.setVgap(10);
            layout.setPadding(new Insets(25, 25, 25, 25));
            
            //placing objects into scene
            layout.add(login, 0, 0, 2, 1);
            layout.add(user, 0, 1);
            layout.add(pass, 0, 2);
            
            layout.add(usr_TextField, 1, 1);
            layout.add(pass_TextField, 1, 2);
            layout.add(aBtn, 1, 3);
            layout.add(cBtn, 0, 3);
            
        }
    }
    
    class SignupPage extends Scene{
        GridPane layout;
        Label signup;
        Label user;
        Label first_name;
        Label last_name;
        Label address;
        Label phonenum;
        Label ccnum;
        TextField usr_TextField;
        TextField first_TextField;
        TextField last_TextField;
        TextField addr_TextField;
        TextField phone_TextField;
        TextField cc_TextField;
        Button applyBtn;
        Button cancelBtn;
        HBox aBtn;
        HBox cBtn;
        
        public SignupPage() {
            super(new GridPane(),400,400);
            layout = (GridPane)this.getRoot();
            
            signup = new Label("Sign Up");
            user = new Label("Username:");
            first_name = new Label("First Name:");
            last_name = new Label("Last Name:");
            address = new Label("Address:");
            phonenum = new Label("Phone Number:");
            ccnum = new Label("Credit Card Number:");
            
            usr_TextField = new TextField();
            first_TextField = new TextField();
            last_TextField = new TextField();
            addr_TextField = new TextField();
            phone_TextField = new TextField();
            cc_TextField = new TextField();
            
            applyBtn = new Button("Apply");
            cancelBtn = new Button("Cancel");
            aBtn = new HBox(5);
            cBtn = new HBox(5);

            applyBtn.setOnAction(e -> {
                String tempUserName = usr_TextField.getText();
                String tempFName = first_TextField.getText();
                String tempLName = last_TextField.getText();
                String tempAddress = addr_TextField.getText();
                String tempPhone = phone_TextField.getText();
                String tempCard = cc_TextField.getText();
                if(!tempUserName.equals("") && !DataManager.isValidUsername(tempUserName)){
                    DataManager.createNewUser(tempUserName, tempFName, tempLName, tempAddress,
                                              tempPhone, tempCard);
                    usr_TextField.setText("");
                    first_TextField.setText("");
                    last_TextField.setText("");
                    addr_TextField.setText("");
                    cc_TextField.setText("");
                    window.setScene(loginScene);
                }
            });

            cancelBtn.setOnAction(e -> {
                usr_TextField.setText("");
                first_TextField.setText("");
                last_TextField.setText("");
                addr_TextField.setText("");
                phone_TextField.setText("");
                cc_TextField.setText("");
                window.setScene(guMainScene);
            });

            signup.setFont(Font.font("Segoe UI Bold",25));
            
            aBtn.setAlignment(Pos.BOTTOM_RIGHT);
            aBtn.getChildren().add(applyBtn);
            
            cBtn.setAlignment(Pos.BOTTOM_LEFT);
            cBtn.getChildren().add(cancelBtn);
            cancelBtn.setOnAction(e -> window.setScene(guMainScene));
            
            layout.setAlignment(Pos.BASELINE_LEFT);
            layout.setHgap(10);
            layout.setVgap(10);
            layout.setPadding(new Insets(25, 25, 25, 25));
            
            //placing objects into scene
            layout.add(signup, 0, 0, 2, 1);
            layout.add(user, 0, 1);
            layout.add(first_name, 0, 2);
            layout.add(last_name, 0, 3);
            layout.add(address, 0, 4);
            layout.add(phonenum, 0, 5);
            layout.add(ccnum, 0, 6);
            
            layout.add(usr_TextField, 1, 1);
            layout.add(first_TextField, 1, 2);
            layout.add(last_TextField, 1, 3);
            layout.add(addr_TextField, 1, 4);
            layout.add(phone_TextField, 1, 5);
            layout.add(cc_TextField, 1, 6);
            layout.add(aBtn, 1, 7);
            layout.add(cBtn, 0, 7);
            
        }
    }

    class OUMainPage extends Scene{
        GridPane layout;
        Text sceneTitle;
        Text recItemTitle;
        Text popItemTitle;
        Text sellTitle;
        Text bidTitle;
        Text friendTitle;
        Text addFriendTitle;
        Text friendName;
        MenuButton menu;
        MenuItem profile;
        MenuItem myTranHist;
        MenuItem signOut;
        TextField searchBar;
        TextField friendTextField;
        Button searchBtn;
        Button sellBtn;
        Button friendBtn;
        ObservableList<String> sellingList;
        ListView<String> sellListView;
        ObservableList<String> biddingList;
        ListView<String> bidListView;
        ObservableList<String> friendList;
        ListView<String> friendListView;


        public OUMainPage() {
            super(new GridPane(),900,800);
            layout = (GridPane)this.getRoot();
            window.setTitle("Not Amazon");
            
            sellingList = FXCollections.observableArrayList();
            sellListView = new ListView<>(sellingList);
            biddingList = FXCollections.observableArrayList();
            bidListView = new ListView<>(biddingList);
            friendList = FXCollections.observableArrayList();
            friendListView = new ListView<>(friendList);
            
            sceneTitle = new Text("<banner>This is the main page of Not Amazon<banner>");
            recItemTitle = new Text("Recommended");
            popItemTitle = new Text("Popular");
            sellTitle = new Text("Sell");
            bidTitle = new Text("Bid");
            friendTitle = new Text("Friend");
            addFriendTitle = new Text("Add Friend");
            friendName = new Text("Username:");

            searchBar = new TextField();
            friendTextField = new TextField();

            recItemTitle.setFont(Font.font("Segoe UI Bold",25));
            popItemTitle.setFont(Font.font("Segoe UI Bold",25));
            sellTitle.setFont(Font.font("Segoe UI Bold",25));
            bidTitle.setFont(Font.font("Segoe UI Bold",25));
            friendTitle.setFont(Font.font("Segoe UI Bold",25));
            addFriendTitle.setFont(Font.font("Segoe UI Bold",25));

            searchBtn = new Button("Search");
            sellBtn = new Button("+");
            friendBtn = new Button("+");

            searchBtn.setOnAction(event -> {
                ouSearchItemScene = new OUSearchItemPage();
                window.setScene(ouSearchItemScene);
            });
            
            sellBtn.setOnAction(event -> {
            	sellScene = new SellPage();
            	window.setScene(sellScene);
            });
            
            friendBtn.setOnAction(event -> {
                GridPane friendLayout = new GridPane();
                Scene friendScene = new Scene(friendLayout, 400, 300);
 
                // New window (Stage)
                Stage friendWindow = new Stage();
                friendWindow.setTitle("Add Friend");
                friendWindow.setScene(friendScene);
                
                friendLayout.setAlignment(Pos.BASELINE_CENTER);
                friendLayout.setHgap(10);
                friendLayout.setVgap(10);
                friendLayout.setPadding(new Insets(25, 25, 25, 25));
                friendLayout.add(addFriendTitle, 2, 0, 2, 1);
                friendLayout.add(friendName, 0, 1, 2, 1);
                friendLayout.add(friendTextField, 2, 1, 2, 1);
                
                friendWindow.show();
            });

            //dropdown menu
            menu = new MenuButton("Menu");
            profile = new MenuItem("Profile");
            myTranHist = new MenuItem("My Transaction History");
            signOut = new MenuItem("Sign Out");
            menu.getItems().addAll(profile, myTranHist, signOut);


            profile.setOnAction(event -> {
                myProfileScene = new MyProfilePage();
                window.setScene(myProfileScene);
            });

            myTranHist.setOnAction(event -> {
                transScene = new TransactionPage();
                window.setScene(transScene);
            });

            signOut.setOnAction(event -> {
                guMainScene = new GUMainPage();
                window.setScene(guMainScene);
            });
            //dropdown menu
            
            sellListView.setPrefWidth(300);
            sellListView.setPrefHeight(400);
            sellListView.setOrientation(Orientation.VERTICAL);
            bidListView.setPrefWidth(300);
            bidListView.setPrefHeight(400);
            bidListView.setOrientation(Orientation.VERTICAL);
            friendListView.setPrefWidth(300);
            friendListView.setPrefHeight(400);
            friendListView.setOrientation(Orientation.VERTICAL);


            layout.setAlignment(Pos.BASELINE_CENTER);
            layout.setHgap(10);
            layout.setVgap(10);
            layout.setPadding(new Insets(25, 25, 25, 25));
            //placing objects into scene
            layout.add(sceneTitle, 0, 0, 2, 1);
            layout.add(searchBar, 0, 1, 2, 1);
            layout.add(recItemTitle, 0, 3, 2, 1);
            layout.add(popItemTitle, 0, 6, 2, 1);
            layout.add(searchBtn, 2, 1, 2, 1);
            layout.add(sellTitle, 2, 3, 2, 1);
            layout.add(sellListView, 2, 4, 2, 1);
            layout.add(bidTitle, 2, 5, 2, 1);
            layout.add(bidListView, 2, 6, 2, 1);
            layout.add(friendTitle, 2, 7, 2, 1);
            layout.add(friendListView, 2, 8, 2, 1);
            layout.add(menu, 4, 1, 2, 1);
            layout.add(sellBtn, 4, 3, 2, 1);
            layout.add(friendBtn, 4, 7, 2, 1);
        }
    }

    class TransactionPage extends Scene{
        GridPane layout;
        Text transTitle;
        Button backBtn;

        public TransactionPage() {
            super(new GridPane(),700,700);
            layout = (GridPane)this.getRoot();
            transTitle = new Text("Transaction History");

            backBtn = new Button("Back");

            transTitle.setFont(Font.font("Segoe UI Bold",25));

            backBtn.setOnAction(event -> {
                ouMainScene = new OUMainPage();
                window.setScene(ouMainScene);
            });

            layout.setAlignment(Pos.BASELINE_CENTER);
            layout.setHgap(10);
            layout.setVgap(10);
            layout.setPadding(new Insets(25, 25, 25, 25));

            layout.add(transTitle, 0, 0, 2, 1);
            layout.add(backBtn, 9, 26, 2, 1);
        }
    }

    class MyProfilePage extends Scene{
        GridPane layout;
        Text sceneTitle;
        Text itemsSale;
        Text ratings;
        Label username;
        Label name;
        TextField searchBar;
        Button searchBtn;
        Button backBtn;
        MenuButton menu;
        MenuItem profile;
        MenuItem myTranHist;
        MenuItem signOut;
        String [] personalInfo;


        public MyProfilePage() {
            super(new GridPane(), 700, 700);
            layout = (GridPane) this.getRoot();
            sceneTitle = new Text("<banner>NotAmazon logo<banner>");
            itemsSale = new Text("Items for sale");
            ratings = new Text("Ratings");
            searchBar = new TextField();

            personalInfo = DataManager.getPersonalInfo(thisUser);

            searchBtn = new Button("Search");
            backBtn = new Button("Back");

            sceneTitle.setFont(Font.font("Segoe UI Bold", 25));
            itemsSale.setFont(Font.font("Segoe UI Bold",25));
            ratings.setFont(Font.font("Segoe UI Bold",25));

            //dropdown menu
            menu = new MenuButton("My NotAmazon");
            profile = new MenuItem("Profile");
            myTranHist = new MenuItem("My Transaction History");
            signOut = new MenuItem("Sign Out");
            menu.getItems().addAll(profile, myTranHist, signOut);

            profile.setOnAction(event -> {
                myProfileScene = new MyProfilePage();
                window.setScene(myProfileScene);
            });

            myTranHist.setOnAction(event -> {
                transScene = new TransactionPage();
                window.setScene(transScene);
            });

            signOut.setOnAction(event -> {
                guMainScene = new GUMainPage();
                window.setScene(guMainScene);
            });

            searchBtn.setOnAction(event ->{
                guSearchItemScene = new GUSearchItemPage();
                window.setScene(guSearchItemScene);
            });

            backBtn.setOnAction(event -> {
                ouMainScene = new OUMainPage();
                window.setScene(ouMainScene);
            });

            //rectangle for profile info
            username = new Label(personalInfo[0]);
            name = new Label(personalInfo[1] + " " + personalInfo[2]);
            Rectangle rectangle = new Rectangle(100, 100, 660, 160);
            rectangle.setFill(Color.LIGHTGRAY);
            rectangle.setArcHeight(10.0d);
            rectangle.setArcWidth(10.0d);
            StackPane stack_pane = new StackPane(rectangle, username, name);
            StackPane.setAlignment(username, Pos.TOP_LEFT);
            StackPane.setAlignment(name,Pos.CENTER_LEFT);


            layout.setAlignment(Pos.BASELINE_CENTER);
            layout.setHgap(10);
            layout.setVgap(10);
            layout.setPadding(new Insets(25, 25, 25, 25));

            layout.add(sceneTitle, 0, 0, 2, 1);
            layout.add(searchBar, 0, 1, 2, 1);
            layout.add(searchBtn, 2, 1, 2, 1);
            layout.add(stack_pane, 0, 3, 2, 1);
            layout.add(menu, 4, 1);
            layout.add(itemsSale,0,4,2,1);
            layout.add(ratings,0,5,2,1);

        }
    }

    class GUSearchItemPage extends Scene{
        GridPane layout;
        Text sceneTitle;
        Text searchResultTitle;
        Button loginBtn;
        Button signupBtn;
        TextField searchBar;
        Button searchBtn;

        public GUSearchItemPage() {
            super(new GridPane(),700,700);
            layout = (GridPane)this.getRoot();

            sceneTitle = new Text("<banner>This is the main page of Not Amazon<banner>");
            searchResultTitle = new Text("Search Results:");
            searchBar = new TextField();

            searchResultTitle.setFont(Font.font("Segoe UI Bold",25));

            searchBtn = new Button("Search");
            loginBtn = new Button("Login");
            signupBtn = new Button("Sign Up");

            loginBtn.setOnAction(event -> {
                loginScene = new LoginPage();
                window.setScene(loginScene);
            });

            signupBtn.setOnAction(event -> {
                signupScene = new SignupPage();
                window.setScene(signupScene);
            });

            layout.setAlignment(Pos.BASELINE_CENTER);
            layout.setHgap(10);
            layout.setVgap(10);
            layout.setPadding(new Insets(25, 25, 25, 25));
            //placing objects into scene
            layout.add(sceneTitle, 0, 0, 2, 1);
            layout.add(searchBar, 0, 1, 2, 1);
            layout.add(searchResultTitle, 0, 3, 2, 1);
            layout.add(searchBtn, 2, 1, 2, 1);
            layout.add(loginBtn,4,1,2,1);
            layout.add(signupBtn,6,1,2,1);
        }
    }

    class OUSearchItemPage extends Scene{
        GridPane layout;
        Text sceneTitle;
        Text searchResultTitle;
        MenuButton menu;
        MenuItem profile;
        MenuItem myTranHist;
        MenuItem signOut;
        TextField searchBar;
        Button searchBtn;

        public OUSearchItemPage() {
            super(new GridPane(),700,700);
            layout = (GridPane)this.getRoot();

            sceneTitle = new Text("<banner>This is the main page of Not Amazon<banner>");
            searchResultTitle = new Text("Search Results:");
            searchBar = new TextField();

            searchResultTitle.setFont(Font.font("Segoe UI Bold",25));

            searchBtn = new Button("Search");

            //dropdown menu
            menu = new MenuButton("Select Action");
            profile = new MenuItem("Profile");
            myTranHist = new MenuItem("My Transaction History");
            signOut = new MenuItem("Sign Out");
            menu.getItems().addAll(profile, myTranHist, signOut);


            profile.setOnAction(event -> {
                myProfileScene = new MyProfilePage();
                window.setScene(myProfileScene);
            });

            myTranHist.setOnAction(event -> {
                transScene = new TransactionPage();
                window.setScene(transScene);
            });

            signOut.setOnAction(event -> {
                guMainScene = new GUMainPage();
                window.setScene(guMainScene);
            });
            //dropdown menu

            layout.setAlignment(Pos.BASELINE_CENTER);
            layout.setHgap(10);
            layout.setVgap(10);
            layout.setPadding(new Insets(25, 25, 25, 25));
            //placing objects into scene
            layout.add(sceneTitle, 0, 0, 2, 1);
            layout.add(menu, 4, 1, 2, 1);
            layout.add(searchBar, 0, 1, 2, 1);
            layout.add(searchResultTitle, 0, 3, 2, 1);
            layout.add(searchBtn, 2, 1, 2, 1);

        }
    }

    class SUMainPage extends Scene{
        GridPane layout;
        Text sceneTitle;
        Text pendAppTitle;
        Text pendItemAppTitle;
        Text reportTitle;
        Text blackListTitle;
        TextField searchBar;
        Button signOutBtn;
        Button searchBtn;
        Image userAppImage;
        Image itemAppImage;
        Image reportImage;
        Image bListImage;
        ImageView userAppView;
        ImageView itemAppView;
        ImageView reportView;
        ImageView bListView;
        MenuButton menu;
        MenuItem profile;
        MenuItem myTranHist;
        MenuItem signOut;

        public SUMainPage() {
            super(new GridPane(),700,700);
            layout = (GridPane)this.getRoot();
            sceneTitle = new Text("<banner>This is the main page of Not Amazon<banner>");
            pendAppTitle = new Text("Pending Apps");
            reportTitle = new Text("Report/Warnings");
            pendItemAppTitle = new Text("Pending Items Apps");
            blackListTitle = new Text("Blacklisted Item");

            searchBar = new TextField();

            pendAppTitle.setFont(Font.font("Segoe UI Bold",25));
            reportTitle.setFont(Font.font("Segoe UI Bold",25));
            pendItemAppTitle.setFont(Font.font("Segoe UI Bold",25));
            blackListTitle.setFont(Font.font("Segoe UI Bold",25));

            userAppImage = new Image("appImage.jpg");
            userAppView = new ImageView();
            userAppView.setImage(userAppImage);
            userAppView.setFitHeight(200);
            userAppView.setFitWidth(200);
            userAppView.setOnMouseClicked((MouseEvent e) -> {
            	pendAppScene = new PendAppPage();
            	window.setScene(pendAppScene);
            });

            itemAppImage = new Image("itemApp.png");
            itemAppView = new ImageView();
            itemAppView.setImage(itemAppImage);
            itemAppView.setFitHeight(200);
            itemAppView.setFitWidth(200);
            itemAppView.setPickOnBounds(true);
            itemAppView.setOnMouseClicked((MouseEvent) -> {
            	pendItemScene = new PendItemPage();
            	window.setScene(pendItemScene);
            });

            reportImage = new Image("reportImage.png");
            reportView = new ImageView();
            reportView.setImage(reportImage);
            reportView.setFitHeight(200);
            reportView.setFitWidth(200);
            reportView.setOnMouseClicked((MouseEvent) -> {
            	pendReportScene = new ReportPage();
            	window.setScene(pendReportScene);
            });

            bListImage = new Image("blackListImage.png");
            bListView = new ImageView();
            bListView.setImage(bListImage);
            bListView.setFitHeight(200);
            bListView.setFitWidth(200);
            bListView.setOnMouseClicked((MouseEvent) -> {
            	bListScene = new BlackListPage();
            	window.setScene(bListScene);
            });


            searchBtn = new Button("Search");
            searchBtn.setOnAction(event -> {
                ouSearchItemScene = new OUSearchItemPage();
                window.setScene(ouSearchItemScene);
            });

            //dropdown menu
            menu = new MenuButton("My NotAmazon");
            profile = new MenuItem("Profile");
            myTranHist = new MenuItem("My Transaction History");
            signOut = new MenuItem("Sign Out");
            menu.getItems().addAll(profile, myTranHist, signOut);


            profile.setOnAction(event -> {
                myProfileScene = new MyProfilePage();
                window.setScene(myProfileScene);
            });

            myTranHist.setOnAction(event -> {
                transScene = new TransactionPage();
                window.setScene(transScene);
            });

            signOut.setOnAction(event -> {
                guMainScene = new GUMainPage();
                window.setScene(guMainScene);
            });
            //dropdown menu


            layout.setAlignment(Pos.BASELINE_CENTER);
            layout.setHgap(10);
            layout.setVgap(10);
            layout.setPadding(new Insets(25, 25, 25, 25));
            //placing objects into scene
            layout.add(sceneTitle, 0, 0, 2, 1);
            layout.add(menu, 3, 1);
            layout.add(searchBar, 0, 1, 2, 1);
            layout.add(pendAppTitle, 0, 3, 2, 1);
            layout.add(userAppView, 0, 6, 2, 1);
            layout.add(reportTitle, 0, 10, 2, 1);
            layout.add(reportView, 0, 13, 2, 1);
            layout.add(searchBtn, 2, 1, 1, 1);
            layout.add(pendItemAppTitle, 2, 3, 2, 1);
            layout.add(itemAppView, 2, 6, 2, 1);
            layout.add(blackListTitle, 2, 10, 2, 1);
            layout.add(bListView, 2, 13, 2, 1);
        }
    }

    class PendAppPage extends Scene{
    	GridPane layout;
    	Text sceneTitle;
    	Button backBtn;
    	Button viewBtn;
    	ObservableList<String> listOfApp;
        ListView<String> appListView;
        Alert confirm;

    	public PendAppPage() {
    		super(new GridPane(),700,700);
            layout = (GridPane)this.getRoot();
            listOfApp = FXCollections.observableArrayList(DataManager.getListOfApp());
            appListView = new ListView<>(listOfApp);
            sceneTitle = new Text("Pending User Applications");
            confirm = new Alert(AlertType.CONFIRMATION, "Approve Application?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);

            sceneTitle.setFont(Font.font("Segoe UI Bold",25));

            viewBtn = new Button("View");
            viewBtn.setOnAction(e -> {
            	currentApp = appListView.getSelectionModel().getSelectedItem().toString();
            	if(appListView.getSelectionModel().getSelectedItem() != null){
            		confirm.showAndWait();
            		if(confirm.getResult() == ButtonType.YES) {
            			DataManager.addNewUser(currentApp);
                		DataManager.deleteNewUser(currentApp);
                		pendAppScene = new PendAppPage();
                		window.setScene(pendAppScene);
            		}
            		else if(confirm.getResult() == ButtonType.NO) {
            			DataManager.deleteNewUser(currentApp);
                    	pendAppScene = new PendAppPage();
                    	window.setScene(pendAppScene);
            		}
            	}
            });
            
            backBtn = new Button("Back");
            backBtn.setOnAction(event -> {
            	suMainScene = new SUMainPage();
            	window.setScene(suMainScene);
            });
            
            appListView.setPrefWidth(300);
            appListView.setPrefHeight(400);
            appListView.setOrientation(Orientation.VERTICAL);

            layout.setAlignment(Pos.BASELINE_CENTER);
            layout.setHgap(10);
            layout.setVgap(10);
            layout.setPadding(new Insets(25, 25, 25, 25));

            layout.add(sceneTitle, 0, 0, 2, 1);
            layout.add(appListView, 0, 1, 2, 1);
            layout.add(backBtn, 2, 0, 2, 1);
            layout.add(viewBtn, 0, 2, 2, 1);
    	}
    }

    class PendItemPage extends Scene{
    	GridPane layout;
    	Text sceneTitle;
    	Button backBtn;
    	ObservableList<String> listOfItem;
        ListView<String> itemListView;

    	public PendItemPage() {
    		super(new GridPane(),700,700);
            layout = (GridPane)this.getRoot();
            listOfItem = FXCollections.observableArrayList(DataManager.getListOfItemApp());
            itemListView = new ListView<>(listOfItem);
            sceneTitle = new Text("Pending Item Applications");

            sceneTitle.setFont(Font.font("Segoe UI Bold",25));

            backBtn = new Button("Back");
            backBtn.setOnAction(event -> {
            	suMainScene = new SUMainPage();
            	window.setScene(suMainScene);
            });
            
            layout.setAlignment(Pos.BASELINE_CENTER);
            layout.setHgap(10);
            layout.setVgap(10);
            layout.setPadding(new Insets(25, 25, 25, 25));
            
            layout.add(sceneTitle, 0, 0, 2, 1);
            layout.add(itemListView, 0, 1, 2, 1);
            layout.add(backBtn, 2, 0, 2, 1);
            
    	}
    }

    class ReportPage extends Scene{
    	GridPane layout;
    	Text sceneTitle;
    	Button backBtn;
    	ObservableList<String> listOfReport;
        ListView<String> reportListView;

    	public ReportPage() {
    		super(new GridPane(),700,700);
            layout = (GridPane)this.getRoot();
            listOfReport = FXCollections.observableArrayList();
            reportListView = new ListView<>(listOfReport);
            sceneTitle = new Text("Report Page");

            sceneTitle.setFont(Font.font("Segoe UI Bold",25));

            backBtn = new Button("Back");
            backBtn.setOnAction(event -> {
            	suMainScene = new SUMainPage();
            	window.setScene(suMainScene);
            });

            layout.setAlignment(Pos.BASELINE_CENTER);
            layout.setHgap(10);
            layout.setVgap(10);
            layout.setPadding(new Insets(25, 25, 25, 25));

            layout.add(sceneTitle, 0, 0, 2, 1);
            layout.add(reportListView, 0, 1, 2, 1);
            layout.add(backBtn, 2, 0, 2, 1);

    	}
    }

    class BlackListPage extends Scene{
    	GridPane layout;
    	Text sceneTitle;
    	Button backBtn;
    	ObservableList<String> blackList;
        ListView<String> blackListView;

    	public BlackListPage() {
    		super(new GridPane(),700,700);
            layout = (GridPane)this.getRoot();
            blackList = FXCollections.observableArrayList();
            blackListView = new ListView<>(blackList);
            sceneTitle = new Text("Black List Page");

            sceneTitle.setFont(Font.font("Segoe UI Bold",25));

            backBtn = new Button("Back");
            backBtn.setOnAction(event -> {
            	suMainScene = new SUMainPage();
            	window.setScene(suMainScene);
            });

            layout.setAlignment(Pos.BASELINE_CENTER);
            layout.setHgap(10);
            layout.setVgap(10);
            layout.setPadding(new Insets(25, 25, 25, 25));

            layout.add(sceneTitle, 0, 0, 2, 1);
            layout.add(blackListView, 0, 1, 2, 1);
            layout.add(backBtn, 2, 0, 2, 1);

    	}
    }

    class SellPage extends Scene{
    	GridPane layout;
    	Text sellTitle;
    	
    	public SellPage() {
    		super(new GridPane(),700,700);
            layout = (GridPane)this.getRoot();
            
            sellTitle = new Text("Sell/Auction");
            
            sellTitle.setFont(Font.font("Segoe UI Bold",25));
            
    	}
    }
}