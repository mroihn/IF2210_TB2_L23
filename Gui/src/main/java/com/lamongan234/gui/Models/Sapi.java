package com.lamongan234.gui.Models;

public class Sapi extends Herbivora {
  public Sapi(){
    super("Sapi", 0, 10);
  }
  public Product panenKartu(){
    if(isSiapPanen()){
      System.out.println("Sapi dipanen!");
      return new Susu();
    }else{
      System.out.println("Sapi belum bisa dipanen!");
    }
    return null;
  }
  public void gunakanItem(Kartu self, Kartu target){
    
  }
}
