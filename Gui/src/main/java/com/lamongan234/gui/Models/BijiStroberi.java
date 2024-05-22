package com.lamongan234.gui.Models;

public class BijiStroberi extends Tanaman{
  public BijiStroberi(){
    super("Biji Stroberi", 0, 4);
  }
  public Product panenKartu(){
    if(isSiapPanen()){
      System.out.println("Biji Stroberi berhasil dipanen");
      return new Tempe();
    }else{
      System.out.println("Umur belum cukup");
      return null;
    }
  }
}
