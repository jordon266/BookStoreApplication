/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.VPos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;

/**
 *
 * @author jjmurray
 */
public class OwnerStartState extends UIState {
    public OwnerStartState(BookStore DB){
        super(DB);
    }
    public void changeState(UIState state ){
        BookStore DB = super.getDataBase();
        DB.setState(state);
    }
    @Override
    public Scene buildUI(){
        BookStore DB = super.getDataBase();
        Button books = new Button();
        Button customers = new Button();
        Button logout = new Button();
        books.setText("Books");
        books.setMinWidth(200);
        books.setMinHeight(60);
        
        customers.setText("Customers");
        customers.setMinWidth(200);
        customers.setMinHeight(60);
        logout.setText("Logout");
        logout.setMinWidth(200);
        logout.setMinHeight(60);

        
        GridPane gp = new GridPane();
        gp.setPadding(new Insets(50,50,50,50));
        gp.setVgap(20);
        gp.add(books, 2, 0);
        gp.add(customers, 2, 1);
        gp.add(logout, 2, 2);

        
        customers.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                changeState(new OwnerCustomersState(DB));
            }
        });
        books.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                changeState(new OwnerBooksState(DB));
            }
        });
        logout.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                DB.setCurrentUser(null);
                changeState(new LoginState(DB));
            }
        });

        

        Scene scene = new  Scene(gp,300,300);
        //System.out.println("This is my gridpane" +gp.getChildren());
        return scene;
        
        
    }
}
