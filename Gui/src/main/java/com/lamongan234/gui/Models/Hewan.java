package com.lamongan234.gui.Models;

import java.util.HashMap;
import java.util.Map;

public abstract class Hewan extends Harvestable{
  protected int berat;
  protected int weight_to_harvest;
  protected boolean isProtected;
  protected boolean isTrapped;
  protected Map<String, Integer> ListItem;
  public Hewan(String nama, int berat, int weight_to_harvest) {
    super(nama);
    this.berat = berat;
    this.weight_to_harvest = weight_to_harvest;
    this.ListItem = new HashMap<>();
    this.isProtected = false;
    this.isTrapped = false;
  }
  public void setProtect(){
    isProtected = true;
  }
  public void setTrap(){
    isTrapped = true;
  }
  // Getter and setter
  public int getUmurOrBerat() {
    return berat;
  }

  public Map<String, Integer> getListAppliedItem(){
    return ListItem;
  }
  public void setBerat(int berat) {
    this.berat = berat;
  }
  public int getweight_to_harvest() {
    return weight_to_harvest;
  }
  public void addweight(int w){
    berat+=w;
  }

  public boolean isSiapPanen(){
    return weight_to_harvest<=berat;
  }
  public void Display(){
    System.out.println("Berat: " + berat + "/" + weight_to_harvest);
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
  public abstract void makan(Product makanan);
}

