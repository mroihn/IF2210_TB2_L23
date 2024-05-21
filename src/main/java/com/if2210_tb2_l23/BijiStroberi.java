package com.if2210_tb2_l23;

public class BijiStroberi extends Tanaman{
  public BijiStroberi(){
    super(0, 4);
  }
  public Kartu panenKartu(){
    if(isSiapPanen()){
      System.out.println("Biji Stroberi berhasil dipanen");
      return new Kartu("Stroberi", new Nabati(30, 10));
    }else{
      System.out.println("Umur belum cukup");
      return null;
    }
  }
}
