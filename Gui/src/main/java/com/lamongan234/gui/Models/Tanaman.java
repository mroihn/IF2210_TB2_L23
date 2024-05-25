package com.lamongan234.gui.Models;

import java.util.HashMap;
import java.util.Map;

public abstract class Tanaman extends Harvestable{
  public Tanaman(String nama, int berat, int weight_to_harvest) {
    super(nama, berat, weight_to_harvest);
  }

  public void Display(){
    System.out.println("Umur: " + berat_or_umur + "/" + weight_to_harvest);
    System.out.print("Item Applied:");
    for (String key : ListItem.keySet()) {
      System.out.print(" "+ key + " " + ListItem.get(key));
    }
    System.out.println("");
  }
  public void AppliedItem(Item item){
    if(ListItem.get(item.getName())==null){
      ListItem.put(item.getName(), 1);
    }else{
      ListItem.put(item.getName(), ListItem.get(item.getName())+1);
    }
  }
}
