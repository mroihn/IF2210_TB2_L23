package com.lamongan234.gui.Models;

public class Delay extends Item{
  public Delay(){
    super("Delay");
  }
  public void Efek(Harvestable target){
    if(target instanceof Hewan){
      Hewan h = (Hewan) target;
      h.addweight(-5);
      h.AppliedItem(this);
    }else if(target instanceof Tanaman){
      Tanaman t = (Tanaman) target;
      t.addweight(-2);
      t.AppliedItem(this);
    }else{
      System.err.println("Invalid move");
    }
  }
}
