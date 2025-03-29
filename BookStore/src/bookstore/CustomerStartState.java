/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 *
 * @author jjmurray
 */
public class CustomerStartState extends UIState {
    private TableView<Book>table;
    public CustomerStartState(BookStore DB){
        super(DB);
        table = new TableView<>();
    }
    public String greetCustomer(){  
        return ("Welcome " + super.getDataBase().getCurrentUser().getUserName() + "." + " You have " + ((Customer)super.getDataBase().getCurrentUser()).getPoints()
                 + " points. Your status is " + PointsSystem.getStatus(((Customer)super.getDataBase().getCurrentUser()).getPoints()) );
    }
    public void buyAndCalTotalCost(){
        BookStore DB = super.getDataBase();
        double cost = 0;
        for(Book book: DB.getBooks()){
            if(book.getCheckBox().isSelected()){
               cost += book.getPrice();
            }
        }
        DB.setTotalCost(cost);
        
        ((Customer) DB.getCurrentUser()).adjustPoints(((int)cost*10));
    } 
    public void redeemAndCalTotalCost(){
        BookStore DB = super.getDataBase();
        double cost = 0;
        for(Book book: DB.getBooks()){
            if(book.getCheckBox().isSelected()){
               cost += book.getPrice();
            }
        }
        int numOfDollarsOff = ((int) (((Customer) DB.getCurrentUser()).getPoints())/100);
        int numOfPoints = ((Customer) DB.getCurrentUser()).getPoints();
        DB.setTotalCost(cost - numOfDollarsOff );
        
        ((Customer) DB.getCurrentUser()).adjustPoints(-(numOfDollarsOff*100));
    } 
    public void changeState(UIState state ){
        BookStore DB = super.getDataBase();
        DB.setState(state);
    }
    public Scene buildUI(){
        BookStore DB = super.getDataBase();
        System.out.println("In ownerCustomersClass");
        Scene scene = new Scene(new Group(),550,800);      
        table.setEditable((true));
        final VBox vb = new VBox();
        final HBox tb = new HBox();
        final HBox hb = new HBox();
        
        //Creating a column for book name
        TableColumn bookName = new TableColumn("Book Name");
        bookName.setCellValueFactory( new PropertyValueFactory<Book,String>("name"));
        
        //creating a column for book price
        TableColumn bookPrice = new TableColumn("Book Price");
        bookPrice.setCellValueFactory( new PropertyValueFactory<Book,Double>("price"));
        
        //creating a column for the check books
        TableColumn select = new TableColumn("Select");
        select.setCellValueFactory( new PropertyValueFactory<Book,CheckBox>("checkBox"));
        
        // Adding the columns to the table
        table.getColumns().addAll(bookName,bookPrice,select);
        
        //restricting the resizing
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        //Forcing the size for of 'select' column
        select.setPrefWidth(90);
        select.setMinWidth(90);
        select.setMaxWidth(90);

        
        table.setItems(DB.getBooks());
        table.setMinWidth(450);
        Label greeting = new Label();
        greeting.setText(greetCustomer());
        tb.getChildren().addAll(greeting);
        vb.setSpacing(5);
        vb.setPadding(new Insets(10,0,0,10));
        vb.getChildren().addAll(tb,table,hb);
        
        //Buy button
        Button buyButton = new Button();
        buyButton.setText("Buy");
        buyButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                buyAndCalTotalCost();
                changeState(new CustomerCostState(DB));

            }
        });
        
        Button redeemAndBuyButton = new Button();
        redeemAndBuyButton.setText("Redeem and Buy");
        redeemAndBuyButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
//               ((Customer) DB.getCurrentUser()).adjustPoints()
                redeemAndCalTotalCost();
                changeState(new CustomerCostState(DB));
            }
        });
        Button logout = new Button();
        logout.setText("Logout");
        logout.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                
                DB.setCurrentUser(null);
                DB.write();
                changeState(new LoginState(DB));
                
            }
        });
        hb.getChildren().addAll(buyButton,redeemAndBuyButton,logout);
        hb.getChildren().addAll();
        hb.setSpacing(5);
        ((Group) scene.getRoot()).getChildren().addAll(vb);
        System.out.println("Reached here");
        return scene;
    }
}
