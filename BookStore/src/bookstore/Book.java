/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore;

/**
 *
 * @author jjmurray
 */
public class Book {
    private String id;
    private String name;
    private double price;
    Book(String ID, String bname, double bprice){
        id = ID;
        name = bname;
        price = bprice;
    }
    String getId(){
        return id;
    }
    String getName(){
        return name;
    }
    double getPrice(){
        return price;
    }
}
