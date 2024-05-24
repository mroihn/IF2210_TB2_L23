package com.lamongan234.gui.Models;
import java.util.HashMap;
import java.util.Map;

public abstract class Harvestable extends Kartu{
  protected int berat_or_umur;
  protected int weight_to_harvest;
  protected boolean isProtected;
  protected boolean isTrapped;
  protected Map<String, Integer> ListItem;
  public Harvestable(String nama, int berat, int weight_to_harvest) {
    super(nama);
    this.berat_or_umur = berat;
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
    return berat_or_umur;
  }

  public Map<String, Integer> getListAppliedItem(){
    return ListItem;
  }
  public void setBeratOrUmur(int berat_or_umur) {
    this.berat_or_umur = berat_or_umur;
  }
  public int getweight_to_harvest() {
    return weight_to_harvest;
  }
  public void addweight(int w){
    berat_or_umur+=w;
  }

  public boolean isSiapPanen(){
    return weight_to_harvest<=berat_or_umur;
  }
  public boolean isProtected(){
    return isProtected;
  }
  public boolean isTrapped(){
    return isTrapped;
  }
  public void AppliedItem(Item item){
    if(ListItem.get(item.getName())==null){
      ListItem.put(item.getName(), 1);
    }else{
      ListItem.put(item.getName(), ListItem.get(item.getName())+1);
    }
  }
  public Harvestable(String nama){
    super(nama);
  }
  public abstract void Display();
  public abstract Product panenKartu();
}
