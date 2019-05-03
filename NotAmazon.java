import javafx.application.*;
import javafx.geometry.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.*;
import javafx.scene.layout.GridPane;
import javafx.scene.*;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.paint.*;
import javafx.collections.*;
import javafx.scene.control.*;



public class NotAmazon extends Application{
    
    private Stage window;
    private GUMainPage guMainScene;
    private LoginPage loginScene;
    private SignupPage signupScene;
    private OUMainPage ouMainScene;
    private TransactionPage transScene;
    private MyProfilePage myProfileScene;
    
    
    public static void main(String[]args){
        launch(args);
    }
    
    @Override
    public void start(Stage stage){
        window = stage;
        
        initialize();
        window.setScene(ouMainScene);
        window.show();
    }
    
    public void initialize(){
        //sets up values for variables
        //N/A to be filled in later
        guMainScene = new GUMainPage();
        loginScene = new LoginPage();
        signupScene = new SignupPage();
        ouMainScene = new OUMainPage();
        transScene = new TransactionPage();
        myProfileScene = new MyProfilePage();
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
        MenuButton menu;
        MenuItem login;
        MenuItem signup;
        TextField searchBar;
        Button searchBtn;
        
        
        
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
            
            //dropdown menu
            menu = new MenuButton("Select Action");
            login = new MenuItem("Login");
            signup = new MenuItem("Sign Up");
            menu.getItems().addAll(login, signup);
            
            login.setOnAction(event -> {
                loginScene = new LoginPage();
                window.setScene(loginScene);
            });
            
            signup.setOnAction(event -> {
                signupScene = new SignupPage();
                window.setScene(signupScene);
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
            layout.add(searchBtn, 2, 1, 2, 1);
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
        TextField pass_TextField;
        Button loginBtn;
        Button cancelBtn;
        HBox aBtn;
        HBox cBtn;
        
        public LoginPage() {
            super(new GridPane(),320,200);
            layout = (GridPane)this.getRoot();
            
            login = new Label("Login");
            user = new Label("Username/ID:");
            pass = new Label("Password:");
            
            usr_TextField = new TextField();
            pass_TextField = new TextField();
            
            loginBtn = new Button("Login");
            cancelBtn = new Button("Cancel");
            aBtn = new HBox(5);
            cBtn = new HBox(5);
            
            login.setFont(Font.font("Segoe UI Bold",25));
            
            aBtn.setAlignment(Pos.BOTTOM_RIGHT);
            aBtn.getChildren().add(loginBtn);
            loginBtn.setOnAction(event ->{
                
            });
            
            cBtn.setAlignment(Pos.BOTTOM_LEFT);
            cBtn.getChildren().add(cancelBtn);
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
            user = new Label("Username/ID:");
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
        MenuButton menu;
        MenuItem profile;
        MenuItem myTranHist;
        MenuItem signOut;
        TextField searchBar;
        Button searchBtn;
        HBox sBtn;
        
        
        public OUMainPage() {
            super(new GridPane(),700,700);
            layout = (GridPane)this.getRoot();
            window.setTitle("Not Amazon");
            sceneTitle = new Text("<banner>This is the main page of Not Amazon<banner>");
            recItemTitle = new Text("Recommended");
            popItemTitle = new Text("Popular");
            
            searchBar = new TextField();
            
            searchBtn = new Button("Search");
            
            recItemTitle.setFont(Font.font("Segoe UI Bold",25));
            popItemTitle.setFont(Font.font("Segoe UI Bold",25));
            
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
            
            searchBtn.setOnAction(event -> {
                
            });
            
            layout.setAlignment(Pos.BASELINE_CENTER);
            layout.setHgap(10);
            layout.setVgap(10);
            layout.setPadding(new Insets(25, 25, 25, 25));
            //placing objects into scene
            layout.add(sceneTitle, 0, 0, 2, 1);
            layout.add(menu, 4, 1, 2, 1);
            layout.add(searchBar, 0, 1, 2, 1);
            layout.add(searchBtn, 2, 1, 2, 1);
            layout.add(recItemTitle, 0, 3, 2, 1);
            layout.add(popItemTitle, 0, 26, 2, 1);
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
        Text myProfileTitle;
        Button backBtn;
        
        public MyProfilePage(){
            super(new GridPane(),700,700);
            layout = (GridPane)this.getRoot();
            myProfileTitle = new Text("Temp Title");
            backBtn = new Button("Back");
            
            myProfileTitle.setFont(Font.font("Segoe UI Bold",25));
            
            backBtn.setOnAction(event -> {
                ouMainScene = new OUMainPage();
                window.setScene(ouMainScene);
            });
            
            layout.setAlignment(Pos.BASELINE_CENTER);
            layout.setHgap(10);
            layout.setVgap(10);
            layout.setPadding(new Insets(25, 25, 25, 25));
            
            layout.add(myProfileTitle, 0, 0, 2, 1);
            layout.add(backBtn, 9, 26, 2, 1);
        }
    }
    
}
