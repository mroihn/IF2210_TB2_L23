package com.lamongan234.gui.Models;

public class Kuda extends Herbivora {
  public Kuda(){
    super("Kuda", 0, 14);
  }
  public Product panenKartu(){
    if(isSiapPanen()){
      System.out.println("Kuda dipanen!");
      return new DagingDomba();
    }else{
      System.out.println("Kuda belum bisa dipanen!");
    }
    return null;
  }
  public void gunakanItem(Kartu self, Kartu target){
    
  }
}
