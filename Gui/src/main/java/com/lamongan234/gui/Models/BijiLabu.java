package com.lamongan234.gui.Models;

public class BijiLabu extends Tanaman{
  public BijiLabu(){
    super("Biji Labu", 0, 5);
  }
  public Product panenKartu(){
    if(isSiapPanen()){
      System.out.println("Biji Labu berhasil dipanen");
      return new DagingDomba();
    }else{
      System.out.println("Umur belum cukup");
      return null;
    }
  }
}
