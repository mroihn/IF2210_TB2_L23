package com.lamongan234.gui;

import com.lamongan234.gui.Models.*;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.util.*;

public class Card extends Pane {
    private final List<String> cardNames = new ArrayList<>(
            Arrays.asList("Hiu Darat","Sapi","Domba","Kuda","Ayam","Beruang","Labu","Jagung","Stroberi","Susu","Telur","Sirip Hiu","Daging Kuda","Daging Domba","Daging Beruang","Biji Labu","Biji Jagung","Biji Stroberi","Accelerate","Delay","Instant Harvest","Destroy","Protect","Trap"));
    private String Name;
    private ImageView imageCard;
    private Kartu kartu;
    private Map<String, Kartu> DictKartu;

    public Card(){
        generateDictKartu();
        Random rand = new Random();
        int idx = rand.nextInt(cardNames.size());
        String temp = cardNames.get(idx);
        Name = temp;
        kartu = DictKartu.get(Name);
        temp = temp.replaceAll(" ","-");

        this.getStyleClass().add("card-style");
        this.setPrefSize(80,100);
        this.getStyleClass().add(temp+"-style");
    }

    public Card(String name){
        Name = name;

        generateDictKartu();

        this.kartu = DictKartu.get(name);
        name = name.replaceAll(" ","-");
        this.getStyleClass().add("card-style");
        this.setPrefSize(80,100);
        this.getStyleClass().add(name + "-style");
    }

    public Card (Kartu kartu){
        this.kartu = kartu;
        this.Name = kartu.getName();
        Label label1 = new Label();
        label1.setText(Name);
        label1.getStyleClass().add("label-card-style");
        this.getChildren().add(label1);
        String name = kartu.getName();
        this.getStyleClass().add("card-style");
        name = name.replaceAll(" ","-");
        this.setPrefSize(80,100);
        this.getStyleClass().add(name + "-style");
    }

    private  void generateDictKartu(){
        DictKartu = new HashMap<>();
        DictKartu.put("Hiu Darat", new HiuDarat());
        DictKartu.put("Sapi", new Sapi());
        DictKartu.put("Domba", new Domba());
        DictKartu.put("Kuda", new Kuda());
        DictKartu.put("Ayam", new Ayam());
        DictKartu.put("Beruang", new Beruang());
        DictKartu.put("Labu", new Labu());
        DictKartu.put("Jagung", new Jagung());
        DictKartu.put("Stroberi", new Stroberi());
        DictKartu.put("Susu", new Susu());
        DictKartu.put("Telur", new Telur());
        DictKartu.put("Sirip Hiu", new SiripHiu());
        DictKartu.put("Daging Domba", new DagingDomba());
        DictKartu.put("Daging Kuda", new DagingKuda());
        DictKartu.put("Daging Beruang", new DagingBeruang());
        DictKartu.put("Biji Labu", new BijiLabu());
        DictKartu.put("Biji Jagung", new BijiJagung());
        DictKartu.put("Biji Stroberi", new BijiStroberi());
        DictKartu.put("Accelerate", new Accelerate());
        DictKartu.put("Delay", new Delay());
        DictKartu.put("Destroy", new Destroy());
        DictKartu.put("Instant Harvest", new InstantHarvest());
        DictKartu.put("Protect", new Protect());
        DictKartu.put("Trap", new Trap());
    }
    public Kartu getKartu(){
        return kartu;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name){
        Name = name;
        this.getStyleClass().add(name + "-style");
    }
}
