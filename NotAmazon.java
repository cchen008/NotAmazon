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
	private FirstScene firstScene;
	private SecondScene secondScene;
	private ThirdScene thirdScene;
	
	public static void main(String[]args){
		launch(args);
	}
	
	@Override
    public void start(Stage stage){
        window = stage;

        initialize();
        window.setScene(firstScene);
        window.show();
    }
	
	public void initialize(){
		//sets up values for variables
		//N/A to be filled in later
		firstScene = new FirstScene();
		secondScene = new SecondScene();
		thirdScene = new ThirdScene();
	}
	
	@Override
	public void stop() {
		DataManager.shutdown();
	}
	
	class FirstScene extends Scene{
		GridPane layout;
		Text sceneTitle;
		MenuButton menu;
		MenuItem login;
		MenuItem signup;
		
		
		public FirstScene() {
			super(new GridPane(),400,400);
			layout = (GridPane)this.getRoot();
			window.setTitle("Not Amazon");
			sceneTitle = new Text("<banner>This is the main page of Not Amazon<banner>");
			
			//dropdown menu
			menu = new MenuButton("Select Action");
			login = new MenuItem("Login");
			signup = new MenuItem("Sign Up");
			menu.getItems().addAll(login, signup);
			
			login.setOnAction(event -> {
				secondScene = new SecondScene();
			    window.setScene(secondScene);
			});
			
			signup.setOnAction(event -> {
				thirdScene = new ThirdScene();
			    window.setScene(thirdScene);
			});
			//dropdown menu
			
			layout.setAlignment(Pos.BASELINE_CENTER);
            layout.setHgap(10);
            layout.setVgap(10);
            layout.setPadding(new Insets(25, 25, 25, 25));
			//placing objects into scene
			layout.add(sceneTitle, 0, 0, 2, 1);
			layout.add(menu, 0, 1, 2, 1);
		}
	}
	
	class SecondScene extends Scene{
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
		
		public SecondScene() {
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

            cBtn.setAlignment(Pos.BOTTOM_LEFT);
            cBtn.getChildren().add(cancelBtn);
			
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

	class ThirdScene extends Scene{
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
		
		public ThirdScene() {
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
			
			
			signup.setFont(Font.font("Segoe UI Bold",25));
			
			aBtn.setAlignment(Pos.BOTTOM_RIGHT);
            aBtn.getChildren().add(applyBtn);

            cBtn.setAlignment(Pos.BOTTOM_LEFT);
            cBtn.getChildren().add(cancelBtn);
			
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
}
