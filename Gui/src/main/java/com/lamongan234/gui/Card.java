package com.lamongan234.gui;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Card extends Pane {
    private final List<String> cardNames = new ArrayList<>(
            Arrays.asList("hiu-darat","cow","sheep","horse","chicken","bear","Labu","Jagung","Stroberi","Susu","Telur","Sirip_Hiu","Daging_Kuda","Daging_Domba","Daging_Beruang","Biji_Labu","Biji_Jagung","Biji_Stroberi","Accelerate","Delay","Instant_Harvest","Destroy","Protect","Trap"));
    private String Name;
    private String Type;
    private ImageView imageCard;

    public Card(){
        Name = "hiu-darat";
        Type = "temp";
        this.getStyleClass().add("card-style");
        this.setPrefSize(80,100);
        this.getStyleClass().add("hiu-darat-style");
    }

    public Card(String name){
        Name = name;
        Type = "temp";
        this.getStyleClass().add("card-style");
        this.setPrefSize(80,100);
        this.getStyleClass().add("hiu-darat-style");
    }

    public String getName() {
        return Name;
    }

    public String getType(){
        return Type;
    }
}