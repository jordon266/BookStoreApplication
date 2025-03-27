/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package bookstore;
import javafx.collections.FXCollections;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;
import java.nio.file.Path;
import java.io.FileWriter;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


//import java.util.ArrayList;
/**
 *
 * @author jjmurray
 */
public class BookStore extends Application {
    private ObservableList<User> users = null;  
    private ObservableList<Book> books = null;
    UIState currentState;
    Stage primaryStage = new Stage();

//    private ArrayList<User> users = null;
//    private PointsSystem pointsystem;
    private User currentUser = null;
    private User owner;
    
    public BookStore(){
        owner = new Owner();
        users = FXCollections.observableArrayList();
        books = FXCollections.observableArrayList();
        this.read();
        currentState = new LoginState(this);

        
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
        Customer temp = new Customer(params[0],params[1],Integer.valueOf(params[2]));
        return temp;
    }
    private Book loadBook(String line){
        String [] params =  line.split(",");
        Book temp = new Book(params[0],Double.valueOf( params[1]));
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
 
    public ObservableList<Book> getBooks(){
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
    public User getUser(User l_user){
        for(User user: users){
            if(user.equals(l_user)){
                return user;
            }
        }
        return null;
    }
    public ObservableList<User> getUsers(){
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
    
    public String greetCustomer(){  
        return ("Welcome " + this.currentUser.getUserName() + "." + " You have " + ((Customer)this.currentUser).getPoints()
                 + " points. Your status is " + PointsSystem.getStatus(((Customer)this.currentUser).getPoints()) );
    }

//    public boolean authenticate(User l_user){
//        if(l_user.getName().equals("admin") && l_user.getPassword().equals("admin")){
//            currentUser = owner;
//            return true;
//        }
//        if(users.contains(l_user)){
//            currentUser = l_user;
//            return true;
//        }
//        return false    ;
//    }
    

    public void setState(UIState state){
        this.currentState = state;
        Scene scene = currentState.buildUI();
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    @Override
    public void start(Stage primaryStage) {
       
        primaryStage = this.primaryStage;
        primaryStage.setTitle("BookStore App");
//        System.out.println("is it crasing here");

        primaryStage.setScene(currentState.buildUI());
        System.out.println("Maybe its crashing here");
        primaryStage.show();
        
    }
    @Override
    public void stop(){
        //this.write();
        System.out.println("Saving occurs here");
        
    }
    public User getOwner(){
        return owner;
    }
    
    public User getCurrentUser(){
        return currentUser;
    }
    public void setCurrentUser(User user){
        currentUser = user;
    }
    // test functions
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
                launch(args);

// Create a BookStore object
                BookStore bookstore = new BookStore();

                // Create 10 Customer objects
                Customer customer1 = new Customer("johnDoe", "password123", 100);
                Customer customer2 = new Customer("janeDoe", "password456", 200);
                Customer customer3 = new Customer("bobSmith", "password789", 300);
                Customer customer4 = new Customer("aliceJohnson", "password101", 400);
                Customer customer5 = new Customer("mikeWilliams", "password102", 500);
                Customer customer6 = new Customer("emilyDavis", "password103", 600);
                Customer customer7 = new Customer("davidMiller", "password104", 700);
                Customer customer8 = new Customer("sarahTaylor", "password105", 800);
                Customer customer9 = new Customer("kevinWhite", "password106", 900);
                Customer customer10 = new Customer("oliviaMartin", "password107", 1000);

                // Add the customers to the bookstore
                bookstore.addUser(customer1);
                bookstore.addUser(customer2);
                bookstore.addUser(customer3);
                bookstore.addUser(customer4);
                bookstore.addUser(customer5);
                bookstore.addUser(customer6);
                bookstore.addUser(customer7);
                bookstore.addUser(customer8);
                bookstore.addUser(customer9);
                bookstore.addUser(customer10);
//                bookstore.write();
//        b.printUsers();
//        b.printBooks();
//        User temp = new Customer("ethanhall","ethan12345");
////        boolean logged_in = b.authenticate(temp);
//        System.out.println("is she logged in ?" + logged_in);
//        System.out.println("The current user is " + b.getCurrentUser());
        
    }
    
}
