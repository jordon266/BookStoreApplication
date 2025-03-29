/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore;
import javafx.scene.control.CheckBox;
/**
 *
 * @author jjmurray
 */
public class Book {
    private String name;
    private double price;
    private CheckBox checkBox;
    
    Book(String bname, double bprice){
        name = bname;
        price = bprice;
        checkBox = new CheckBox();
    }
    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }
    public CheckBox getCheckBox(){
        return this.checkBox;
    }
    public void setCheckBox(CheckBox checkBox){
        this.checkBox = checkBox;
    }
    @Override 
    public boolean equals(Object comparedObject){
        if(this == comparedObject){
            return true;
        }
        if(!(comparedObject instanceof User)){
            return false;
        }
        Book comparedBook = (Book) comparedObject;
        return ( (this.getName().equals(comparedBook.getName())) && 
                (this.getPrice() == comparedBook.getPrice()));
    }
//
    @Override 
    public String toString(){
        return this.getName() + "," + this.getPrice();
    }
    
}
