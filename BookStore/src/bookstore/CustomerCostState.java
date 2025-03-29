/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookstore;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
/**
 *
 * @author jjmurray
 */
public class CustomerCostState extends UIState {
    public CustomerCostState(BookStore DB){
        super(DB);
    }
    public void changeState(UIState state ){
        BookStore DB = super.getDataBase();
        DB.setState(state);
    }
    public Scene buildUI(){
        BookStore DB = super.getDataBase();
        Label totalCost = new Label();
        Label pointsAndStatus = new Label();
        Button logout = new Button();
        GridPane gp = new GridPane();
        logout.setText("Logout");
        totalCost.setText("Total Cost: " + DB.getTotalCost());
        pointsAndStatus.setText("Points: " + ((Customer) DB.getCurrentUser()).getPoints() + ", Status: " + PointsSystem.getStatus(((Customer) DB.getCurrentUser()).getPoints() ));
        totalCost.setMinWidth(200);
        totalCost.setMinHeight(60);
        pointsAndStatus.setMinWidth(200);
        pointsAndStatus.setMinHeight(60);
        logout.setMinWidth(200);
        logout.setMinHeight(60);
        gp.setPadding(new Insets(50,50,50,50));
        gp.setVgap(20);
        gp.add(totalCost, 2, 0);
        gp.add(pointsAndStatus, 2, 1);
        gp.add(logout, 2, 2);
        logout.setOnAction(new EventHandler<ActionEvent>() {            
            @Override
            public void handle(ActionEvent event) {
                DB.setCurrentUser(null);
                DB.write();
                changeState(new LoginState(DB));
            }
        });
        Scene scene = new  Scene(gp,300,300);
        return scene;
    } 
}
