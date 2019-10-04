/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zorgcode;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author R. Uijttenboogaard
 */
public class PatientenCRUD {
    
    public PatientenCRUD(Stage stage, Scene scene){
        
        Button TerugKnop = new Button();
        TerugKnop.setText("Terug naar hoofdpagina");
        
        Pane root = new Pane();
        TerugKnop.setLayoutX(1000);
        TerugKnop.setLayoutY(600);
        root.getChildren().add(TerugKnop);

        
        Scene nieuwScene = new Scene(root, 1280, 720);
        stage.setScene(nieuwScene);
        
        TerugKnop.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(scene);
            }
        });
        
        
        
        
        
        
    
    }
    
}
