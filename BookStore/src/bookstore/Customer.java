/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore;

/**
 *
 * @author jjmurray
 */
import java.util.ArrayList;
public class Customer extends User {
    private double points;
    Customer(String uname, String pword){
         super(uname,pword);
         points = 0;
    }
    public ArrayList<String> viewBooks(ArrayList<String> Books){
        return Books; // to do implementation 
    }
}
