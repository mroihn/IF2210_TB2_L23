package com.lamongan234.gui.Models;

public class InstantHarvest extends Item{
  public InstantHarvest(){
    super("Instant Harvest");
  }
  public void Efek(Kartu target){
    if(target instanceof Hewan){
      Hewan h = (Hewan) target;
      h.addweight(100);
      h.panenKartu();
    }else if(target instanceof Tanaman){
      Tanaman t = (Tanaman) target;
      t.addweight(100);
      t.panenKartu();
    }else{
      System.err.println("Invalid move");
    }
  }
}
