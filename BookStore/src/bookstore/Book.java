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
    private CheckBox select;
    Book(String bname, double bprice){
        name = bname;
        price = bprice;
        CheckBox selected = new CheckBox();
    }
    String getName(){
        return name;
    }
    double getPrice(){
        return price;
    }
    public CheckBox getCheckBox(){
        return this.select;
    }
    public void setCheckBox(CheckBox checkBox){
        this.select = checkBox;
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
