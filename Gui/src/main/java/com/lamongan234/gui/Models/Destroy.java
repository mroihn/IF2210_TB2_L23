package com.lamongan234.gui.Models;

public class Destroy extends Item{
  public Destroy(){
    super("Destroy");
  }
  public void Efek(Harvestable target){
    target = null;
  }
}
