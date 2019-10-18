/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zorgcode;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author rickd
 */
public class ZorgCode extends Application {

    @Override
    public void start(Stage primaryStage) {
        Button BtnPatient = new Button("Patienten");
        Button BtnAandoening = new Button("Aandoening");
        Button BtnBehandeling = new Button("Behandeling");
        ImageView imageview = new ImageView();

        imageview.setImage(new Image(ZorgCode.class.getResourceAsStream("Images/ziekenhuis.jpg")));
        imageview.setLayoutX(400);
        imageview.setLayoutY(100);
        imageview.setFitHeight(1280);
        imageview.setFitWidth(720);
        imageview.setPreserveRatio(true);

        Pane root = new Pane();
        root.getChildren().addAll(BtnPatient, BtnAandoening, BtnBehandeling, imageview);
        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setTitle("ZorgCode");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(ZorgCode.class.getResourceAsStream("Images/Icon.png")));
        root.setStyle("-fx-background-color: #8bb3e8;");
        primaryStage.show();

        BtnPatient.setLayoutX(50);
        BtnPatient.setLayoutY(150);
        BtnPatient.setPrefSize(200, 100);
        BtnPatient.setStyle("-fx-font-size: 20;");

        BtnAandoening.setLayoutX(50);
        BtnAandoening.setLayoutY(300);
        BtnAandoening.setPrefSize(200, 100);
        BtnAandoening.setStyle("-fx-font-size: 20;");

        BtnBehandeling.setLayoutX(50);
        BtnBehandeling.setLayoutY(450);
        BtnBehandeling.setPrefSize(200, 100);
        BtnBehandeling.setStyle("-fx-font-size: 20;");

        BtnPatient.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                PatientenCRUD Patient = new PatientenCRUD(primaryStage, scene);
            }
        });

        BtnAandoening.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                AandoeningCRUD aandoening = new AandoeningCRUD(primaryStage, scene);
            }
        });
        BtnBehandeling.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                BehandelingCRUD behandeliing = new BehandelingCRUD(primaryStage, scene);
            }
        });

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }

}
