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
    static String getStatus(int balance){
        if(balance >= 1000){
            return "G";
        }
        return "S";
    }
}
