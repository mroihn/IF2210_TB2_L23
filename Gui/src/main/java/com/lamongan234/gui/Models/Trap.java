package com.lamongan234.gui.Models;

public class Trap extends Item{
  public Trap(){
    super("Trap");
  }
  public void Efek(Harvestable target){
    if(target instanceof Hewan){
      Hewan h = (Hewan) target;
      h.setTrap();
      h.AppliedItem(this);
    }else if(target instanceof Tanaman){
      Tanaman t = (Tanaman) target;
      t.setTrap();
      t.AppliedItem(this);
    }else{
      System.err.println("Invalid move");
    }
  }
}
