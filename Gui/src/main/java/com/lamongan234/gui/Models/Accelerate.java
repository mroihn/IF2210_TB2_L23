package com.lamongan234.gui.Models;

public class Accelerate extends Item{
  public Accelerate(){
    super("Accelerate");
  }
  public void Efek(Harvestable target){
    if(target instanceof Hewan){
      System.out.println("Hewan");
      Hewan h = (Hewan) target;
      h.addweight(8);
      h.AppliedItem(this);
    }else if(target instanceof Tanaman){
      System.out.println("Tanaman");
      Tanaman t = (Tanaman) target;
      t.addweight(2);
      t.AppliedItem(this);
    }else{
      System.err.println("Invalid move");
    }
  }
}
