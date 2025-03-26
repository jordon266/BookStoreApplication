/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore;

/**
 *
 * @author jjmurray
 */
public final class PointsSystem {
    static String getStatus(double balance){
        if(balance >= 1000.0){
            return "Gold";
        }
        return "Silver";
    }
}
