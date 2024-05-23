package com.lamongan234.gui.Models;

public class InstantHarvest extends Item{
  public InstantHarvest(){
    super("Instant Harvest");
  }
  public void Efek(Harvestable target){
    if(target instanceof Hewan){
      Hewan h = (Hewan) target;
      h.addweight(100);
      h.panenKartu();
      h.AppliedItem(this);
    }else if(target instanceof Tanaman){
      Tanaman t = (Tanaman) target;
      t.addweight(100);
      t.panenKartu();
      t.AppliedItem(this);
    }else{
      System.err.println("Invalid move");
    }
  }
}
