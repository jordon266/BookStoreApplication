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
public class OwnerBooksState extends UIState {
        private TableView<Book>table;
//    private final VBox vb = new VBox();
        private HBox hb ;
    private Group group;
    
    OwnerBooksState(BookStore DB){
        super(DB);
        table =  new TableView<Book>();
        
        hb = new HBox();
        group = new Group();
    }
    
    @Override
    public Scene buildUI(){
        BookStore DB = super.getDataBase();
        System.out.println("In ownerCustomersClass");
        Scene scene = new Scene(new Group());
        table.setEditable((true));
        final VBox vb = new VBox();
        final HBox hb = new HBox();
        TableColumn bookName = new TableColumn("Book Name");
        TableColumn bookPrice = new TableColumn("Book Price");
        bookName.setCellValueFactory( new PropertyValueFactory<Book,String>("name"));
        bookPrice.setCellValueFactory( new PropertyValueFactory<Book,Double>("price"));
        table.getColumns().addAll(bookName,bookPrice);
        table.setItems(DB.getBooks());
        vb.setSpacing(5);
        vb.setPadding(new Insets(10,0,0,10));
        vb.getChildren().addAll(table,hb);
        TextField addBookName = new TextField();
        TextField addBookPrice = new TextField();
        Button addButton = new Button();
        addButton.setText("Add");
        addButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                DB.addBook(new Book(addBookName.getText(),Double.valueOf(addBookPrice.getText())));
                addBookName.clear();
                addBookPrice.clear();
            }
        });
        
        Button deleteButton = new Button();
        hb.getChildren().addAll(addBookName,addBookPrice,addButton,deleteButton);
        deleteButton.setText("Delete");
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                Book b = table.getSelectionModel().getSelectedItem();
                DB.delete(b);
                System.out.println("Deleted");
            }
        });
        hb.getChildren().addAll();
        hb.setSpacing(5);
        ((Group) scene.getRoot()).getChildren().addAll(vb);
        System.out.println("Reached here");
        return scene;
    }
}
