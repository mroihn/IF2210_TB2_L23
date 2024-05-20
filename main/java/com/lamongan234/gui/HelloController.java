package com.lamongan234.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class HelloController {
    @FXML
    private VBox buttonContainer;

    @FXML
    private Pane turnClock;

    @FXML
    private Label player1Gold;

    @FXML
    private Label player2Gold;

    @FXML
    private Label clockCounter;

    @FXML
    private GridPane ladangA;

    @FXML
    private GridPane deck;

    @FXML
    private Button b0Next;

    @FXML
    private Button b1Ladangku;

    @FXML
    private Button b2LadangLawan;

    @FXML
    private Button b3Toko;

    @FXML
    private Button b4SaveState;

    @FXML
    private Button b5LoadState;

    @FXML
    private Button b6LoadPlugin;

    @FXML
    public void initialize() {
        int i,j;
        // deck
        for (i = 0; i < deck.getColumnCount(); i++) {
            Pane pane = new Pane();
            pane.setMinSize(80, 100);
            pane.getStyleClass().add("card-style");
            deck.add(pane, i, 0);
        }
        // ladangA
        for (i = 0; i < 5; i++) {
            for (j = 0; j < 4; j++) {
                Pane pane = new Pane();
                pane.setMinSize(80, 100);
                pane.getStyleClass().add("card-style");
                ladangA.add(pane, i, j);
            }
        }

        //labels and clock
        turnClock.getStyleClass().add("clock-style");

        //button style
        b0Next.getStyleClass().add("button-style");

        b1Ladangku.getStyleClass().add("button-style");
        b2LadangLawan.getStyleClass().add("button-style");
        b3Toko.getStyleClass().add("button-style");
        b4SaveState.getStyleClass().add("button-style");
        b5LoadState.getStyleClass().add("button-style");
        b6LoadPlugin.getStyleClass().add("button-style");

        buttonContainer.getStyleClass().add("button-container-style");
        buttonContainer.setAlignment(javafx.geometry.Pos.CENTER);

    }
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }
}