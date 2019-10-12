/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zorgcode;

import java.time.format.DateTimeFormatter;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
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

    public AandoeningCRUD(Stage stage, Scene scene) {

        TextField TxtNaam = new TextField();
        TextArea TxtOmschrijving = new TextArea();
        Label LblNaam = new Label("Naam: ");
        Label LblOmschrijving = new Label("Omschrijving: ");

        Button TerugKnop = new Button("Terug naar hoofdpagina");
        Button BtnOpslaan = new Button("Opslaan");
        Button BtnAanpassen = new Button("Aanpassen");
        Button BtnVerwijder = new Button("Verwijder");

        Pane root = new Pane();
        TerugKnop.setLayoutX(1000);
        TerugKnop.setLayoutY(600);

        BtnOpslaan.setLayoutX(700);
        BtnOpslaan.setLayoutY(200);

        BtnAanpassen.setLayoutX(550);
        BtnAanpassen.setLayoutY(200);

        BtnVerwijder.setLayoutX(400);
        BtnVerwijder.setLayoutY(200);

        ListView<EntAanDoening> list = new ListView<EntAanDoening>();

        list.setLayoutX(10);
        list.setLayoutY(10);

        LblNaam.setLayoutX(450);
        LblNaam.setLayoutY(15);

        TxtNaam.setLayoutX(500);
        TxtNaam.setLayoutY(10);
        TxtNaam.setPrefSize(200, 35);

        LblOmschrijving.setLayoutX(400);
        LblOmschrijving.setLayoutY(55);

        TxtOmschrijving.setLayoutX(500);
        TxtOmschrijving.setLayoutY(50);
        TxtOmschrijving.setPrefSize(200, 100);
        TxtOmschrijving.setWrapText(true);

        root.getChildren().addAll(TerugKnop, list, TxtNaam, TxtOmschrijving, LblNaam, LblOmschrijving, BtnOpslaan, BtnAanpassen, BtnVerwijder);

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

        BtnOpslaan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String Naam = TxtNaam.getText();
                String Omschrijving = TxtOmschrijving.getText();
                db.NieuweAandoening(Naam, Omschrijving);
                list.setItems(db.VulLijstAandoening());
                TxtNaam.clear();
                TxtOmschrijving.clear();

            }
        });

        BtnAanpassen.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String Naam = TxtNaam.getText();
                String Omschrijving = TxtOmschrijving.getText();
                EntAanDoening deAandoening = list.getSelectionModel().getSelectedItem();
                int AID = deAandoening.getId();
                db.AanpassenAandoening(AID, Naam, Omschrijving);
                list.setItems(db.VulLijstAandoening());
            }
        });

        BtnVerwijder.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                EntAanDoening deAandoening = list.getSelectionModel().getSelectedItem();
                int AID = deAandoening.getId();
                db.DeletePersoon(AID);
                list.setItems(db.VulLijstAandoening());
            }
        });

        if (db.connectDb()) {
            list.setItems(db.VulLijstAandoening());
        }
    }
}
