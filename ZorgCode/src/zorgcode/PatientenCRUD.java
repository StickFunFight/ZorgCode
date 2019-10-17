/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zorgcode;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author R. Uijttenboogaard
 */
public class PatientenCRUD {

    Patient db = new Patient();

    public PatientenCRUD(Stage stage, Scene scene) {

        Button TerugKnop = new Button();
        TextField TxtVoorNaam = new TextField();
        TextField TxtAchternaam = new TextField();
        TextField TxtLengte = new TextField();
        TerugKnop.setText("Terug naar hoofdpagina");

        Pane root = new Pane();
        TerugKnop.setLayoutX(1000);
        TerugKnop.setLayoutY(600);

        ListView<EntPatient> list = new ListView<EntPatient>();

        list.setLayoutX(10);
        list.setLayoutY(10);

        root.getChildren().addAll(TerugKnop, list);

        list.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                EntPatient Patient = list.getSelectionModel().getSelectedItem();
                int AID = Patient.getId();
            }
        });

        Scene nieuwScene = new Scene(root, 1280, 720);
        stage.setScene(nieuwScene);

        TerugKnop.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                stage.setScene(scene);
            }
        });

        if (db.connectDb()) {
            list.setItems(db.VulLijstPatienten());
        }
    }

}
