/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore;

import javafx.collections.ObservableList;
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
     private ObservableList<Book> b_books;
    
    
    OwnerBooksState(BookStore DB){
        super(DB);
        table =  new TableView<Book>();
        b_books = super.getDataBase().getBooks();
        hb = new HBox();
        group = new Group();
    }
    
        public boolean addBook(Book book){
            if(!(b_books.contains(book))){
                b_books.add(book);
                return true;
        }
        return false;
        
    }
    public boolean delete(Book book){
        if(b_books.contains(book)){
            b_books.remove(book);
            return true;
        }
        return false;
    }

    @Override
    public Scene buildUI(){
        BookStore DB = super.getDataBase();
        System.out.println("In ownerBooksClass");
        Scene scene = new Scene(new Group());
        table.setEditable((true));
        final VBox vb = new VBox();
        final HBox hb = new HBox();
        
        //Creates a column for book name
        TableColumn bookName = new TableColumn("Book Name");
        bookName.setCellValueFactory( new PropertyValueFactory<Book,String>("name"));
        //Creates a column for book price
        TableColumn bookPrice = new TableColumn("Book Price ");
        bookPrice.setCellValueFactory( new PropertyValueFactory<Book,Double>("price"));
        
        table.getColumns().setAll(bookName,bookPrice);
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
                addBook(new Book(addBookName.getText(),Double.valueOf(addBookPrice.getText())));
                addBookName.clear();
                addBookPrice.clear();
            }
        });
        
        Button deleteButton = new Button();
        deleteButton.setText("Delete");
        deleteButton.setOnAction(new EventHandler<ActionEvent>() {           
            @Override
            public void handle(ActionEvent event) {
                Book b = table.getSelectionModel().getSelectedItem();
                delete(b);
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
        hb.getChildren().addAll(addBookName,addBookPrice,addButton,deleteButton, backButton);

        hb.setSpacing(5);
        ((Group) scene.getRoot()).getChildren().addAll(vb);
        System.out.println("Reached here");
        return scene;
    }
}
