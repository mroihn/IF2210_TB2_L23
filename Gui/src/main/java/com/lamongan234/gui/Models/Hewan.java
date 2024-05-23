package com.lamongan234.gui.Models;

import java.util.Map;

public abstract class Hewan extends Kartu implements Harvestable{
  protected int berat;
  protected int weight_to_harvest;
  protected boolean isProtected;
  protected boolean isTrapped;
  protected Map<String, Integer> ListItem;
  public Hewan(String nama, int berat, int weight_to_harvest) {
    super(nama);
    this.berat = berat;
    this.weight_to_harvest = weight_to_harvest;
  }
  public void setProtect(){
    isProtected = true;
  }
  public void setTrap(){
    isTrapped = true;
  }
  // Getter and setter
  public int getberat() {
    return berat;
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
  public abstract void makan(Product makanan);
}

