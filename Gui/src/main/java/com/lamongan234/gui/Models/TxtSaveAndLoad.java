package com.lamongan234.gui.Models;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TxtSaveAndLoad implements SaveAndLoad {
  private Map<String, Kartu> DictKartu;
  public TxtSaveAndLoad(){
    DictKartu = new HashMap<>();
    DictKartu.put("HIU_DARAT", new HiuDarat());
    DictKartu.put("SAPI", new Sapi());
    DictKartu.put("DOMBA", new Domba());
    DictKartu.put("KUDA", new Kuda());
    DictKartu.put("AYAM", new Ayam());
    DictKartu.put("BERUANG", new Beruang());
    DictKartu.put("LABU", new Labu());
    DictKartu.put("JAGUNG", new Jagung());
    DictKartu.put("STROBERI", new Stroberi());
    DictKartu.put("SUSU", new Susu());
    DictKartu.put("TELUR", new Telur());
    DictKartu.put("SIRIP_HIU", new SiripHiu());
    DictKartu.put("DAGING_DOMBA", new DagingDomba());
    DictKartu.put("DAGING_KUDA", new DagingKuda());
    DictKartu.put("DAGING_BERUANG", new DagingBeruang());
    DictKartu.put("BIJI_LABU", new BijiLabu());
    DictKartu.put("BIJI_JAGUNG", new BijiJagung());
    DictKartu.put("BIJI_STROBERI", new BijiStroberi());
    DictKartu.put("ACCELERATE", new Accelerate());
    DictKartu.put("DELAY", new Delay());
    DictKartu.put("DESTROY", new Destroy());
    DictKartu.put("INSTANT_HARVEST", new InstantHarvest());
    DictKartu.put("PROTECT", new Protect());
    DictKartu.put("TRAP", new Trap());
  }
  public void saveState(GameManager g, String fileDir){}
  public void loadState(GameManager g, String fileDir){
    fileDir = "Gui\\src\\main\\java\\com\\lamongan234\\gui\\Models\\" + fileDir;
    String gamestate = fileDir + "\\gamestate.txt";
    String player1 = fileDir + "\\player1.txt";
    String player2 = fileDir + "\\player2.txt";
    try (BufferedReader br = new BufferedReader(new FileReader(gamestate))) {
      String line;
      int Itemcount;
      int turnCount;
      Toko toko = new Toko();
      turnCount = Integer.parseInt(br.readLine());
      g.setTurn(turnCount);
      Itemcount = Integer.parseInt(br.readLine());
      while (Itemcount>0) {
        line = br.readLine();
        if(line != null){
          String[] parts = line.split(" ");
          if (parts.length == 2) {
            String itemName = parts[0];
            int itemQuantity = Integer.parseInt(parts[1]);
            for(int i=0; i<itemQuantity; i++){
              Product p = (Product) DictKartu.get(itemName);
              toko.AddKartu(p);
            }
          }
        }
        Itemcount--;
      }
      g.setToko(toko);
    } catch (IOException e) {
        e.printStackTrace();
    }
    try (BufferedReader br = new BufferedReader(new FileReader(player1))) {
      String line;
      int uang, deckCount, activeDeckCount, LadangCount;
      Player p = new Player();
      
      uang = Integer.parseInt(br.readLine());
      p.setUang(uang);

      deckCount = Integer.parseInt(br.readLine());
      p.generateRandomCards(deckCount);
      
      activeDeckCount = Integer.parseInt(br.readLine());
      
      
      while (activeDeckCount>0) {
        line = br.readLine();
        if(line != null){
          String[] parts = line.split(" ");
          if (parts.length == 2) {
            String position = parts[0];
            String itemName = parts[1];
            Kartu card = DictKartu.get(itemName);
            p.addToActiveDeck(card);
          }
        }
        activeDeckCount--;
      }
      
      LadangCount = Integer.parseInt(br.readLine());
      while (LadangCount>0) {
        line = br.readLine();
        if(line != null){
          String[] parts = line.split(" ");
          if (parts.length >= 4) {
            String position = parts[0];
            int row = position.charAt(0) - 'A';
            int col = Integer.parseInt(position.substring(1)) - 1;
            System.out.println("coordinates" + row + " " + col);
            String itemName = parts[1];
            int umur_or_berat = Integer.parseInt(parts[2]);
            umur_or_berat = 1;
            int appliedItems = Integer.parseInt(parts[3]);
            Kartu card = DictKartu.get(itemName);
            if(card instanceof Hewan){
              Hewan hewan = (Hewan) card;
              hewan.setBerat(umur_or_berat);
              for(int i = 0; i< appliedItems; i++){
                Item item = (Item) DictKartu.get(parts[4+i]);
                hewan.AppliedItem(item);
              }
              System.out.println("flag1");
              p.setLadang(hewan, row, col);
            }else{
              Tanaman tanaman = (Tanaman) card;
              tanaman.setUmur(umur_or_berat);
              for(int i = 0; i< appliedItems; i++){
                Item item = (Item) DictKartu.get(parts[4+i]);
                tanaman.AppliedItem(item);
              }
              p.setLadang(tanaman, row, col);
            }
          }
        }
        LadangCount--;
      }
      g.setPlayer1(p);
      
    } catch (IOException e) {
        e.printStackTrace();
    }
    try (BufferedReader br = new BufferedReader(new FileReader(player2))) {
      String line;
      int uang, deckCount, activeDeckCount, LadangCount;
      Player p = new Player();
      
      uang = Integer.parseInt(br.readLine());
      p.setUang(uang);

      deckCount = Integer.parseInt(br.readLine());
      p.generateRandomCards(deckCount);
      
      activeDeckCount = Integer.parseInt(br.readLine());
      
      
      while (activeDeckCount>0) {
        line = br.readLine();
        if(line != null){
          String[] parts = line.split(" ");
          if (parts.length == 2) {
            String position = parts[0];
            String itemName = parts[1];
            Kartu card = DictKartu.get(itemName);
            p.addToActiveDeck(card);
          }
        }
        activeDeckCount--;
      }
      
      LadangCount = Integer.parseInt(br.readLine());
      while (LadangCount>0) {
        line = br.readLine();
        if(line != null){
          String[] parts = line.split(" ");
          if (parts.length >= 4) {
            String position = parts[0];
            int row = position.charAt(0) - 'A';
            int col = Integer.parseInt(position.substring(1)) - 1;
            String itemName = parts[1];
            int umur_or_berat = Integer.parseInt(parts[2]);
            int appliedItems = Integer.parseInt(parts[3]);
            Kartu card = DictKartu.get(itemName);
            if(card instanceof Hewan){
              Hewan hewan = (Hewan) card;
              hewan.setBerat(umur_or_berat);
              for(int i = 0; i< appliedItems; i++){
                Item item = (Item) DictKartu.get(parts[4+i]);
                hewan.AppliedItem(item);
              }
              p.setLadang(hewan, row, col);
            }else{
              Tanaman tanaman = (Tanaman) card;
              tanaman.setUmur(umur_or_berat);
              for(int i = 0; i< appliedItems; i++){
                Item item = (Item) DictKartu.get(parts[4+i]);
                tanaman.AppliedItem(item);
              }
              p.setLadang(tanaman, row, col);
            }
          }
        }
        LadangCount--;
      }
      g.setPlayer2(p);
      
    } catch (IOException e) {
        e.printStackTrace();
    }

  }
}