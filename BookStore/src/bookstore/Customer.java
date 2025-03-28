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
    Customer(String uname, String pword, String pointsBalance){
         super(uname,pword);
         System.out.println("In cusomter constructor");
         points = Integer.parseInt(pointsBalance.strip());
         System.out.println("Out of Customer constructor");
    }
    Customer(String uname, String pword){ // for temporary use
         super(uname,pword);
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
        return this.getUserName() + "," +this.getPassword() + "," + this.getPoints();
    }


}
