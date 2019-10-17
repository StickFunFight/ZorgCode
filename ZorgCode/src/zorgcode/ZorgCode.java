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
import javafx.scene.layout.FlowPane;
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

        FlowPane root = new FlowPane();
        root.getChildren().add(BtnPatient);
        root.getChildren().add(BtnAandoening);
        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setTitle("ZorgCode");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image(ZorgCode.class.getResourceAsStream("Icon/images.png")));
        root.setStyle("-fx-background-color: #8bb3e8;");
        primaryStage.show();

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
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);

    }

}
