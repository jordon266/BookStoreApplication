/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;


/**
 *
 * @author jjmurray
 */
abstract class UIState  {
    
    private BookStore db;
    UIState(BookStore DB){
        db = DB;
    }
    public BookStore getDataBase(){
        return db;
    }
    abstract Scene buildUI();
}
