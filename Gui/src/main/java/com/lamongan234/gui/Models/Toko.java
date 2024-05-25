package com.lamongan234.gui.Models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Toko {
  private Map<String, Integer> ListQuantity;
  private Map<String, Integer> ListPrice;
  public Toko(){
    ListQuantity = new HashMap<>(9);
    ListQuantity.put("Labu", 0);
    ListQuantity.put("Jagung", 0);
    ListQuantity.put("Stroberi", 0);
    ListQuantity.put("Susu", 0);
    ListQuantity.put("Telur", 0);
    ListQuantity.put("Sirip-Hiu", 0);
    ListQuantity.put("Daging-Domba", 0);
    ListQuantity.put("Daging-Kuda", 0);
    ListQuantity.put("Daging-Beruang",0);

    ListPrice = new HashMap<>(9);
    ListPrice.put("Labu", 500);
    ListPrice.put("Jagung", 150);
    ListPrice.put("Stroberi", 350);
    ListPrice.put("Susu", 100);
    ListPrice.put("Telur", 50);
    ListPrice.put("Sirip-Hiu", 500);
    ListPrice.put("Daging-Domba", 120);
    ListPrice.put("Daging-Kuda", 150);
    ListPrice.put("Daging-Beruang",500);
  }
  public Map<String, Integer> getListQuantity(){
    return ListQuantity;
  }

  public Map<String, Integer> getListPrice(){
    return ListPrice;
  }


  public int getUniqueItemCnt(){
    int i = 0;
    for (Map.Entry<String, Integer> entry : ListQuantity.entrySet()) {
      String key = entry.getKey();
      int value = entry.getValue();

      // Print the key if the value is greater than 0
      if (value > 0) {
        System.out.println(key);
        i++;
      }
    }
    return i;
  }

  public void displayToko(){
    System.out.println("===================== Items =======================");
     for (Map.Entry<String, Integer> entry : ListQuantity.entrySet()) {
        String itemName = entry.getKey();
        Integer itemQuantity = entry.getValue();
        System.out.println(itemName + " " + itemQuantity);
     }
  }

  public void AddKartu(Product card){
      ListQuantity.put(card.getName(), ListQuantity.get(card.getName()) +1);
  }

  public void jual(Product produk, Player p){
    if(produk.getprice()<=p.getUang()){
      p.addToActiveDeck(produk);
      ListQuantity.put(produk.getName(), ListQuantity.get(produk.getName()) - 1);
      p.addUang(produk.getprice()*-1);
    }else{
      System.out.println("Uang tidak cukup!");
    }
  }

  public void beli(Product p, Player pl){
    pl.addUang(p.getprice());
    AddKartu(p);
    pl.removeFromActiveDeck(p);
  }
}
