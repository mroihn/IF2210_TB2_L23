package com.lamongan234.gui.Models;
public class GameManager {
  private Player p1;
  private Player p2;
  private int turnCount;
  private Toko toko;

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



}
package com.lamongan234.gui.Models;

// import java.util.Random;

public class GameManager {
    // //return koordinat dan p X L
    // public  seranganBeruang(Player p){
    //     // x,y adalah titik pangkal kiri atas dari serangan beruang
    //     int x;
    //     int y;

    //     //berapa kolom ke kanan dan berapa baris ke bawah yang mau diserang dari x,y
    //     int randomRow;
    //     int randomColumn;

    //     Random random = new Random();
    //     do{
    //         x = random.nextInt(5); //bisa 0,1,2,3,4
    //         randomColumn = random.nextInt(5-x); //bisa 0,1,2,3,4
    //         //misal x = 2, randomColumn bisa 0,1,2

            
    //         y = random.nextInt(4); //0,1,2,3
    //         randomRow = random.nextInt(4-y); //0,1,2,3
    //     } while (randomColumn * randomRow > 6);

    //     return 
    // }
}

