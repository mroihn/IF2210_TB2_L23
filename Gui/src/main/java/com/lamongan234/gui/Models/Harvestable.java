package com.lamongan234.gui.Models;
import java.util.Map;

public abstract class Harvestable extends Kartu{
  public Harvestable(String nama){
    super(nama);
  }
  public abstract int getUmurOrBerat();
  public abstract Map<String, Integer> getListAppliedItem();
  public abstract Product panenKartu();
  public abstract void AppliedItem(Item item);
}
