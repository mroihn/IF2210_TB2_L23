package com.lamongan234.gui.Models;

import java.util.ArrayList;
import java.util.List;

public class Toko {
  private List<Product> ListProduk;
  public Toko(){
    ListProduk = new ArrayList<>();
  }
  public Toko(List<Product> p){
    ListProduk = p;
  }

  public void displayToko(){
    for (Product p : ListProduk){
      System.out.println("===================== Items =======================");
      System.out.println(p.getName());
    }
  }

  public void AddKartu(Product card){
    ListProduk.add(card);
  }

  public void jual(Product produk, Player p){
    if(produk.getprice()<=p.getUang()){
      p.addToActiveDeck(produk);
      ListProduk.remove(produk);
      p.addUang(produk.getprice()*-1);
    }else{
      System.out.println("Uang tidak cukup!");
    }
  }

  public void beli(Product p, Player pl){
    pl.addUang(p.getprice());
    ListProduk.add(p);
    pl.removeFromActiveDeck(p);
  }

}
