package com.lamongan234.gui;

import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Card extends Pane {
    private final List<String> cardNames = new ArrayList<>(
            Arrays.asList("Hiu Darat","Sapi","Domba","Kuda","Ayam","Beruang","Labu","Jagung","Stroberi","Susu","Telur","Sirip Hiu","Daging Kuda","Daging Domba","Daging Beruang","Biji Labu","Biji Jagung","Biji Stroberi","Accelerate","Delay","Instant Harvest","Destroy","Protect","Trap"));
    private String Name;
    private ImageView imageCard;

    public Card(){
        Random rand = new Random();
        int idx = rand.nextInt(cardNames.size());
        String temp = cardNames.get(idx);
        Name = temp;
        temp = temp.replaceAll(" \\s+","-");
        this.getStyleClass().add("card-style");
        this.setPrefSize(80,100);
        this.getStyleClass().add(temp+"-style");
    }

    public Card(String name){
        Name = name;
        this.getStyleClass().add("card-style");
        this.setPrefSize(80,100);
        this.getStyleClass().add(name + "-style");
    }

    public String getName() {
        return Name;
    }

    public void setName(String name){
        Name = name;
        this.getStyleClass().add(name + "-style");
    }
}
