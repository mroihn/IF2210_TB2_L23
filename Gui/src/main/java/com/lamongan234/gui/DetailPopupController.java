package com.lamongan234.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class DetailPopupController {

    @FXML
    AnchorPane shuffleAnchor;

    @FXML
    Button  buttonBack;

    @FXML
    Label nama;

    private HelloController mainController;
    private Rectangle overlay;
    private Card card;

    public void setMainController(HelloController mainController) {
        this.mainController = mainController;
        System.out.println("MainController is set in PopupController: " + this.mainController);
    }

    public void setOverlay(Rectangle overlay) {
        this.overlay = overlay;
    }

    public void setCard(Card card){
        this.card = card;
        nama.setText(card.getName());
    }

    @FXML
    public void initialize(){
        buttonBack.setOnAction(actionEvent -> {
            Stage stage = (Stage)buttonBack.getScene().getWindow();
            stage.close();
        });
    }

}
