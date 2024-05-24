package com.lamongan234.gui.Models;

public class ShuffleState implements GameState, Runnable{
  @Override
  public void handle(GameManager g, int turnCount){
    if(g==null){
      return;
    }
    if(turnCount%2==0){
      g.getPlayer2().shuffleKartu();
    }else{
      g.getPlayer1().shuffleKartu();
    }
  }
  @Override
  public void run() {
    handle(null, 0);
  }
}
