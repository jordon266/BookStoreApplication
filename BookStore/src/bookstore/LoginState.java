/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.collections.ObservableList;
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
        
        if(l_user.getName().equals("admin") && l_user.getPassword().equals("admin")){
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
    
    public Scene buildUI(){
        GridPane root = new GridPane();
        Button loginbtn = new Button();
        loginbtn.setText("Login");
        Label welcomeBanner = new Label("Welcome to the Bookstore");
        Label userNameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password");
        TextField userNameField = new TextField();
        TextField passwordField = new TextField();
        Button btn = new Button();
        GridPane.setConstraints(welcomeBanner,0,0);
        GridPane.setConstraints(userNameLabel,0,1);
        GridPane.setConstraints(passwordLabel,0,2);
        GridPane.setConstraints(userNameField,1,1);
        GridPane.setConstraints(passwordField,1,2);
        GridPane.setConstraints(loginbtn, 1, 3);
        root.getChildren().addAll(welcomeBanner,loginbtn,userNameLabel,userNameField,passwordLabel,passwordField);

//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
//            
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("BookStore");
//            }
//        });
        Scene scene = new Scene(root, 400, 150);
        return scene;
    }
     
}
