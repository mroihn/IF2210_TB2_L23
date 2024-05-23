package com.lamongan234.gui.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Toko {
  private List<Product> ListProduk;
  private Map<String, Integer> ListHarga;
  public Toko(){
    ListProduk = new ArrayList<>();
    ListHarga = new HashMap<>();
  }
  public Toko(List<Product> p){
    ListProduk = p;
  }

  public void displayToko(){
    System.out.println("===================== Items =======================");
     for (Map.Entry<String, Integer> entry : ListHarga.entrySet()) {
        String itemName = entry.getKey();
        Integer itemQuantity = entry.getValue();
        System.out.println(itemName + " " + itemQuantity);
     }
  }

  public void AddKartu(Product card){
    ListProduk.add(card);
    if(ListHarga.get(card.getName()) == null){
      ListHarga.put(card.getName(), 1);
    }else{
      ListHarga.put(card.getName(), ListHarga.get(card.getName()) +1);
    }
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
