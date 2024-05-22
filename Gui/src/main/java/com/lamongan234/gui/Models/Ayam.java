package com.lamongan234.gui.Models;

public class Ayam extends Omnivora {
  public Ayam(){
    super("Ayam", 0, 5);
  }
  public Product panenKartu(){
    if(isSiapPanen()){
      System.out.println("Ayam dipanen!");
      return new DagingDomba();
    }else{
      System.out.println("Ayam belum bisa dipanen!");
    }
    return null;
  }
  public void gunakanItem(Kartu self, Kartu target){
    
  }
}
