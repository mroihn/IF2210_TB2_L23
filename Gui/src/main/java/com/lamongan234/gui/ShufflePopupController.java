package com.lamongan234.gui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class  ShufflePopupController {
    @FXML
    AnchorPane shuffleAnchor;

    @FXML
    GridPane ShuffleContainer;

    @FXML
    Button b11Accept;

    @FXML
    Button b12Reshuffle;

    private HelloController mainController;
    private Rectangle overlay;
    private int freeslot;
    private Card[] shuffleChoices;

    // Method to set the main controller
    public void setMainController(HelloController mainController) {
        this.mainController = mainController;
        System.out.println("MainController is set in PopupController: " + this.mainController);
    }

    public void setOverlay(Rectangle overlay) {
        this.overlay = overlay;
    }

    public void setFreeslot(){
        freeslot = mainController.GetFreeSlotHand();
        System.out.println("this is a test");
    }

    @FXML
    public void initialize() {
        getShuffle();
        b11Accept.getStyleClass().add("button-style");
        b12Reshuffle.getStyleClass().add("button-style");
        b12Reshuffle.getStyleClass().add("reshuffle-button-style");
        b12Reshuffle.setOnAction(event -> {
            getShuffle();
        });
        //freeslot = mainController.GetFreeSlotHand();
        if (mainController != null) {
            freeslot = mainController.GetFreeSlotHand();
        }
    }

    @FXML
    private void addElementToMain() {
        if (mainController != null) {
            //mainController.addToCurHand();
            closePopup();
        }
    }

    @FXML
    private void closePopup() {
        Stage stage = (Stage) b11Accept.getScene().getWindow();
        Card[] cards = new Card[4];
        for (int i = 0; i < 4; i++) {
            if (( ShuffleContainer.getChildren().get(i)).getStyleClass().contains("style-toggled")) {
                Card card = (Card) ((Pane) ShuffleContainer.getChildren().get(i)).getChildren().get(0);
                cards[i] = card;
            }
        }
        mainController.addToCurHand(cards);
//        for (int i = 0; i < 6; i++) {
//            if (((Pane) ShuffleContainer.getChildren().get(i)).getChildren().isEmpty()) {
//                ((Pane) ShuffleContainer.getChildren().get(i)).getChildren().add(cards[i]);
//            }
//        }
        stage.close();
    }

    protected void makeClickable(Pane pane){
        pane.setOnMouseClicked(event -> {
            if (pane.getStyleClass().contains("style-toggled")) {
                pane.getStyleClass().remove("style-toggled");
                freeslot++;
            } else {
                if (freeslot>0) {
                    pane.getStyleClass().add("style-toggled");
                    freeslot--;
                }
                else{
                    System.out.println("toilet");
                }
            }
            System.out.println(freeslot);
        });
    }

    protected void getShuffle(){
        int i=0;
        for (Node pane : ShuffleContainer.getChildren()) {

            if (pane instanceof Pane) {
                pane.getStyleClass().add("card-container-style");
                makeClickable((Pane) pane);
                pane.setId("s"+i);
                ((Pane) pane).getChildren().clear();

                Card card = new Card();
                card.setId("c"+i);

                System.out.println(i);
                ((Pane) pane).getChildren().add(card);
                System.out.println(card.getName());
                i++;
            }
        }
    }
}
