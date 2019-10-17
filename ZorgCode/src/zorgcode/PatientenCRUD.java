/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zorgcode;

import java.time.LocalDate;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author R. Uijttenboogaard
 */
public class PatientenCRUD {

    Patient db = new Patient();
    Aandoening ad = new Aandoening();

    public PatientenCRUD(Stage stage, Scene scene) {

        Button BtnOpslaan = new Button("Opslaan");
        Button BtnWijzig = new Button("Wijzig");
        Button TerugKnop = new Button("Terug naar hoofdpagina");
        TextField TxtVoorNaam = new TextField();
        Label LblVoornaam = new Label("Voornaam: ");
        TextField TxtAchternaam = new TextField();
        Label LblAchternaam = new Label("Achternaam: ");
        DatePicker DpGeboorteDatum = new DatePicker();
        Label LblGeboorteDatum = new Label("GeboorteDatum: ");
        TextField TxtLengte = new TextField();
        Label LblLengte = new Label("Lengte: ");
        TextField TxtGewicht = new TextField();
        Label LblGewicht = new Label("Gewicht: ");
        TextField TxtTelefoonNummer = new TextField();
        Label LblTelefoonNummer = new Label("Telefoonnummer: ");
        TextField TxtNoodNummer = new TextField();
        Label LblNoodNummer = new Label("Noodnummer: ");
        TextField TxtExtraNoodNummer = new TextField();
        Label LblExtraNoodNummer = new Label("Extra Noodnummer: ");
        TextArea TxtAllergieen = new TextArea();
        Label LblAllergieen = new Label("Allergieën: ");

        Pane root = new Pane();
        root.setStyle("-fx-background-color: #8bb3e8;");
        TerugKnop.setLayoutX(1000);
        TerugKnop.setLayoutY(600);

        BtnOpslaan.setLayoutX(450);
        BtnOpslaan.setLayoutY(450);

        BtnWijzig.setLayoutX(10);
        BtnWijzig.setLayoutY(450);

        ListView<EntPatient> list = new ListView<EntPatient>();

        list.setLayoutX(10);
        list.setLayoutY(10);

        LblVoornaam.setLayoutX(300);
        LblVoornaam.setLayoutY(10);

        TxtVoorNaam.setLayoutX(450);
        TxtVoorNaam.setLayoutY(10);

        LblAchternaam.setLayoutX(300);
        LblAchternaam.setLayoutY(50);

        TxtAchternaam.setLayoutX(450);
        TxtAchternaam.setLayoutY(50);

        LblGeboorteDatum.setLayoutX(300);
        LblGeboorteDatum.setLayoutY(90);

        DpGeboorteDatum.setLayoutX(450);
        DpGeboorteDatum.setLayoutY(90);

        LblLengte.setLayoutX(300);
        LblLengte.setLayoutY(130);

        TxtLengte.setLayoutX(450);
        TxtLengte.setLayoutY(130);

        LblGewicht.setLayoutX(300);
        LblGewicht.setLayoutY(170);

        TxtGewicht.setLayoutX(450);
        TxtGewicht.setLayoutY(170);

        LblTelefoonNummer.setLayoutX(300);
        LblTelefoonNummer.setLayoutY(210);

        TxtTelefoonNummer.setLayoutX(450);
        TxtTelefoonNummer.setLayoutY(210);

        LblNoodNummer.setLayoutX(300);
        LblNoodNummer.setLayoutY(250);

        TxtNoodNummer.setLayoutX(450);
        TxtNoodNummer.setLayoutY(250);

        LblExtraNoodNummer.setLayoutX(300);
        LblExtraNoodNummer.setLayoutY(290);

        TxtExtraNoodNummer.setLayoutX(450);
        TxtExtraNoodNummer.setLayoutY(290);

        LblAllergieen.setLayoutX(300);
        LblAllergieen.setLayoutY(330);

        TxtAllergieen.setLayoutX(450);
        TxtAllergieen.setLayoutY(330);
        TxtAllergieen.setPrefSize(200, 100);
        TxtAllergieen.setWrapText(true);

        Button BtnAandoening = new Button("Aandoening Toevoegen");
        BtnAandoening.setLayoutX(60);
        BtnAandoening.setLayoutY(450);

        root.getChildren().addAll(BtnAandoening, TerugKnop, list, TxtVoorNaam, LblVoornaam, TxtAchternaam, LblAchternaam, DpGeboorteDatum, LblGeboorteDatum, TxtLengte, LblLengte, TxtGewicht, LblGewicht, LblTelefoonNummer, TxtTelefoonNummer, LblNoodNummer, TxtNoodNummer, LblExtraNoodNummer, TxtExtraNoodNummer, LblAllergieen, TxtAllergieen, BtnOpslaan, BtnWijzig);

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

        BtnOpslaan.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                try {
                    String Voornaam = TxtVoorNaam.getText();
                    String Achternaam = TxtAchternaam.getText();
                    LocalDate LocalDate = DpGeboorteDatum.getValue();
                    String Datum = LocalDate.toString();
                    Double Lengte = Double.parseDouble(TxtLengte.getText());
                    Double Gewicht = Double.parseDouble(TxtGewicht.getText());
                    String TelefoonNummer = TxtTelefoonNummer.getText();
                    String NoodNummer = TxtNoodNummer.getText();
                    String ExtraNoodnummer = TxtExtraNoodNummer.getText();
                    String Allergieen = TxtAllergieen.getText();

                    db.NieuwePatient(Voornaam, Achternaam, Datum, Lengte, Gewicht, TelefoonNummer, NoodNummer, ExtraNoodnummer, Allergieen);
                    list.setItems(db.VulLijstPatienten());

                    TxtVoorNaam.clear();
                    TxtAchternaam.clear();
                    TxtLengte.clear();
                    TxtGewicht.clear();
                    TxtTelefoonNummer.clear();
                    TxtNoodNummer.clear();
                    TxtExtraNoodNummer.clear();
                    TxtAllergieen.clear();

                } catch (Exception e) {
                PopUP(""+e);
                }
            }
        });

        //Dit is niet best practice maar het wilde met niet op een andere manier lukken 
        //ik maak hier een nieuw scherm die ik dan open als een popup scherm
        //in dit scherm kun je een persoon wijzigen
        //je kunt er ook zijn aandoeningen inzien en deze op genezen zetten
        BtnWijzig.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    EntPatient Patient = list.getSelectionModel().getSelectedItem();
                    int PID = Patient.getId();

                    Pane pane = new Pane();
                    Stage stage = new Stage();
                    stage.setTitle("Aanpassen");
                    stage.setScene(new Scene(pane, 700, 500));
                    stage.getIcons().add(new Image(ZorgCode.class.getResourceAsStream("Icon/images.png")));
                    pane.setStyle("-fx-background-color: #A7b6FF;");
                    stage.show();

                    TextField AanpassenVoorNaam = new TextField();
                    TextField AanpassenAchternaam = new TextField();
                    DatePicker AanpassenGeboorteDatum = new DatePicker();
                    TextField AanpassenLengte = new TextField();
                    TextField AanpassenGewicht = new TextField();
                    TextField AanpassenTelefoonNummer = new TextField();
                    TextField AanpassenNoodNummer = new TextField();
                    TextField AanpassenExtraNoodNummer = new TextField();
                    TextArea AanpassenAllergieen = new TextArea();

                    AanpassenVoorNaam.setLayoutX(10);
                    AanpassenVoorNaam.setLayoutY(10);
                    AanpassenVoorNaam.setText(db.GeefPatient(PID).Voornaam);

                    AanpassenAchternaam.setLayoutX(10);
                    AanpassenAchternaam.setLayoutY(50);
                    AanpassenAchternaam.setText(db.GeefPatient(PID).Achternaam);

                    AanpassenGeboorteDatum.setLayoutX(10);
                    AanpassenGeboorteDatum.setLayoutY(90);
                    String Datum = db.GeefPatient(PID).GeboorteDatum;
                    LocalDate localDate = LocalDate.parse(Datum);
                    AanpassenGeboorteDatum.setValue(localDate);

                    AanpassenLengte.setLayoutX(10);
                    AanpassenLengte.setLayoutY(130);
                    AanpassenLengte.setText("" + db.GeefPatient(PID).Lengte);

                    AanpassenGewicht.setLayoutX(10);
                    AanpassenGewicht.setLayoutY(170);
                    AanpassenGewicht.setText("" + db.GeefPatient(PID).Gewicht);

                    AanpassenTelefoonNummer.setLayoutX(10);
                    AanpassenTelefoonNummer.setLayoutY(210);
                    AanpassenTelefoonNummer.setText(db.GeefPatient(PID).Telefoonnummer);

                    AanpassenNoodNummer.setLayoutX(10);
                    AanpassenNoodNummer.setLayoutY(250);
                    AanpassenNoodNummer.setText(db.GeefPatient(PID).Noodnummer);

                    AanpassenExtraNoodNummer.setPromptText("Extra Noodnummer");
                    AanpassenExtraNoodNummer.setLayoutX(10);
                    AanpassenExtraNoodNummer.setLayoutY(290);
                    AanpassenExtraNoodNummer.setText(db.GeefPatient(PID).ExtraNoodnummer);

                    AanpassenAllergieen.setPromptText("Allergieën");
                    AanpassenAllergieen.setLayoutX(10);
                    AanpassenAllergieen.setLayoutY(330);
                    AanpassenAllergieen.setPrefSize(200, 100);
                    AanpassenAllergieen.setWrapText(true);
                    AanpassenAllergieen.setText(db.GeefPatient(PID).Allergieen);

                    Button AanpassingOpslaan = new Button("Wijziging opslaan");
                    AanpassingOpslaan.setLayoutX(10);
                    AanpassingOpslaan.setLayoutY(450);

                    Button BtnVerwijder = new Button("Verwijder");
                    BtnVerwijder.setLayoutX(130);
                    BtnVerwijder.setLayoutY(450);

                    ListView<EntAanDoening> LijstAandoeningen = new ListView<EntAanDoening>();
                    LijstAandoeningen.setLayoutX(400);
                    LijstAandoeningen.setLayoutY(10);
                    LijstAandoeningen.setPrefSize(200, 100);

                    Button BtnGenezen = new Button("Genezen");
                    BtnGenezen.setLayoutX(400);
                    BtnGenezen.setLayoutY(120);

                    if (ad.connectDb()) {
                        LijstAandoeningen.setItems((ad.AandoeningBijPersoon(PID)));
                    }

                    pane.getChildren().addAll(AanpassenVoorNaam, AanpassenAchternaam, AanpassenGeboorteDatum, AanpassenLengte, AanpassenGewicht, AanpassenTelefoonNummer, AanpassenNoodNummer, AanpassenExtraNoodNummer, AanpassenAllergieen, AanpassingOpslaan, BtnVerwijder, LijstAandoeningen, BtnGenezen);

                    BtnGenezen.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try {
                                EntAanDoening deAandoening = LijstAandoeningen.getSelectionModel().getSelectedItem();
                                int AID = deAandoening.getId();
                                ad.AandoeningenGenezen(AID);
                                LijstAandoeningen.setItems((ad.AandoeningBijPersoon(PID)));
                            } catch (Exception e) {
                            PopUP("Selecteer een aandoening");
                            }
                        }
                    });

                    AanpassingOpslaan.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try {
                                String Voornaam = AanpassenVoorNaam.getText();
                                String Achternaam = AanpassenAchternaam.getText();
                                LocalDate LocalDate = AanpassenGeboorteDatum.getValue();
                                String Datum = LocalDate.toString();
                                Double Lengte = Double.parseDouble(AanpassenLengte.getText());
                                Double Gewicht = Double.parseDouble(AanpassenGewicht.getText());
                                String TelefoonNummer = AanpassenTelefoonNummer.getText();
                                String NoodNummer = AanpassenNoodNummer.getText();
                                String ExtraNoodnummer = AanpassenExtraNoodNummer.getText();
                                String Allergieen = AanpassenAllergieen.getText();
                                db.AanpassenPatient(PID, Voornaam, Achternaam, Datum, Lengte, Gewicht, TelefoonNummer, NoodNummer, ExtraNoodnummer, Allergieen);
                                list.setItems(db.VulLijstPatienten());
                                stage.close();

                            } catch (Exception e) {
                            PopUP(""+e);
                            }
                        }
                    });
                    BtnVerwijder.setOnAction(new EventHandler<ActionEvent>() {

                        @Override
                        public void handle(ActionEvent event) {
                            db.DeletePatient(PID);
                            list.setItems(db.VulLijstPatienten());
                            stage.close();
                        }
                    });

                } catch (Exception e) {
                PopUP("Selecteer een Patient");
                }

            }
        });

        //Dit is niet best practice maar het wilde met niet op een andere manier lukken 
        //ik maak hier een nieuw scherm die ik dan open als een popup scherm
        BtnAandoening.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    EntPatient Patient = list.getSelectionModel().getSelectedItem();
                    int PID = Patient.getId();

                    Pane pane = new Pane();
                    Stage stage = new Stage();
                    stage.setTitle("Aanpassen");
                    stage.setScene(new Scene(pane, 225, 150));
                    pane.setStyle("-fx-background-color: #A7b6FF;");
                    stage.getIcons().add(new Image(ZorgCode.class.getResourceAsStream("Icon/images.png")));
                    stage.show();

                    Button BtnToevoegen = new Button("Toevoegen");
                    BtnToevoegen.setLayoutX(10);
                    BtnToevoegen.setLayoutY(100);

                    Label LblAandoeningen = new Label("Aandoening: ");
                    LblAandoeningen.setLayoutX(10);
                    LblAandoeningen.setLayoutY(10);

                    Aandoening AD = new Aandoening();
                    ComboBox<EntAanDoening> cbxAandoeningen = new ComboBox<EntAanDoening>();
                    cbxAandoeningen.setLayoutX(100);
                    cbxAandoeningen.setLayoutY(10);

                    if (AD.connectDb()) {
                        cbxAandoeningen.getItems().addAll(AD.VulLijstAandoening());
                    }

                    BtnToevoegen.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            try {
                                EntAanDoening deAandoening = cbxAandoeningen.getSelectionModel().getSelectedItem();
                                int AID = deAandoening.getId();
                                db.AandoeningToevoegen(PID, AID);
                                stage.close();
                            } catch (Exception e) {
                                PopUP("Selecteer een aandoening");
                            }
                        }
                    });

                    pane.getChildren().addAll(LblAandoeningen, cbxAandoeningen, BtnToevoegen);

                } catch (Exception e) {
                PopUP("Selecteer iets uit de lijst");
                }

            }
        });
        if (db.connectDb()) {
            list.setItems(db.VulLijstPatienten());
        }
    }

    public void PopUP(String Message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Er ging iets fout!");
        alert.setContentText(Message);
        alert.showAndWait();
    }

}
