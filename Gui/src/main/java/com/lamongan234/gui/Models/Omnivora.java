package com.lamongan234.gui.Models;

public abstract class Omnivora extends Hewan {
  public Omnivora(String nama, int berat, int weight_to_harvest){
    super(nama, berat,weight_to_harvest);
  }
  public void makan(Product makanan){
    System.out.println("Omnivora makan " + makanan.getName());
    berat_or_umur += makanan.getaddedweight();
  }
}
