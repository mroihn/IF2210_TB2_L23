package com.lamongan234.gui.Models;

public abstract class Harvestable extends Kartu{
  public Harvestable(String nama){
    super(nama);
  }
  public abstract Product panenKartu();
  public abstract void AppliedItem(Item item);
}
