package com.lamongan234.gui;

import com.lamongan234.gui.Models.GameManager;
import com.lamongan234.gui.Models.Toko;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class TokoController {

    @FXML
    Button b21Back;

    @FXML
    Button b22Beli;

    @FXML
    Button b23Jual;

    @FXML
    GridPane tokoContainer;

    private HelloController mainController;
    private Map<String, Integer> ListQuantity;
    private Map<String, Integer> ListPrice;
    private Rectangle overlay;
    private GameManager game;
    private String[] listnama = new String[9];

    public void setMainController(HelloController mainController) {
        this.mainController = mainController;
        System.out.println("MainController is set in PopupController: " + this.mainController);
    }


    public void setOverlay(Rectangle overlay) {
        this.overlay = overlay;
    }

    @FXML
    public void initialize(GameManager game) {
        this.game = game;
        listnama[0] = "Labu";
        listnama[1] = "Jagung";
        listnama[2] = "Stroberi";
        listnama[3] = "Susu";
        listnama[4] = "Telur";
        listnama[5] = "Sirip-Hiu";
        listnama[6] = "Daging-Domba";
        listnama[7] = "Daging-Kuda";
        listnama[8] = "Daging-Beruang";

        this.ListQuantity = game.getToko().getListQuantity();
        this.ListPrice = game.getToko().getListPrice();

        int i = 0;
        String temp;
        for (Node pane : tokoContainer.getChildren()) {

            if (pane instanceof Pane) {
                System.out.println("test");
                pane.setId("t"+i);
                //pane.setStyle("-fx-background-color: Black;");
                pane.getStyleClass().add("toko-container-style");


                ((Pane) pane).getChildren().get(0).getStyleClass().add("card-container-style");
                ((Pane) pane).getChildren().get(0).getStyleClass().add(listnama[i] +  "-style");

                ((Label) ((Pane) pane).getChildren().get(2)).setText(String.valueOf(ListPrice.get(listnama[i])));
                ((Label) ((Pane) pane).getChildren().get(4)).setText(String.valueOf(ListQuantity.get(listnama[i])));
                ((Label) ((Pane) pane).getChildren().get(5)).setText("Dibeli: " + "0");
                i++;
            }
        }

        b21Back.setOnAction(actionEvent -> {
            Stage stage = (Stage)b21Back.getScene().getWindow();
            stage.close();
        });
    }

    public void buyButton(){

    }

    public void sellButton(){

    }

}
