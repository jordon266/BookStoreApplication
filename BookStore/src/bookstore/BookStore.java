/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package bookstore;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import java.util.ArrayList;
/**
 *
 * @author jjmurray
 */
public class BookStore extends Application {
    private ArrayList<User> users;
    private ArrayList<Book> books;
    private PointsSystem pointsystem;
    private User currentUser = null;
    
    public void addUser(User user){
        this.users.add(user);
    }
    public void addBook(Book book){
        this.books.add(book);
    }

    public boolean authenticate(User l_user){
        return users.contains(l_user);
    }
    public boolean delete(User d_user){
        if(users.contains(d_user)){
            users.remove(d_user);
            return true;
        }
        return false;
    }
        public boolean delete(Book book){
        if(books.contains(book)){
            books.remove(books);
            return true;
        }
        return false;
    }
    
    @Override
    public void start(Stage primaryStage) {
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("Hello World!");
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
