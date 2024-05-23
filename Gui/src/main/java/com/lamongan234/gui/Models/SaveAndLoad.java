package com.lamongan234.gui.Models;

public interface SaveAndLoad {
  public void saveState(GameManager g, String fileDir);
  public void loadState(GameManager g, String fileDir);
}
