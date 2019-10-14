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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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

        Pane root = new Pane();
        TerugKnop.setLayoutX(1000);
        TerugKnop.setLayoutY(600);

        BtnOpslaan.setLayoutX(650);
        BtnOpslaan.setLayoutY(200);

        BtnAanpassen.setLayoutX(500);
        BtnAanpassen.setLayoutY(200);

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

        root.getChildren().addAll(TerugKnop, list, TxtNaam, TxtOmschrijving, LblNaam, LblOmschrijving, BtnOpslaan, BtnAanpassen);

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
                try {
                    EntAanDoening deAandoening = list.getSelectionModel().getSelectedItem();
                    int AID = deAandoening.getId();

                    Pane sjaak = new Pane();
                    Stage stage = new Stage();
                    stage.setTitle("Aanpassen");
                    stage.setScene(new Scene(sjaak, 250, 225));
                    stage.show();

                    TextField TxtNaamAanpassen = new TextField();
                    TxtNaamAanpassen.setLayoutX(10);
                    TxtNaamAanpassen.setLayoutY(10);
                    TxtNaamAanpassen.setPrefSize(200, 35);

                    TextArea TxtOmschrijvingAanpassen = new TextArea();
                    TxtOmschrijvingAanpassen.setLayoutX(10);
                    TxtOmschrijvingAanpassen.setLayoutY(50);
                    TxtOmschrijvingAanpassen.setPrefSize(200, 100);
                    TxtOmschrijvingAanpassen.setWrapText(true);

                    Button AanpassingOpslaan = new Button("Wijziging opslaan");
                    AanpassingOpslaan.setLayoutX(10);
                    AanpassingOpslaan.setLayoutY(175);

                    TxtNaamAanpassen.setText(db.GeefAandoening(AID).Naam);
                    TxtOmschrijvingAanpassen.setText(db.GeefAandoening(AID).Omschrijving);

                    sjaak.getChildren().addAll(TxtNaamAanpassen, TxtOmschrijvingAanpassen, AanpassingOpslaan);

                    AanpassingOpslaan.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            String Naam = TxtNaamAanpassen.getText();
                            String Omschrijving = TxtOmschrijvingAanpassen.getText();
                            db.AanpassenAandoening(AID, Naam, Omschrijving);
                            list.setItems(db.VulLijstAandoening());
                            stage.close();
                        }
                    });

                } catch (Exception e) {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Selecteer een Item");
                    alert.setContentText("Je hebt niks uit de lijst geselecteerd!");
                    alert.showAndWait();
                }

            }
        }
        );

        if (db.connectDb()) {
            list.setItems(db.VulLijstAandoening());
        }
    }
}
