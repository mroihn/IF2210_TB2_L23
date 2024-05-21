package com.if2210_tb2_l23;

public class Kuda extends Herbivora {
  public Kuda(){
    super(0, 14);
  }
  public Kartu panenKartu(){
    if(isSiapPanen()){
      System.out.println("Kuda dipanen!");
      return new Kartu("daging Kuda", new Kuda());
    }else{
      System.out.println("Kuda belum bisa dipanen!");
    }
    return null;
  }
  public void gunakanItem(Kartu self, Kartu target){
    
  }
}
