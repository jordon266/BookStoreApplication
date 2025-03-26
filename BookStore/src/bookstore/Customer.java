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
    private double points;
    Customer(String uname, String pword){
         super(uname,pword);
         points = 0;
    }
        @Override 
    public String toString(){
        return this.getName() + "," +this.getPassword();
    }
}
