package com.lamongan234.gui;

import com.lamongan234.gui.Models.Harvestable;
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

    @FXML
    Label data;

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
        if(card.getKartu() instanceof Harvestable){
            Harvestable hewan = (Harvestable) card.getKartu();
            data.setText("Berat : " + String.valueOf(hewan.getUmurOrBerat()));
        }

    }

    @FXML
    public void initialize(){
        buttonBack.setOnAction(actionEvent -> {
            Stage stage = (Stage)buttonBack.getScene().getWindow();
            stage.close();
        });
    }

}
