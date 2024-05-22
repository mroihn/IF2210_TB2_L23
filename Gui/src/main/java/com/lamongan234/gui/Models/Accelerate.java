package com.lamongan234.gui.Models;

public class Accelerate extends Item{
  public Accelerate(){
    super("Accelerate");
  }
  public void Efek(Kartu target){
    if(target instanceof Hewan){
      Hewan h = (Hewan) target;
      h.addweight(8);
    }else if(target instanceof Tanaman){
      Tanaman t = (Tanaman) target;
      t.addweight(2);
    }else{
      System.err.println("Invalid move");
    }
  }
}
