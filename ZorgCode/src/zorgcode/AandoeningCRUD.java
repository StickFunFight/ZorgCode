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
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
        
        TextField TxtNaam = new TextField();
        TextArea TxtOmschrijving = new TextArea();
        Button TerugKnop = new Button();
        TerugKnop.setText("Terug naar hoofdpagina");
        
        Pane root = new Pane();
        TerugKnop.setLayoutX(1000);
        TerugKnop.setLayoutY(600);
        
        ListView<EntAanDoening> list = new ListView<EntAanDoening>();
    
        list.setLayoutX(10);
        list.setLayoutY(10);
        
        TxtNaam.setLayoutX(500);
        TxtNaam.setLayoutY(10);
        TxtNaam.setPrefSize(200, 35);
        
        TxtOmschrijving.setLayoutX(500);
        TxtOmschrijving.setLayoutY(50);
        TxtOmschrijving.setPrefSize(200, 100);
        TxtOmschrijving.setWrapText(true);

        root.getChildren().addAll(TerugKnop,list, TxtNaam, TxtOmschrijving);
        
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
            EntAanDoening deAandoening = list.getSelectionModel().getSelectedItem();
            int AID = deAandoening.getId();

            TxtNaam.setText(db.GeefAandoening(AID).Naam);
             TxtOmschrijving.setText(db.GeefAandoening(AID).Omschrijving);
            
            }
        });

        if(db.connectDb()){
            list.setItems(db.VulLijstAandoening()); 
        }
    }  
}
