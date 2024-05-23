package com.lamongan234.gui;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class  ShufflePopupController {
    @FXML
    AnchorPane shuffleAnchor;

    @FXML
    GridPane ShuffleCointainer;

    @FXML
    Button b11Accept;

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

    @FXML
    public void initialize() {
        int i = 0;

        for (Node pane : ShuffleCointainer.getChildren()) {

            if (pane instanceof Pane) {
                pane.getStyleClass().add("card-style");
                makeClickable((Pane) pane);
                pane.setId("s"+i);

                Card card = new Card();
                card.setId("c"+i);

                System.out.println(i);
                ((Pane) pane).getChildren().add(card);
                System.out.println(card.getName());
                i++;
            }
        }

        b11Accept.getStyleClass().add("button-style");
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
        stage.close();
    }

    protected void makeClickable(Pane pane){
        pane.setOnMouseClicked(event -> {
            if (!pane.getStyleClass().contains("style-toggled")) {
                pane.getStyleClass().add("style-toggled");
                freeslot--;
            } else {
                pane.getStyleClass().remove("style-toggled");
                freeslot++;
            }
            System.out.println(freeslot);
        });
    }
}
