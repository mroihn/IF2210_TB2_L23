package com.lamongan234.gui.Models;

public abstract class Karnivora extends Hewan {
  public Karnivora(String nama, int berat, int weight_to_harvest){
    super(nama, berat,weight_to_harvest);
  }
  public void makan(Product makanan){
    if(makanan instanceof Hewani){
      System.out.println("Karnivora makan " + makanan.getName());
      berat_or_umur += makanan.getaddedweight();
    }else{
      System.out.println("Makanan tidak cocok");
    }
  }
}
