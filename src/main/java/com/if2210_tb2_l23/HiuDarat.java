package com.if2210_tb2_l23;

public class HiuDarat extends Karnivora {
  public HiuDarat(){
    super(0, 20);
  }
  public Kartu panenKartu(){
    if(isSiapPanen()){
      System.out.println("Hiu darat dipanen!");
      return new Kartu("sirip hiu", new HiuDarat());
    }else{
      System.out.println("Hiu darat belum bisa dipanen!");
    }
    return null;
  }
  public void gunakanItem(Kartu self, Kartu target){
    
  }
}
