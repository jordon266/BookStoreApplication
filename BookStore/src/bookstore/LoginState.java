/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
//import javafx.scene.layout.StackPane;
//import javafx.collections.ObservableList;

/**
 *
 * @author jjmurray
 */
public class LoginState extends UIState {
    public LoginState(BookStore DB){
        super(DB);
    }
    public boolean isOwner(){
        return super.getDataBase().getCurrentUser() instanceof Owner;
    }
    public boolean authenticate(User l_user){
        BookStore DB = super.getDataBase();
        User currentUser = DB.getCurrentUser();
        User owner = DB.getOwner();
        
        if(l_user.getUserName().equals("admin") && l_user.getPassword().equals("admin")){
            DB.setCurrentUser(owner);
            return true;
        }
        if(super.getDataBase().getUsers().contains(l_user)){
//            currentUser = l_user;
            DB.setCurrentUser(DB.getUser(l_user));
            return true;
        }
        return false    ;
    }
    public boolean login(String username, String password){
        User temp = new Customer(username, password);
        boolean isUser = authenticate(temp);
        return isUser;
    }
    
    public void changeState(UIState state ){
        BookStore DB = super.getDataBase();
        DB.setState(state);
    }
    @Override
    public Scene buildUI(){
        BookStore DB =super.getDataBase();
        GridPane root = new GridPane();
        Button loginButton = new Button();
        loginButton.setText("Login");
        
        //Creating the labels
        Label welcomeBanner = new Label("Welcome to the Bookstore");
        Label userNameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");
        
        //Creating the user and password text field
        TextField userNameField = new TextField();
        PasswordField passwordField = new PasswordField();
       
        GridPane.setMargin(welcomeBanner, new Insets(0, 0, 10, 0));
        GridPane.setMargin(userNameField, new Insets(5, 0, 5, 0));
        GridPane.setMargin(passwordField, new Insets(5, 0, 5, 0));
        GridPane.setMargin(userNameLabel, new Insets(5, 0, 5, 5));
        GridPane.setMargin(passwordLabel, new Insets(5, 0, 5, 5));
        GridPane.setMargin(loginButton, new Insets(5, 0, 5, 5));
        
        //Adding the all elements to the grid
        GridPane.setConstraints(welcomeBanner,0,0);
        GridPane.setConstraints(userNameLabel,0,1);
        GridPane.setConstraints(passwordLabel,0,2);
        GridPane.setConstraints(userNameField,1,1);
        GridPane.setConstraints(passwordField,1,2);
        GridPane.setConstraints(loginButton, 1, 3);
        root.getChildren().addAll(welcomeBanner,loginButton,userNameLabel,userNameField,passwordLabel,passwordField);

        
        loginButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                boolean userIsLoggedIn = login(userNameField.getText(),passwordField.getText());
                if(userIsLoggedIn){
                    if(isOwner()){
                          UIState ownerStart = new OwnerStartState(DB);
                          DB.setState(ownerStart);
                         
                    } else {
                          UIState customerStart = new CustomerStartState(DB);
                          DB.setState(customerStart);
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setContentText("Invalid User name or Invalid Password");
                    alert.setHeaderText("Sorry Wrong Login");
                    alert.show();
                }              
            }
        });
        
        Scene scene = new Scene(root, 400, 150);
        return scene;
    }
     
}
