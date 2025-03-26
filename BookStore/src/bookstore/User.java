/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore;
import java.util.ArrayList;
/**
 *
 * @author jjmurray
 */
public abstract class User {
    private String username;
    private String password;
    User(String uname, String pword){
        username = uname;
        password = pword;
    }
    abstract ArrayList<String> viewBooks(ArrayList<String> Books);
}
