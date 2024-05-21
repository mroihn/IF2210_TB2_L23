package com.if2210_tb2_l23;

public class Ayam extends Omnivora {
  public Ayam(){
    super(0, 5);
  }
  public Kartu panenKartu(){
    if(isSiapPanen()){
      System.out.println("Ayam dipanen!");
      return new Kartu("daging Ayam", new Ayam());
    }else{
      System.out.println("Ayam belum bisa dipanen!");
    }
    return null;
  }
  public void gunakanItem(Kartu self, Kartu target){
    
  }
}
