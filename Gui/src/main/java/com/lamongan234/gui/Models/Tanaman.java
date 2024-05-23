package com.lamongan234.gui.Models;

import java.util.HashMap;
import java.util.Map;

public abstract class Tanaman extends Harvestable{
  int umur;
  int age_to_harvest;
  protected boolean isProtected;
  protected boolean isTrapped;
  protected Map<String, Integer> ListItem;

  public Tanaman(String nama, int umur, int age_to_harvest) {
    super(nama);
    this.umur = umur;
    this.age_to_harvest = age_to_harvest;
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
  public int getUmur() {
    return umur;
  }
  public void setUmur(int umur) {
    this.umur = umur;
  }
  public int getage_to_harvest() {
    return age_to_harvest;
  }
  public void addweight(int w){
    umur+=w;
  }

  public boolean isSiapPanen(){
    return age_to_harvest<=umur;
  }
  public void AppliedItem(Item item){
    if(ListItem.get(item.getName())==null){
      ListItem.put(item.getName(), 1);
    }else{
      ListItem.put(item.getName(), ListItem.get(item.getName())+1);
    }
  }
}
