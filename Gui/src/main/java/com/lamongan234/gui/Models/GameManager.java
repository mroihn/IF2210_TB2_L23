package com.lamongan234.gui.Models;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

public class GameManager {
  private Player p1;
  private Player p2;
  private int turnCount;
  private Toko toko;
  private GameState state;


  public GameManager(){
    this.p1 = new Player();
    this.p2 = new Player();
    this.toko = new Toko();
    this.turnCount = 0;

  }

  public void setTurn(int turn){
    turnCount = turn;
  }
  public int getTurn(){
    return turnCount;
  }
  public Player getPlayer1(){
    return p1;
  }
  public void setPlayer1(Player p){
    this.p1 = p;
  }
  public Player getPlayer2(){
    return p2;
  }
  public void setPlayer2(Player p){
    this.p2 = p;
  }
  public Toko getToko(){
    return toko;
  }
  public void setToko(Toko t){
    this.toko = t;
  }

  public void SeranganBeruang(){
    state = new SeranganBeruang();
    state.handle(this, turnCount);
  }


  public void saveState(String jarPath, String pluginClassName){
  File jarFile = new File(jarPath);
  try (URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{jarFile.toURI().toURL()})) {
      Class<?> pluginClass = urlClassLoader.loadClass(pluginClassName);
      SaveAndLoad load = (SaveAndLoad) pluginClass.getDeclaredConstructor().newInstance();
      load.saveState(this, "tes");
    } catch (Exception e) {
        e.printStackTrace();
    }
  }


  public SaveAndLoad SaveAndLoadPlugin(String jarPath, String pluginClassName){
    File jarFile = new File(jarPath);
    try (URLClassLoader urlClassLoader = new URLClassLoader(new URL[]{jarFile.toURI().toURL()})) {
        Class<?> pluginClass = urlClassLoader.loadClass(pluginClassName);
        SaveAndLoad saveAndLoad = (SaveAndLoad) pluginClass.getDeclaredConstructor().newInstance();
        return saveAndLoad;
    }  catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
}