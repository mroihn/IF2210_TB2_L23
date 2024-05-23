package com.lamongan234.gui.Models;

public class Destroy extends Item{
  public Destroy(){
    super("Destroy");
  }
  public void Efek(Harvestable target){
    if(target instanceof Hewan){
      Hewan h = (Hewan) target;
      h = null;
    }else if(target instanceof Tanaman){
      Tanaman t = (Tanaman) target;
      t.addweight(-2);
      t = null;
    }else{
      System.err.println("Invalid move");
    }
  }
}
