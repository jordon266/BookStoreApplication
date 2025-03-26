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
    public String getName(){
        return username;
    }
    public String getPassword(){
        return password;
    }
    @Override
    public boolean equals(Object comparedObject){
        if(this == comparedObject){
            return true;
        }
        if(!(comparedObject instanceof User)){
            return false;
        }
        User comparedUser = (User) comparedObject;
        return (this.getName().equals(comparedUser.getName())
                && this.getPassword().equals(comparedUser.getPassword()));
    }
    public String toString(){
        return this.getName() + "," +this.getPassword();
    }
}
