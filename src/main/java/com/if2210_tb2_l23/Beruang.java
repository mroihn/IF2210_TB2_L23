package com.if2210_tb2_l23;

public class Beruang extends Omnivora {
  public Beruang(){
    super(0, 25);
  }
  public Kartu panenKartu(){
    if(isSiapPanen()){
      System.out.println("Beruang dipanen!");
      return new Kartu("daging Beruang", new Beruang());
    }else{
      System.out.println("Beruang belum bisa dipanen!");
    }
    return null;
  }
  public void gunakanItem(Kartu self, Kartu target){
    
  }
}
