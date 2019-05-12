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
import javafx.scene.effect.DropShadow;


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
    private ViewItemPage viewItemScene;
    private MyAccountPage myAccountScene;
    private EditAddressPage editAddrScene;
    private EditNamePage editNameScene;
    private EditPasswordPage editPWScene;
    //private EditPhoneNum editPhoneScene;
    //private EditCCNum editCCScene;
    
    private String thisUser;
    private String thisAdmin;
    private String thisItem;
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
        viewItemScene = new ViewItemPage();
        myAccountScene = new MyAccountPage();
        editAddrScene = new EditAddressPage();
        editNameScene = new EditNamePage();
        editPWScene = new EditPasswordPage();
        //editPhoneScene = new EditPhoneNum();
        //editCCScene = new EditCCNum();
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

        private boolean validateFields(){
            if(usr_TextField.getText().isEmpty() | pass_TextField.getText().isEmpty()){

                Alert warnUsr = new Alert(AlertType.ERROR);
                warnUsr.setTitle("Invalid Credentials");
                warnUsr.setHeaderText(null);
                warnUsr.setContentText("Invalid username and/or password. Please try again.");
                warnUsr.showAndWait();

                return false;
            }
            return true;
        }
        
        public LoginPage() {
            super(new GridPane(),300,220);
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
                if(validateFields()) {
                    String tempUsername = usr_TextField.getText();
                    String tempPassword = pass_TextField.getText();
                    if (DataManager.isValidAdmin(tempUsername, tempPassword)) {
                        thisAdmin = tempUsername;
                        usr_TextField.setText("");
                        pass_TextField.setText("");
                        suMainScene = new SUMainPage();
                        window.setScene(suMainScene);
                    }
                    if (DataManager.isValidUser(tempUsername, tempPassword)) {
                        thisUser = tempUsername;
                        usr_TextField.setText("");
                        pass_TextField.setText("");
                        suMainScene = new SUMainPage();
                        window.setScene(ouMainScene);
                    }
                }
            });

            cBtn.setAlignment(Pos.BOTTOM_LEFT);
            cBtn.getChildren().add(cancelBtn);
            cancelBtn.setOnAction(e -> window.setScene(guMainScene));
            
            
            // action event
            EventHandler<ActionEvent> pressEnter = new EventHandler<ActionEvent>() {
                public void handle(ActionEvent e) {
                    if (validateFields()) {
                        String tempUsername = usr_TextField.getText();
                        String tempPassword = pass_TextField.getText();
                        if (DataManager.isValidAdmin(tempUsername, tempPassword)) {
                            thisAdmin = tempUsername;
                            usr_TextField.setText("");
                            pass_TextField.setText("");
                            suMainScene = new SUMainPage();
                            window.setScene(suMainScene);
                        }
                        if (DataManager.isValidUser(tempUsername, tempPassword)) {
                            thisUser = tempUsername;
                            usr_TextField.setText("");
                            pass_TextField.setText("");
                            suMainScene = new SUMainPage();
                            window.setScene(ouMainScene);
                        }
                    }
                }
            };

            // when enter is pressed
            pass_TextField.setOnAction(pressEnter);
            

            cancelBtn.setOnAction(e -> window.setScene(guMainScene));

            layout.setAlignment(Pos.CENTER);
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
        Tooltip t1,t2,t3;

        private boolean validateFields(){
            if(usr_TextField.getText().isEmpty() | first_TextField.getText().isEmpty() | last_TextField.getText().isEmpty() |
            addr_TextField.getText().isEmpty() | phone_TextField.getText().isEmpty() | cc_TextField.getText().isEmpty()){

                Alert warnUsr = new Alert(AlertType.WARNING);
                warnUsr.setTitle("Warning");
                warnUsr.setHeaderText("An empty field has been detected.");
                warnUsr.setContentText("Please complete the application and submit again.");
                warnUsr.showAndWait();

                return false;
            }
            return true;
        }
        
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

            t1 = new Tooltip("Exclude country code and dashes.");
            t2 = new Tooltip("Input your 16-digit card while excluding space.");

            phone_TextField.setTooltip(t1);
            cc_TextField.setTooltip(t2);

            t1.setFont(Font.font("Segoe UI",12));
            t2.setFont(Font.font("Segoe UI",12));
            
            applyBtn = new Button("Submit");
            cancelBtn = new Button("Cancel");
            aBtn = new HBox(5);
            cBtn = new HBox(5);



            applyBtn.setOnAction(e -> {
                if(validateFields()){
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
            
            layout.setAlignment(Pos.CENTER);
            layout.setHgap(10);
            layout.setVgap(8);
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
            layout.add(sellTitle, 2, 3, 1, 1);
            layout.add(sellListView, 2, 4, 2, 1);
            layout.add(bidTitle, 2, 5, 1, 1);
            layout.add(bidListView, 2, 6, 2, 1);
            layout.add(friendTitle, 2, 7, 1, 1);
            layout.add(friendListView, 2, 8, 2, 1);
            layout.add(menu, 3, 1, 2, 1);
            layout.add(sellBtn, 3, 3, 2, 1);
            layout.add(friendBtn, 3, 7, 2, 1);
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
        MenuItem myAcc;
        MenuItem myTranHist;
        MenuItem signOut;
        String [] personalInfo;
        MenuItem item; //TEMP FOR TESTING


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

            DropShadow dropShadow = new DropShadow();
            dropShadow.setOffsetX(5);
            dropShadow.setOffsetY(5);

            //dropdown menu
            menu = new MenuButton("My NotAmazon");
            profile = new MenuItem("Profile");
            myAcc = new MenuItem("My Account");
            myTranHist = new MenuItem("My Transaction History");
            item = new MenuItem("Item"); //TEMP FOR TESTING
            signOut = new MenuItem("Sign Out");
            menu.getItems().addAll(profile, myAcc, myTranHist, item, signOut);

            profile.setOnAction(event -> {
                myProfileScene = new MyProfilePage();
                window.setScene(myProfileScene);
            });

            myAcc.setOnAction(event -> {
                myAccountScene = new MyAccountPage();
                window.setScene(myAccountScene);
            });

            myTranHist.setOnAction(event -> {
                transScene = new TransactionPage();
                window.setScene(transScene);
            });

            //TEMP FOR TESTING
            item.setOnAction(event -> {
                viewItemScene = new ViewItemPage();
                window.setScene(viewItemScene);
            });

            signOut.setOnAction(event -> {
                guMainScene = new GUMainPage();
                window.setScene(guMainScene);
            });

            searchBtn.setOnAction(event ->{
                ouSearchItemScene = new OUSearchItemPage();
                window.setScene(ouSearchItemScene);
            });

            backBtn.setOnAction(event -> {
                ouMainScene = new OUMainPage();
                window.setScene(ouMainScene);
            });

            //rectangle for profile info
            username = new Label("  " + personalInfo[0]);
            name = new Label("  " + personalInfo[1] + " " + personalInfo[2]);
            Rectangle rectangle = new Rectangle(100, 100, 660, 160);
            rectangle.setFill(Color.LIGHTGRAY);
            rectangle.setArcHeight(10.0d);
            rectangle.setArcWidth(10.0d);
            rectangle.setEffect(dropShadow);
            StackPane stack_pane = new StackPane(rectangle, username, name);
            StackPane.setAlignment(username, Pos.TOP_LEFT);
            StackPane.setAlignment(name,Pos.CENTER_LEFT);

            username.setFont(Font.font("Segoe UI Bold", 15));
            name.setFont(Font.font("Segoe UI Bold", 15));

            layout.setAlignment(Pos.BASELINE_CENTER);
            layout.setHgap(10);
            layout.setVgap(5);
            layout.setPadding(new Insets(25, 25, 25, 25));

            layout.add(sceneTitle, 0, 0, 2, 1);
            layout.add(searchBar, 0, 1, 2, 1);
            layout.add(searchBtn, 2, 1, 2, 1);
            layout.add(stack_pane, 0, 4, 2, 1);
            layout.add(menu, 4, 1);
            layout.add(itemsSale,0,6,2,1);
            layout.add(ratings,0,8,2,1);

        }
    }

    class MyAccountPage extends Scene{
        GridPane layout;
        Text sceneTitle;
        Label username;
        Label name;
        Label address;
        Label phonenum;
        TextField searchBar;
        Button searchBtn;
        Button backBtn;
        Button editNameBtn;
        Button editAddrBtn;
        Button editPhoneBtn;
        Button editCCBtn;
        Button editPWBtn;
        MenuButton menu;
        MenuItem profile;
        MenuItem myAcc;
        MenuItem myTranHist;
        MenuItem signOut;
        String [] personalInfo;

        public MyAccountPage(){
            super(new GridPane(), 700, 350);
            layout = (GridPane) this.getRoot();
            sceneTitle = new Text("<banner>NotAmazon logo<banner>");
            personalInfo = DataManager.getPersonalInfo(thisUser);

            searchBar = new TextField();
            searchBtn = new Button("Search");
            backBtn = new Button("Back");
            editNameBtn = new Button("Change");
            editAddrBtn = new Button("Change");
            editPhoneBtn = new Button("Change");
            editCCBtn = new Button("Change");
            editPWBtn = new Button("Change password");

            sceneTitle.setFont(Font.font("Segoe UI Bold", 20));

            username = new Label("Username: " + personalInfo[0]);
            name = new Label("Name: " + personalInfo[1] + " " + personalInfo[2]);
            address = new Label("Address: " + personalInfo[3]);
            phonenum = new Label("Phone Number: " + personalInfo[4]);

            username.setFont(Font.font("Segoe UI Bold", 13));
            name.setFont(Font.font("Segoe UI Bold",13));
            address.setFont(Font.font("Segoe UI Bold",13));
            phonenum.setFont(Font.font("Segoe UI Bold",13));

            //dropdown menu
            menu = new MenuButton("My NotAmazon");
            profile = new MenuItem("Profile");
            myAcc = new MenuItem("My Account");
            myTranHist = new MenuItem("My Transaction History");
            signOut = new MenuItem("Sign Out");
            menu.getItems().addAll(profile, myAcc, myTranHist, signOut);

            profile.setOnAction(event -> {
                myProfileScene = new MyProfilePage();
                window.setScene(myProfileScene);
            });

            myAcc.setOnAction(event -> {
                myAccountScene = new MyAccountPage();
                window.setScene(myAccountScene);
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
                ouSearchItemScene = new OUSearchItemPage();
                window.setScene(ouSearchItemScene);
            });

            backBtn.setOnAction(event -> {
                ouMainScene = new OUMainPage();
                window.setScene(ouMainScene);
            });

            editNameBtn.setOnAction(event -> {
                editNameScene = new EditNamePage();
                window.setScene(editNameScene);
            });

            editAddrBtn.setOnAction(event -> {
                editAddrScene = new EditAddressPage();
                window.setScene(editAddrScene);
            });

            /*editPhoneBtn.setOnAction(event -> {
                editPhoneScene = new EditPhoneNum();
                window.setScene(editPhoneScene);
            });

            editCCBtn.setOnAction((event -> {
                editCCScene = new EditCCNum();
                window.setScene(editCCScene);
            }));*/

            editPWBtn.setOnAction(event -> {
                editPWScene = new EditPasswordPage();
                window.setScene(editPWScene);
            });

            layout.setAlignment(Pos.BASELINE_CENTER);
            layout.setHgap(10);
            layout.setVgap(5);
            layout.setPadding(new Insets(25, 25, 25, 25));

            layout.add(sceneTitle, 0, 0, 2, 1);
            layout.add(searchBar, 0, 1, 2, 1);
            layout.add(searchBtn, 2, 1, 2, 1);
            layout.add(menu, 4, 1);
            layout.add(username, 0,5,2,1);
            layout.add(name,0,6,2,1);
            layout.add(address,0,7,2,1);
            layout.add(phonenum,0,8,2,1);
            layout.add(editNameBtn,2,6);
            layout.add(editAddrBtn,2,7);
            layout.add(editPhoneBtn,2,8);
            layout.add(editPWBtn,0,9);
        }
    }

    class EditNamePage extends Scene{
        GridPane layout;
        Text sceneTitle;
        Text firstname;
        Text lastname;
        TextField firstname_field;
        TextField lastname_field;
        Button updateBtn;
        Button cancelBtn;

        private boolean validateFields(){
            if(firstname_field.getText().isEmpty() | lastname_field.getText().isEmpty()){

                Alert warnUsr = new Alert(AlertType.WARNING);
                warnUsr.setTitle("Warning");
                warnUsr.setHeaderText("An empty field has been detected.");
                warnUsr.setContentText("Input into the field (you do not wish to change) with the currently existing " +
                        "information (i.e. if you want to change just your first name, input \"Jane Doe\" if you want " +
                        "to change it from \"John Doe\").");
                warnUsr.showAndWait();

                return false;
            }
            return true;
        }

        public EditNamePage(){
            super(new GridPane(), 300, 200);
            layout = (GridPane) this.getRoot();

            sceneTitle = new Text("Update Information: Name");
            firstname = new Text("First name ");
            lastname = new Text("Last name ");

            firstname_field = new TextField();
            firstname_field.setPromptText("Enter your first name.");
            lastname_field = new TextField();
            lastname_field.setPromptText("Enter your last name.");

            updateBtn = new Button("Update");
            cancelBtn = new Button("Cancel");

            updateBtn.setOnAction(event -> {
                if(validateFields()){
                    DataManager.updateUserName(thisUser,firstname_field.getText(),lastname_field.getText());
                    myAccountScene = new MyAccountPage();
                    window.setScene(myAccountScene);
                }
            });

            cancelBtn.setOnAction(event-> {
                myAccountScene = new MyAccountPage();
                window.setScene(myAccountScene);
            });

            updateBtn.setAlignment(Pos.BOTTOM_LEFT);
            cancelBtn.setAlignment(Pos.BOTTOM_LEFT);

            layout.setAlignment(Pos.CENTER);
            layout.setHgap(10);
            layout.setVgap(5);
            layout.setPadding(new Insets(25, 25, 25, 25));

            layout.add(sceneTitle, 0, 0, 3, 1);
            layout.add(firstname,0,3);
            layout.add(lastname,0,4);
            layout.add(firstname_field,1,3,3,1);
            layout.add(lastname_field,1,4,3,1);
            layout.add(updateBtn,7,5);
            layout.add(cancelBtn,6,5);
        }
    }

    class EditAddressPage extends Scene{
        GridPane layout;
        Text sceneTitle;
        Text addr;
        TextField addr_field;
        Button updateBtn;
        Button cancelBtn;

        private boolean validateFields(){
            if(addr_field.getText().isEmpty()){

                Alert warnUsr = new Alert(AlertType.WARNING);
                warnUsr.setTitle("Warning");
                warnUsr.setHeaderText("An empty field has been detected.");
                warnUsr.setContentText("Please fill in the empty field and try again.");
                warnUsr.showAndWait();

                return false;
            }
            return true;
        }

        public EditAddressPage(){
            super(new GridPane(), 250, 200);
            layout = (GridPane) this.getRoot();

            sceneTitle = new Text("Update Information: Address");
            addr = new Text("Address ");

            addr_field = new TextField();
            addr_field.setPromptText("Enter your street address.");

            updateBtn = new Button("Update");
            cancelBtn = new Button("Cancel");

            updateBtn.setOnAction(event -> {
                if(validateFields()){
                    DataManager.updateUserAddr(thisUser,addr_field.getText());
                    myAccountScene = new MyAccountPage();
                    window.setScene(myAccountScene);
                }
            });

            cancelBtn.setOnAction(event-> {
                myAccountScene = new MyAccountPage();
                window.setScene(myAccountScene);
            });

            updateBtn.setAlignment(Pos.BOTTOM_LEFT);
            cancelBtn.setAlignment(Pos.BOTTOM_LEFT);

            layout.setAlignment(Pos.CENTER);
            layout.setHgap(10);
            layout.setVgap(5);
            layout.setPadding(new Insets(25, 25, 25, 25));

            layout.add(sceneTitle, 0, 0, 2, 1);
            layout.add(addr,0,3);
            layout.add(addr_field,1,3,2,1);
            layout.add(updateBtn,1,4);
            layout.add(cancelBtn,0,4);
        }
    }

    class EditPasswordPage extends Scene {
        GridPane layout;
        Text sceneTitle;
        Text newPass;
        Text confirmPass;
        TextField newPass_field;
        TextField confirmPass_field;
        Button updateBtn;
        Button cancelBtn;
        Tooltip t1;
        String[] personalInfo;

        private boolean validateFields() {
            if (newPass_field.getText().isEmpty() | confirmPass_field.getText().isEmpty()){

                Alert warnUsr = new Alert(AlertType.WARNING);
                warnUsr.setTitle("Warning");
                warnUsr.setHeaderText("An empty field has been detected.");
                warnUsr.setContentText("Please fill in the empty field and try again.");
                warnUsr.showAndWait();

                return false;
            }
            return true;
        }

        public EditPasswordPage() {
            super(new GridPane(), 400, 230);
            layout = (GridPane) this.getRoot();
            personalInfo = DataManager.getPersonalInfo(thisUser);

            sceneTitle = new Text("Update Information: Password");
            newPass = new Text("New password ");
            confirmPass = new Text("Confirm new password ");

            newPass_field = new TextField();
            newPass_field.setPromptText("Enter new password.");
            confirmPass_field = new TextField();
            confirmPass_field.setPromptText("Enter again.");

            updateBtn = new Button("Update");
            cancelBtn = new Button("Cancel");

            updateBtn.setOnAction(event -> {
                if (validateFields()) {
                    String newPass1 = newPass_field.getText();
                    String newPass2 = confirmPass_field.getText();
                    if (newPass1 == newPass2) {
                        Alert warnUsr = new Alert(AlertType.WARNING);
                        warnUsr.setTitle("Error");
                        warnUsr.setHeaderText("An incorrect field has been detected.");
                        warnUsr.setContentText("Please fill in the field correctly and try again.");
                        warnUsr.showAndWait();
                    }else {
                        DataManager.updateUserPass(thisUser, newPass_field.getText());
                        myAccountScene = new MyAccountPage();
                        window.setScene(myAccountScene);
                    }
                }
            });

            cancelBtn.setOnAction(event -> {
                myAccountScene = new MyAccountPage();
                window.setScene(myAccountScene);
            });

            updateBtn.setAlignment(Pos.BOTTOM_RIGHT);
            cancelBtn.setAlignment(Pos.BOTTOM_RIGHT);

            layout.setAlignment(Pos.CENTER);
            layout.setHgap(10);
            layout.setVgap(5);
            layout.setPadding(new Insets(25, 25, 25, 25));

            layout.add(sceneTitle, 0, 0, 2, 1);
            layout.add(newPass, 0, 3);
            layout.add(confirmPass, 0, 4);
            layout.add(newPass_field, 1, 3, 2, 1);
            layout.add(confirmPass_field, 1, 4, 2, 1);
            layout.add(updateBtn, 1, 5);
            layout.add(cancelBtn, 0, 5);
        }
    }

    /*class EditPhoneNum extends Scene{

    }

    class EditCCNum extends Scene{

    }*/

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
            layout.add(menu, 4, 1, 2, 1);
            layout.add(searchBar, 0, 1, 2, 1);
            layout.add(searchResultTitle, 0, 3, 2, 1);
            layout.add(searchBtn, 2, 1, 2, 1);

        }
    }

    class ViewItemPage extends Scene{
        GridPane layout;
        Label itemLabel;
        Text sceneTitle;
        Text itemCondition;
        Text timeLeft;
        Text currentBid;
        TextField myBid;
        TextField searchBar;
        Button placeBidBtn;
        Button searchBtn;
        Button backBtn;
        MenuButton menu;
        MenuItem profile;
        MenuItem myTranHist;
        MenuItem item; //TEMP FOR TESTING
        MenuItem signOut;
        String [] itemInfo;

        public ViewItemPage(){
            super(new GridPane(),700,700);
            layout = (GridPane)this.getRoot();

            sceneTitle = new Text("<NotAmazon logo>");
            sceneTitle.setFont(Font.font("Segoe UI Bold",20));

            searchBar = new TextField();

            searchBtn = new Button("Search");
            backBtn = new Button("Back");

            //dropdown menu
            menu = new MenuButton("My NotAmazon");
            profile = new MenuItem("Profile");
            myTranHist = new MenuItem("My Transaction History");
            item = new MenuItem("Item");
            signOut = new MenuItem("Sign Out");
            menu.getItems().addAll(profile, myTranHist, item, signOut);

            profile.setOnAction(event -> {
                myProfileScene = new MyProfilePage();
                window.setScene(myProfileScene);
            });

            myTranHist.setOnAction(event -> {
                transScene = new TransactionPage();
                window.setScene(transScene);
            });

            item.setOnAction(event -> {
                viewItemScene = new ViewItemPage();
                window.setScene(viewItemScene);
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

            //itemInfo = DataManager.getItemInfo(thisItem);

            itemLabel = new Label("<Insert item_name here>"); //itemInfo[0]
            itemCondition = new Text("Condition:  "); //itemInfo[2]
            timeLeft = new Text("Time left:  ");
            currentBid = new Text("Current bid:  "); //itemInfo[1]
            myBid = new TextField();
            placeBidBtn = new Button("Place bid");

            itemLabel.setFont(Font.font("Segoe UI Bold",15));
            itemCondition.setFont(Font.font("Segoe UI",13));
            timeLeft.setFont(Font.font("Segoe UI",13));
            currentBid.setFont(Font.font("Segoe UI",13));

            layout.setVgap(5);
            layout.setHgap(10);
            layout.setPadding(new Insets(25, 25, 25, 25));

            layout.add(sceneTitle,0,0,2,1);
            layout.add(searchBar, 0, 2, 2, 1);
            layout.add(searchBtn, 2, 2, 2, 1);
            layout.add(menu, 4, 2);
            layout.add(itemLabel,0,5,2,1);
            layout.add(itemCondition,0,7);
            layout.add(timeLeft,0,8);
            layout.add(currentBid,0,9);
            layout.add(myBid,0,10,2,1);
            layout.add(placeBidBtn,0,11);
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

            signOutBtn = new Button("Sign Out");
            signOutBtn.setOnAction(event -> {
                guMainScene = new GUMainPage();
                window.setScene(guMainScene);
            });



            layout.setAlignment(Pos.BASELINE_CENTER);
            layout.setHgap(10);
            layout.setVgap(10);
            layout.setPadding(new Insets(25, 25, 25, 25));
            //placing objects into scene
            layout.add(sceneTitle, 0, 0, 2, 1);
            layout.add(signOutBtn, 3, 1);
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
        String [] userInfo;
        String user;
        String first;
        String last;
        String addr;
        String phone;
        String cc;

    	public PendAppPage() {
    		super(new GridPane(),700,700);
            layout = (GridPane)this.getRoot();
            listOfApp = FXCollections.observableArrayList(DataManager.getListOfApp());
            appListView = new ListView<>(listOfApp);
            sceneTitle = new Text("Pending User Applications");


            sceneTitle.setFont(Font.font("Segoe UI Bold",25));

            viewBtn = new Button("View");
            viewBtn.setOnAction(e -> {
            	currentApp = appListView.getSelectionModel().getSelectedItem().toString();
            	userInfo = DataManager.getUserApp(currentApp);
                user = userInfo[0];
                first = userInfo[1];
                last = userInfo[2];
                addr = userInfo[3];
                phone = userInfo[4];
                cc = userInfo[5];

                confirm = new Alert(AlertType.CONFIRMATION,
                		"Username: "+user
                		+"\nFirst Name: "+first
                		+"\nLast Name: "+last
                		+"\nAddress:"+addr
                		+"\nPhone Number:"+phone
                		+"\nCredit Card Number:"+cc
                		+"\nApprove application?", ButtonType.YES, ButtonType.NO, ButtonType.CANCEL);

            	if(appListView.getSelectionModel().getSelectedItem() != null){
            		confirm.showAndWait();
            		if(confirm.getResult() == ButtonType.YES) {
            			DataManager.addNewUser(currentApp);
            			DataManager.defaultUserPass(currentApp);
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