package com.lamongan234.gui.Models;

public class Domba extends Herbivora {
  public Domba(){
    super("Domba", 0, 12);
  }
  public Product panenKartu(){
    if(isSiapPanen()){
      System.out.println("Domba dipanen!");
      return new DagingDomba();
    }else{
      System.out.println("Domba belum bisa dipanen!");
    }
    return null;
  }
  public void gunakanItem(Kartu self, Kartu target){
    
  }
}
