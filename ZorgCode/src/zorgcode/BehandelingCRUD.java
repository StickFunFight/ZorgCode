/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zorgcode;

import static java.sql.Types.NULL;
import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BehandelingCRUD {

    Behandeling db = new Behandeling();
    Patient Pdb = new Patient();

    public BehandelingCRUD(Stage stage, Scene scene) {
        Button TerugKnop = new Button("Terug naar hoofdpagina");

        Button BtnOpslaan = new Button("Opslaan");
        TextField TxtNaam = new TextField();
        Label LblNaam = new Label("Naam: ");
        TextArea TxtOmschrijving = new TextArea();
        Label LblOmschrijving = new Label("Omschrijving: ");
        TextField TxtMedicijn = new TextField();
        Label LblMedicijn = new Label("Medicijn: ");
        TextField TxtHoeveelheid = new TextField();
        Label LblHoeveelheid = new Label("Hoeveelheid: ");
        DatePicker DpBeginDatum = new DatePicker();
        Label LblbeginDatum = new Label("BeginDatum: ");
        DatePicker DpEindDatum = new DatePicker();
        Label LblEindDatum = new Label("EindDatum: ");
        ComboBox<EntPatient> CbxPatient = new ComboBox<EntPatient>();
        Label LblPatient = new Label("Patient: ");

        Pane root = new Pane();
        root.setStyle("-fx-background-color: #8bb3e8;");
        TerugKnop.setLayoutX(1000);
        TerugKnop.setLayoutY(600);

        ListView<EntBehandeling> list = new ListView<EntBehandeling>();

        list.setLayoutX(10);
        list.setLayoutY(10);

        LblNaam.setLayoutX(300);
        LblNaam.setLayoutY(10);

        TxtNaam.setLayoutX(450);
        TxtNaam.setLayoutY(10);

        LblOmschrijving.setLayoutX(300);
        LblOmschrijving.setLayoutY(50);

        TxtOmschrijving.setLayoutX(450);
        TxtOmschrijving.setLayoutY(50);
        TxtOmschrijving.setPrefSize(200, 100);
        TxtOmschrijving.setWrapText(true);

        LblMedicijn.setLayoutX(300);
        LblMedicijn.setLayoutY(170);

        TxtMedicijn.setLayoutX(450);
        TxtMedicijn.setLayoutY(170);

        LblHoeveelheid.setLayoutX(300);
        LblHoeveelheid.setLayoutY(210);

        TxtHoeveelheid.setLayoutX(450);
        TxtHoeveelheid.setLayoutY(210);

        LblbeginDatum.setLayoutX(300);
        LblbeginDatum.setLayoutY(250);

        DpBeginDatum.setLayoutX(450);
        DpBeginDatum.setLayoutY(250);

        LblEindDatum.setLayoutX(300);
        LblEindDatum.setLayoutY(290);

        DpEindDatum.setLayoutX(450);
        DpEindDatum.setLayoutY(290);

        LblPatient.setLayoutX(300);
        LblPatient.setLayoutY(330);

        CbxPatient.setLayoutX(450);
        CbxPatient.setLayoutY(330);

        BtnOpslaan.setLayoutX(450);
        BtnOpslaan.setLayoutY(370);

        root.getChildren().addAll(TerugKnop, list, TxtNaam, LblNaam, TxtOmschrijving, LblOmschrijving, LblMedicijn, TxtMedicijn, LblHoeveelheid, TxtHoeveelheid, LblbeginDatum, DpBeginDatum, LblEindDatum, DpEindDatum, LblPatient, CbxPatient, BtnOpslaan);

        Scene nieuwScene = new Scene(root, 1280, 720);
        stage.setScene(nieuwScene);

        TerugKnop.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.setScene(scene);
            }
        });

        BtnOpslaan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int Hoeveelheid;
                String Naam = TxtNaam.getText();
                String Omschrijving = TxtOmschrijving.getText();
                String Medicijn = TxtMedicijn.getText();
                
                LocalDate bDatum = DpBeginDatum.getValue();
                String BeginDatum = bDatum.toString();
                LocalDate eDatum = DpEindDatum.getValue();
                String EindDatum = eDatum.toString();
               if (TxtHoeveelheid.getText().isEmpty()){
                    Hoeveelheid = NULL;
                }else{
                   Hoeveelheid = Integer.parseInt(TxtHoeveelheid.getText());
               }
                EntPatient Patient = CbxPatient.getSelectionModel().getSelectedItem();
                int PID = Patient.getId();
                db.NieuweAandoening(Naam, Omschrijving, Medicijn, Hoeveelheid, BeginDatum, EindDatum, PID);
            }
        });

        if (db.connectDb()) {
            list.setItems(db.VulLijstBehandeling());
        }
        if (Pdb.connectDb()) {
            CbxPatient.getItems().addAll((Pdb.VulLijstPatienten()));
        }
    }
}
