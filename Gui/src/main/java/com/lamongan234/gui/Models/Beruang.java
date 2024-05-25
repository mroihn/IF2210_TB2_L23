package com.lamongan234.gui.Models;

public class Beruang extends Omnivora {
  public Beruang(){
    super("Beruang", 0, 25);
  }
  public Product panenKartu(){
    if(isSiapPanen()){
      System.out.println("Beruang dipanen!");
      return new DagingBeruang();
    }else{
      System.out.println("Beruang belum bisa dipanen!");
    }
    return null;
  }
  public void gunakanItem(Kartu self, Kartu target){
    
  }
}
