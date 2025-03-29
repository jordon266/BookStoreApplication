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
    //Instnace variables
    private String userName;
    private String password;
    
    User(String uname, String pword){
        userName = uname;
        password = pword;
    }
    public String getUserName(){
        return userName;
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
        return (this.getUserName().equals(comparedUser.getUserName())
                && this.getPassword().equals(comparedUser.getPassword()));
    }
    public String toString(){
        return this.getUserName() + "," +this.getPassword();
    }
}
