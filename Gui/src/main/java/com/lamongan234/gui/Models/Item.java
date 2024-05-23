package com.lamongan234.gui.Models;

public abstract class Item extends Kartu{
  public Item(String nama){
    super(nama);
  }
  public abstract void Efek(Harvestable target);
}
