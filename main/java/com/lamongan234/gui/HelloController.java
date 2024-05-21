package com.lamongan234.gui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
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
    private Label clockLabel;

    @FXML
    private Pane playerGoldContainer;

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
        // deck
        int i;
        i=0;
        for (Node pane : deck.getChildren()) {
            if (pane instanceof Pane) {
                pane.getStyleClass().add("card-style");
                pane.setId("d"+i);
                makeDraggable(pane);

                i++;
            }
        }

        // ladangA
        i=0;
        for (Node pane : ladangA.getChildren()) {
            if (pane instanceof Pane) {
                pane.getStyleClass().add("card-style");
                pane.setId("l"+i);
                makeDraggable(pane);

                //pane.setOnDragDetected();
                i++;
            }
        }

        //labels and clock
        turnClock.getStyleClass().add("clock-style");
        clockLabel.getStyleClass().add("label-style");
        clockCounter.getStyleClass().add("label-style");
        player1Gold.getStyleClass().add("label-style");
        player2Gold.getStyleClass().add("label-style");
        playerGoldContainer.getStyleClass().add("container-style");

        //button style
        b0Next.getStyleClass().add("button-style");

        b1Ladangku.getStyleClass().add("button-style");
        b2LadangLawan.getStyleClass().add("button-style");
        b3Toko.getStyleClass().add("button-style");
        b4SaveState.getStyleClass().add("button-style");
        b5LoadState.getStyleClass().add("button-style");
        b6LoadPlugin.getStyleClass().add("button-style");

        buttonContainer.getStyleClass().add("container-style");
        buttonContainer.setAlignment(javafx.geometry.Pos.CENTER);

    }

    protected void makeDraggable(Node node) {
        node.setOnDragDetected(
                event -> {
                    System.out.println("Start Drag: " + node.getId());
                    Dragboard db = node.startDragAndDrop(TransferMode.MOVE);
                    ClipboardContent content = new ClipboardContent();
                    content.putString("pane");
                    db.setContent(content);
                    event.consume();
                }
        );
        node.setOnDragOver(
                event -> {
                    System.out.println("Hovering Over: " + node.getId());
                    if (event.getGestureSource() != node && event.getDragboard().hasString()) {
                        event.acceptTransferModes(TransferMode.MOVE);
                    }
                    event.consume();
                }
        );
//        node.setOnDragExited(
//                event -> {
//                    System.out.println("Exited On: " + node.getId());
//                    event.consume();
//                }
//        );
        node.setOnDragDropped(event -> {
//            Dragboard db = event.getDragboard();
//            boolean success = false;
//            if (db.hasString() && "circle".equals(db.getString())) {
//                sourcePane.getChildren().remove(draggableCircle);
//                targetPane.getChildren().add(draggableCircle);
//                success = true;
//            }
//            event.setDropCompleted(success);
            System.out.println("Drag Dropped: " + node.getId());
            event.consume();
        });
    }
    protected void shuffleDeck(){

    }
//    protected void onHelloButtonClick() {
//        welcomeText.setText("Welcome to JavaFX Application!");
//    }
}