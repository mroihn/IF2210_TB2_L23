package com.lamongan234.gui.Models;

public class Protect extends Item{
  public Protect(){
    super("Protect");
  }
  public void Efek(Harvestable target){
    if(target instanceof Hewan){
      Hewan h = (Hewan) target;
      h.setProtect();
      h.AppliedItem(this);
    }else if(target instanceof Tanaman){
      Tanaman t = (Tanaman) target;
      t.setProtect();
      t.AppliedItem(this);
    }else{
      System.err.println("Invalid move");
    }
  }
}
