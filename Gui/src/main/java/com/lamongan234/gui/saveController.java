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


public class saveController {
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
                txtPluginSaveAndLoad.saveState(game, folderInput);

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
