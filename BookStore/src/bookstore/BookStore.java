/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package bookstore;

import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.nio.file.Path;
import java.io.FileWriter;
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
    private ArrayList<User> users = null;
    private ArrayList<Book> books= null;
    private PointsSystem pointsystem;
    private User currentUser = null;
    private User owner;
    
    public BookStore(){
        owner = new Owner();
        users = new ArrayList<>();
        books = new ArrayList<>();
        
    }
    // writing to new file customers
    public  void write(){
        try {
            File c_file = new File("customers.txt");
            File b_file = new File("books.txt");
            FileWriter writer;
            if (c_file.createNewFile()){
                writer = new FileWriter(c_file.getAbsolutePath());
                
            } else {
                writer = new FileWriter(c_file.getAbsolutePath(),true);
            }
            for(User user: users){
                writer.write(user.toString());
                writer.write("\n");
            }
            writer.close();
            if (b_file.createNewFile()){
                writer = new FileWriter(b_file.getAbsolutePath());
            } else {
                writer = new FileWriter(b_file.getAbsolutePath(),true);
            }
            for(Book book: books){
                writer.write(book.toString());
                writer.write("\n");
            }
            writer.close();
            System.out.println("Reached here and everything is written");
            
        } catch(IOException e) {
            System.out.println("There is a problem writing to the file =========================");
        }
    }
    
    private Customer loadCustomer(String line){
        String [] params =  line.split(",");
        Customer temp = new Customer(params[0],params[1]);
        return temp;
    }
    private Book loadBook(String line){
        String [] params =  line.split(",");
        Book temp = new Book(params[1],Double.valueOf( params[2]));
        return temp;
    }
    // reads files customers.txt Books.txt
    public  void read() {
        try (Scanner scanner = new Scanner(Path.of("customers.txt")) ) {
        // Write the code here
            while(scanner.hasNextLine()){
                this.addUser(loadCustomer(scanner.nextLine()));
                
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
        try (Scanner scanner = new Scanner(Path.of("books.txt")) ) {
        // Write the code here
            while(scanner.hasNextLine()){
                this.addBook(loadBook(scanner.nextLine()));                
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
 
    public ArrayList<Book> getBooks(){
        return books;
    }
    public boolean addBook(Book book){
        if(!(books.contains(book))){
            this.books.add(book);
            return true;
        }
        return false;
        
    }
    public boolean delete(Book book){
        if(books.contains(book)){
            books.remove(book);
            return true;
        }
        return false;
    }
    public ArrayList<User> getUsers(){
        return users;
    }
    public boolean addUser(User user){
        if(!(users.contains(user))){
            this.users.add(user);
            return true;
        }
        return false;
    }
    public boolean delete(User d_user){
        if(users.contains(d_user)){
            users.remove(d_user);
            return true;
        }
        return false;
    }
    

    public boolean authenticate(User l_user){
        return users.contains(l_user);
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
    
    public void printUsers(){
        for(User user: users){
            System.out.println(user);
        }
    }
    public void printBooks(){
        for(Book book: books){
            System.out.println(book);
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //launch(args);
        BookStore b = new BookStore();
        ArrayList<User> customers = b.getUsers();
        customers.add(new Customer("emilychen", "ilovebooks123"));
        customers.add(new Customer("davidlee1980", "password123"));
        customers.add(new Customer("sophiap123", "sophiap1234"));
        customers.add(new Customer("jacksonb", "jackson123"));
        customers.add(new Customer("avamoreno", "avam123"));
        customers.add(new Customer("liamk90", "liamk1234"));
        customers.add(new Customer("charlotted", "charlotte99"));
        customers.add(new Customer("ethanhall", "ethan12345"));
        customers.add(new Customer("abitaylor", "abigailt123"));
        customers.add(new Customer("loganb22", "loganbrooks1"));
        System.out.println(customers);
        ArrayList<Book> books = b.getBooks();
        books.add(new Book( "To Kill a Mockingbird", 15.99));
        books.add(new Book("1984", 12.99));
        books.add(new Book( "Pride and Prejudice", 10.99));
        books.add(new Book( "The Great Gatsby", 14.99));
        books.add(new Book("The Catcher in the Rye", 13.99));
        books.add(new Book("The Hunger Games", 16.99));
        books.add(new Book("The Handmaid's Tale", 11.99));
        books.add(new Book( "The Picture of Dorian Gray", 9.99));
        books.add(new Book( "War and Peace", 17.99));
        books.add(new Book( "Moby-Dick", 18.99));   

        b.write();
        b.printUsers();
        b.printBooks();        
    }
    
}
