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
    Customer(String uname, String pword, int pointsBalance){
         super(uname,pword);
         points = pointsBalance;
    }
    public int getPoints(){
        return points;
    }
    public boolean adjustPoints(int amount){
        if(points + amount >= 0){
            points += amount;
            return true;
        }
        return false;
    }
        @Override 
    public String toString(){
        return this.getName() + "," +this.getPassword() + "," + this.getPoints();
    }
}
