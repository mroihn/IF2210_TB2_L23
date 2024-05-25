package com.lamongan234.gui;

import com.lamongan234.gui.Models.*;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.Map;

public class DetailPopupController {

    @FXML
    AnchorPane detailAnchor;

    @FXML
    Button  buttonBack;

    @FXML
    Label nama;

    @FXML
    Label data;

    @FXML
    Label item;

    @FXML
    Button panen;

    private HelloController mainController;
    private Rectangle overlay;
    private Card card;
    private int umur_or_berat;
    private int umur_or_berat_harvest;
    private GameManager game;
    private int pos;
    private Map<String, Integer> ListItem;

    public void setMainController(HelloController mainController) {
        this.mainController = mainController;
        System.out.println("MainController is set in PopupController: " + this.mainController);
    }

    public void setPos(int pos){
        this.pos = pos;
    }

    public void setOverlay(Rectangle overlay) {
        this.overlay = overlay;
    }

    public void setCard(Card card){
        this.card = card;
        nama.setText(card.getName());
        if(card.getKartu() instanceof Hewan){
            Hewan hewan = (Hewan) card.getKartu();
            umur_or_berat = hewan.getUmurOrBerat();
            umur_or_berat_harvest = hewan.getweight_to_harvest();
            data.setText("Berat : " + String.valueOf(hewan.getUmurOrBerat())+" ("+String.valueOf(hewan.getweight_to_harvest())+")");
        }else {
            Tanaman tanaman = (Tanaman) card.getKartu();
            umur_or_berat = tanaman.getUmurOrBerat();
            umur_or_berat_harvest = tanaman.getweight_to_harvest();
            data.setText("Umur : " + String.valueOf(tanaman.getUmurOrBerat())+" ("+String.valueOf(tanaman.getweight_to_harvest())+")");
        }
        System.out.println(pos);
        ListItem = game.getCurrPlayer().ladang[pos].getListAppliedItem();
        if(!ListItem.isEmpty()){
            StringBuilder itemsText = new StringBuilder();
            itemsText.append("Item Aktif :");
            for (Map.Entry<String, Integer> entry : ListItem.entrySet()) {
                itemsText.append(entry.getKey())
                        .append("(")
                        .append(entry.getValue())
                        .append(")")
                        .append(",");
            }
            item.setText(itemsText.toString());
        }
        if(umur_or_berat >= umur_or_berat_harvest){
            panen.setStyle("-fx-text-fill: black");
            panen.setStyle("-fx-border-color: black");
            panen.setOnAction(actionEvent -> {
                panenKartu();
                Stage stage = (Stage)panen.getScene().getWindow();
                stage.close();
            });
        }
        else{
            panen.setStyle("-fx-text-fill: gray");
        }

    }

    private void panenKartu(){
        game.getCurrPlayer().Panen(pos);
        mainController.renderLadang(mainController.getLadangPlayer(),game.getCurrPlayer().ladang);
        mainController.renderHand(mainController.getHandPlayer(),game.getCurrPlayer().getActiveDeck());
        mainController.clearAll(mainController.ladangA);
        mainController.clearAll(mainController.hand);
        mainController.loadAll(mainController.getLadangPlayer(), mainController.ladangA);
        mainController.loadAll(mainController.getHandPlayer(),mainController.hand);
    }

    @FXML
    public void initialize(GameManager game,int pos){
        this.game = game;
        setPos(pos);
        buttonBack.setOnAction(actionEvent -> {
            Stage stage = (Stage)buttonBack.getScene().getWindow();
            stage.close();
        });
    }

}
