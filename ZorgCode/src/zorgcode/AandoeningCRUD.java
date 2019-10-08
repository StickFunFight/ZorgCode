/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zorgcode;

import java.util.ArrayList;
import java.util.prefs.Preferences;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author R. Uijttenboogaard
 */
public class AandoeningCRUD {
    
    Aandoening db = new Aandoening();
    
    public AandoeningCRUD(Stage stage, Scene scene){
        
        Button TerugKnop = new Button();
        TerugKnop.setText("Terug naar hoofdpagina");
        
        Pane root = new Pane();
        TerugKnop.setLayoutX(1000);
        TerugKnop.setLayoutY(600);
        
        ListView<String> list = new ListView<String>();
    
        list.setLayoutX(10);
        list.setLayoutY(10);

        root.getChildren().addAll(TerugKnop,list);
        
        Scene nieuwScene = new Scene(root, 1280, 720);
        stage.setScene(nieuwScene);

        TerugKnop.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(scene);
                
            }
        });
        
        list.setOnMouseClicked(new EventHandler<MouseEvent>() {

        @Override
        public void handle(MouseEvent event) {
            System.out.println(list.getSelectionModel().getSelectedItem());
            }
        });

        if(db.connectDb()){
            db.GeefAandoeningNaam();
            System.out.println(db.GeefAandoeningNaam());
            ArrayList<String> AandoeningNaam = db.GeefAandoeningNaam();
            ObservableList<String> Namen = FXCollections.observableArrayList(AandoeningNaam);
            list.setItems(Namen); 
        }
    }  
}
