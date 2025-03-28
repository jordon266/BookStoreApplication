/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.Group;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TextField;

/**
 *
 * @author jjmurray
 */
public class OwnerCustomersState extends UIState {
    private TableView<User> table;
//    private final VBox vb = new VBox();
    private HBox hb ;
    private Group group;
    
    OwnerCustomersState(BookStore DB){
        super(DB);
        table =  new TableView<User>();
        
        hb = new HBox();
        group = new Group();
    }
    
    @Override
    public Scene buildUI(){
        BookStore DB = super.getDataBase();
        System.out.println("In ownerCustomersClass");
        Scene scene = new Scene(new Group(),550,800);
        table.setEditable((true));
        final VBox vb = new VBox();
        final HBox hb = new HBox();
        TableColumn userName = new TableColumn("Username");
        TableColumn password = new TableColumn("Password");
        TableColumn points = new TableColumn("Points");
        userName.setCellValueFactory( new PropertyValueFactory<Customer,String>("userName"));
        password.setCellValueFactory( new PropertyValueFactory<Customer,String>("password"));
        points.setCellValueFactory( new PropertyValueFactory<Customer,String>("points"));
        table.getColumns().addAll(userName,password,points);
        table.setItems(DB.getUsers());
        vb.setSpacing(5);
        vb.setPadding(new Insets(10,0,0,10));
        vb.getChildren().addAll(table,hb);
        TextField addUserName = new TextField();
        TextField addPassword = new TextField();
        Button addButton = new Button();
        addButton.setText("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {          
            @Override
            public void handle(ActionEvent event) {
                DB.addUser(new Customer(addUserName.getText(),addPassword.getText()));
                addUserName.clear();
                addPassword.clear();
            }
        });
        
        Button deleteButton = new Button();
        deleteButton.setText("Delete");
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                User u = table.getSelectionModel().getSelectedItem();
                DB.delete(u);
                System.out.println("Deleted");
            }
        });
        Button backButton = new Button();
        backButton.setText("Back");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                DB.write();
                changeState(new OwnerStartState(DB));
            }
        });
        hb.getChildren().addAll(addUserName,addPassword,addButton,deleteButton, backButton);
        hb.setSpacing(5);
        ((Group) scene.getRoot()).getChildren().addAll(vb);
        System.out.println("Reached here");
        
        return scene;
    }
}
