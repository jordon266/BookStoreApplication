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
public class Owner extends User {
    Owner(){
        super("admin","admin");
    }
    public ArrayList<String>  viewBooks(ArrayList<String> Books){
        return Books; // todo Implementation
    }
}
