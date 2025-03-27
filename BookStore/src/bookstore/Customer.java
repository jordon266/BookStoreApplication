/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore;

/**
 *
 * @author jjmurray
 */
public class Customer extends User {
    private int points;
    Customer(String uname, String pword){
         super(uname,pword);
         points = 0;
    }
    public int getPoints(){
        return points;
    }
        @Override 
    public String toString(){
        return this.getName() + "," +this.getPassword();
    }
}
