package com.lamongan234.gui.Models;

public class SeranganBeruang implements GameState, Runnable{
  public void handle(GameManager g, int turnCount){
    if(g==null){
      return;
    }
    //Implementation here
    if(turnCount%2==0){
      g.getPlayer2().SeranganBeruang();
    }else{
      g.getPlayer1().SeranganBeruang();
    }
  }
  @Override
  public void run() {
    handle(null, 0);
  }
    

}
