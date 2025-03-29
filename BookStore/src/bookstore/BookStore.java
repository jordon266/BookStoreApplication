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
import javafx.scene.Scene;
import javafx.stage.Stage;
/**
 *
 * @author jjmurray
 */
public class BookStore extends Application {
    private ObservableList<User> users = null;  
    private ObservableList<Book> books = null;
    UIState currentState;
    Stage primaryStage;
    private double totalCost;
    private User currentUser = null;
    private final User owner;
    public BookStore(){
        owner = new Owner();
        users = FXCollections.observableArrayList();
        books = FXCollections.observableArrayList();
        totalCost = 0;
        this.read();   
        currentState = new LoginState(this); 
    }
    public User getOwner(){
        return owner;
    }   
    public User getCurrentUser(){
        return currentUser;
    }
    public double getTotalCost(){
        return totalCost;
    }
    public void setCurrentUser(User user){
        currentUser = user;
    }
    public void setTotalCost(double tCost){
        if(tCost>= 0){
            totalCost = tCost;
        } else {
            totalCost = 0;
        }
    }
    // writing to new file customers
    public  void write(){
        try {
            File c_file = new File("customers.txt");
            File b_file = new File("books.txt");
            FileWriter writer = new FileWriter(c_file.getAbsolutePath());;             
            for(User user: users){
                writer.write(user.toString());
                writer.write("\n");
            }
            writer.close();
            writer = new FileWriter(b_file.getAbsolutePath());
            for(Book book: books){
                writer.write(book.toString());
                writer.write("\n");
            }
            writer.close();
        } catch(IOException e) {
            System.out.println("There is a problem writing to the file =========================");
        }
    }
    
    private Customer loadCustomer(String line){
        System.out.println(line);
        String [] params =  line.split(",");
        Customer temp = new Customer(params[0],params[1],(params[2]));
        return temp;
    }
    private Book loadBook(String line){
        String [] params =  line.split(",");
        Book temp = new Book(params[0],Double.parseDouble(params[1]));
        return temp;
    }   
    // reads files customers.txt Books.txt
    private  void read() {
        //Reads over the customers
        try (Scanner scanner = new Scanner(Path.of("customers.txt")) ) {
            while(scanner.hasNextLine()){
                getUsers().add(loadCustomer(scanner.nextLine()));
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
        
        //Reads over the books
        try (Scanner scanner = new Scanner(Path.of("books.txt")) ) {
            while(scanner.hasNextLine()){
                  getBooks().add(loadBook(scanner.nextLine()));
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
        }
    }
    
    public ObservableList<Book> getBooks(){
        return books;
    }
    
    public ObservableList<User> getUsers(){
        return users;
    }
    //gets user
    public User getUser(User l_user){
        for(User user: users){
            if(user.equals(l_user)){
                return user;
            }
        }
        return null;
    }
    public void setState(UIState state){
        this.currentState = state;
        this.primaryStage.close();
        this.primaryStage = new Stage();
        this.primaryStage.setTitle("BookStore App");
        Scene scene = currentState.buildUI();
        primaryStage.setScene(scene);
        primaryStage.show();
    }  
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setScene(currentState.buildUI());
        this.primaryStage.sizeToScene();
        this.primaryStage.show();
    }
    @Override
    public void stop(){
        this.write(); 
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }  
}
