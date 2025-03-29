/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
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
    private ObservableList<User> u_users;  

    OwnerCustomersState(BookStore DB){
        super(DB);
        table =  new TableView<User>();
        u_users = super.getDataBase().getUsers();
        hb = new HBox();
        group = new Group();
    }
    
    public boolean addUser(User user){
        if(!(u_users.contains(user))){
            this.u_users.add(user);
            return true;
        }
        return false;
    }
    public boolean delete(User d_user){
        if(u_users.contains(d_user)){
            u_users.remove(d_user);
            return true;
        }
        return false;
    }


    
    
    @Override
    public Scene buildUI(){
        BookStore DB = super.getDataBase();
        System.out.println("In ownerCustomersClass");
        Scene scene = new Scene(new Group(),550,800);
        table.setEditable((true));
        final VBox vb = new VBox();
        final HBox hb = new HBox();
        
        //Creating a column for username
        TableColumn userName = new TableColumn("Username");
        userName.setCellValueFactory( new PropertyValueFactory<Customer,String>("userName"));
        
        //crerating a column for password
        TableColumn password = new TableColumn("Password");
        password.setCellValueFactory( new PropertyValueFactory<Customer,String>("password"));
        
        //creating a column for points
        TableColumn points = new TableColumn("Points");
        points.setCellValueFactory( new PropertyValueFactory<Customer,String>("points"));
        
        //adding the columns onto the table
        table.getColumns().addAll(userName,password,points);
        
        //restricting the resizing
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        table.setItems(DB.getUsers());
        vb.setSpacing(5);
        vb.setPadding(new Insets(10,0,0,10));
        vb.getChildren().addAll(table,hb);
        
        //text field for username
        TextField addUserName = new TextField();
        addUserName.setPromptText("Username");
        
        //text field for password
        TextField addPassword = new TextField();
        addPassword.setPromptText("Password");
        
        Button addButton = new Button();
        addButton.setText("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {          
            @Override
            public void handle(ActionEvent event) {
                addUser(new Customer(addUserName.getText(),addPassword.getText()));
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
                delete(u);
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
