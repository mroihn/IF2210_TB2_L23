package com.lamongan234.gui;

import com.lamongan234.gui.Models.GameManager;
import com.lamongan234.gui.Models.Harvestable;
import com.lamongan234.gui.Models.Kartu;
import com.lamongan234.gui.Models.SaveAndLoad;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;


public class loadController {
    @FXML
    Button buttonBack;

    @FXML
    ChoiceBox<String> pilihFormat;

    @FXML
    TextField folder;

    @FXML
    Button submit;

    private String[] format = {"TXT","XML","YMAL"};
    private HelloController mainController;
    private Rectangle overlay;
    private String formatChoice;
    private GameManager game ;


    public void setMainController(HelloController mainController) {
        this.mainController = mainController;
        System.out.println("MainController is set in PopupController: " + this.mainController);
    }

    public void setOverlay(Rectangle overlay) {
        this.overlay = overlay;
    }

    @FXML
    public void initialize(GameManager game){
        this.game = game;
        buttonBack.setOnAction(actionEvent -> {
            Stage stage = (Stage)buttonBack.getScene().getWindow();
            stage.close();
        });
        submit.setOnAction(actionEvent -> {
            if(formatChoice.equals("TXT")){
                String jarPath = "TxtSaveAndLoad.jar";
                String folderInput = folder.getText();
                SaveAndLoad txtPluginSaveAndLoad = game.SaveAndLoadPlugin(jarPath, "com.lamongan234.gui.Models.TxtSaveAndLoad");
                txtPluginSaveAndLoad.loadState(game, folderInput);
                mainController.clockCounter.setText(String.valueOf(game.getTurn())+"/20");
                mainController.player1Gold.setText("Player 1:"+String.valueOf(game.getPlayer1().getUang()));
                mainController.player2Gold.setText("Player 2:"+String.valueOf(game.getPlayer2().getUang()));
                Harvestable[] h = game.getPlayer1().ladang;
                mainController.renderLadang(mainController.ladangPlayer1, h);


                mainController.ladangPlayer2 = new Card[20];
                h = game.getPlayer1().ladang;
                mainController.renderLadang(mainController.ladangPlayer2, h);
                mainController.handPlayer1= new Card[6];
                Kartu[] h2 = game.getPlayer1().getActiveDeck();
                mainController.renderHand(mainController.handPlayer1, h2);
                mainController.handPlayer2= new Card[6];
                Kartu[] h3 = game.getPlayer1().getActiveDeck();
                mainController.renderHand(mainController.handPlayer2, h3);
                mainController.showLadangku();
            }
            Stage stage = (Stage)submit.getScene().getWindow();
            System.out.println(formatChoice);
            System.out.println(folder.getText());
            stage.close();
        });
        pilihFormat.getItems().addAll(format);
        pilihFormat.setOnAction(this::getFormat);
    }

    public void getFormat(ActionEvent event){
        this.formatChoice = pilihFormat.getValue();
    }
}
