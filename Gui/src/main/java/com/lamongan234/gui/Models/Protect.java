package com.lamongan234.gui.Models;

public class Protect extends Item{
  public Protect(){
    super("Protect");
  }
  public void Efek(Kartu target){
    if(target instanceof Hewan){
      Hewan h = (Hewan) target;
      h.setProtect();
    }else if(target instanceof Tanaman){
      Tanaman t = (Tanaman) target;
      t.setProtect();
    }else{
      System.err.println("Invalid move");
    }
  }
}
