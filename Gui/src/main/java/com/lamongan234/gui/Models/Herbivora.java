package com.lamongan234.gui.Models;

public abstract class Herbivora extends Hewan {
  public Herbivora(String nama, int berat, int weight_to_harvest){
    super(nama, berat,weight_to_harvest);
  }
  public void makan(Product makanan){
    if(makanan instanceof Nabati){
      System.out.println("Herbivora makan " + makanan.getName());
      Nabati vegan = (Nabati) makanan;
      berat_or_umur += vegan.getaddedweight();
    }else{
      System.out.println("Makanan tidak cocok");
    }
  }
}
