package com.lamongan234.gui.Models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TxtSaveAndLoad implements SaveAndLoad {
  private Map<String, String> DictKartu;

  public TxtSaveAndLoad() {
    DictKartu = new HashMap<>();
    DictKartu.put("HIU_DARAT", "com.lamongan234.gui.Models.HiuDarat");
    DictKartu.put("SAPI", "com.lamongan234.gui.Models.Sapi");
    DictKartu.put("DOMBA", "com.lamongan234.gui.Models.Domba");
    DictKartu.put("KUDA", "com.lamongan234.gui.Models.Kuda");
    DictKartu.put("AYAM", "com.lamongan234.gui.Models.Ayam");
    DictKartu.put("BERUANG", "com.lamongan234.gui.Models.Beruang");
    DictKartu.put("LABU", "com.lamongan234.gui.Models.Labu");
    DictKartu.put("JAGUNG", "com.lamongan234.gui.Models.Jagung");
    DictKartu.put("STROBERI", "com.lamongan234.gui.Models.Stroberi");
    DictKartu.put("SUSU", "com.lamongan234.gui.Models.Susu");
    DictKartu.put("TELUR", "com.lamongan234.gui.Models.Telur");
    DictKartu.put("SIRIP_HIU", "com.lamongan234.gui.Models.SiripHiu");
    DictKartu.put("DAGING_DOMBA", "com.lamongan234.gui.Models.DagingDomba");
    DictKartu.put("DAGING_KUDA", "com.lamongan234.gui.Models.DagingKuda");
    DictKartu.put("DAGING_BERUANG", "com.lamongan234.gui.Models.DagingBeruang");
    DictKartu.put("BIJI_LABU", "com.lamongan234.gui.Models.BijiLabu");
    DictKartu.put("BIJI_JAGUNG", "com.lamongan234.gui.Models.BijiJagung");
    DictKartu.put("BIJI_STROBERI", "com.lamongan234.gui.Models.BijiStroberi");
    DictKartu.put("ACCELERATE", "com.lamongan234.gui.Models.Accelerate");
    DictKartu.put("DELAY", "com.lamongan234.gui.Models.Delay");
    DictKartu.put("DESTROY", "com.lamongan234.gui.Models.Destroy");
    DictKartu.put("INSTANT_HARVEST", "com.lamongan234.gui.Models.InstantHarvest");
    DictKartu.put("PROTECT", "com.lamongan234.gui.Models.Protect");
    DictKartu.put("TRAP", "com.lamongan234.gui.Models.Trap");    
  }

  private Kartu createInstance(String className) {
    try {
      Class<?> cls = Class.forName(className);
      return (Kartu) cls.getDeclaredConstructor().newInstance();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  public void saveState(GameManager g, String fileDir) {
    fileDir = "src\\main\\java\\com\\lamongan234\\gui\\Models\\" + fileDir;
    String gamestate = fileDir + "\\gamestate.txt";
    String player1 = fileDir + "\\player1.txt";
    String player2 = fileDir + "\\player2.txt";

    try (BufferedWriter bw = new BufferedWriter(new FileWriter(gamestate))) {
      bw.write(String.valueOf(g.getTurn()));
      bw.newLine();
      bw.write(String.valueOf(g.getToko().getUniqueItemCnt()));
      bw.newLine();
      for (Map.Entry<String, Integer> entry : g.getToko().getListQuantity().entrySet()) {
        bw.write(entry.getKey().toUpperCase().replace(" ", "_") + " " + entry.getValue());
        bw.newLine();
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    savePlayerState(g.getPlayer1(), player1);
    savePlayerState(g.getPlayer2(), player2);
  }

  private void savePlayerState(Player player, String filePath) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
        bw.write(String.valueOf(player.getUang()));
        bw.newLine();
        bw.write(String.valueOf(player.sizeDeck()));
        bw.newLine();
        // Save deck state
        // for (Kartu kartu : player.getDeck()) {
        //     bw.write(kartu.getName());
        //     bw.newLine();
        // }
        // Save active deck state
        bw.write(String.valueOf(player.filledSlotActiveDeck()));
        bw.newLine();
        for (int i = 0; i<player.sizeActiveDeck(); i++) {
          if(player.getActiveDeck()[i] != null){
            char rowChar = (char) ('A' + i);
            bw.write(rowChar + "01" + " " + player.getActiveDeck()[i].getName().toUpperCase().replace(" ", "_"));
            bw.newLine();
          }
        }
        // Save ladang state
        bw.write(String.valueOf(player.jumlahSlotLadangTerisi()));
        bw.newLine();
        for (int i =0; i < player.getLadang().length; i ++) {
          if(player.getLadang()[i] != null){
            int row = i/5;
            // int col = i - row;
            char colNumber = (char) ('A' + row);
  
            // Format column index to start from 1
            String rowLetter = String.format("%02d", row + 1);
            bw.write(colNumber + rowLetter + " " + player.getLadang()[i].getName().toUpperCase().replace(" ", "_") + " " +
            player.getLadang()[i].getUmurOrBerat() + " " + player.getLadang()[i].getListAppliedItem().size());
            for (Map.Entry<String, Integer> entry : player.getLadang()[i].getListAppliedItem().entrySet()) {
              bw.write(" " + entry.getKey().toUpperCase().replace(" ", "_"));
            }
            bw.newLine();
          }
        }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void loadState(GameManager g, String fileDir){
    fileDir = "src\\main\\java\\com\\lamongan234\\gui\\Models\\" + fileDir;
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
              Product p = (Product) createInstance(DictKartu.get(itemName));
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
            Kartu card = createInstance(DictKartu.get(itemName));
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
            int col = position.charAt(0) - 'A';
            int row = Integer.parseInt(position.substring(1)) - 1;
            int pos = row*5 + col;
            String itemName = parts[1];
            int umur_or_berat = Integer.parseInt(parts[2]);
            int appliedItems = Integer.parseInt(parts[3]);
            Kartu card = createInstance(DictKartu.get(itemName));
            if(card instanceof Hewan){
              Hewan hewan = (Hewan) card;
              hewan.setBeratOrUmur(umur_or_berat);
              for(int i = 0; i< appliedItems; i++){
                Item item = (Item) createInstance(DictKartu.get(parts[4+i]));
                hewan.AppliedItem(item);
              }
              p.setLadang(hewan, pos);
            }else{
              Tanaman tanaman = (Tanaman) card;
              tanaman.setBeratOrUmur(umur_or_berat);
              for(int i = 0; i< appliedItems; i++){
                Item item = (Item) createInstance(DictKartu.get(parts[4+i]));
                tanaman.AppliedItem(item);
              }
              p.setLadang(tanaman, pos);
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
            Kartu card = createInstance(DictKartu.get(itemName));
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
            int col = position.charAt(0) - 'A';
            int row = Integer.parseInt(position.substring(1)) - 1;
            int pos = row*5+col;
            String itemName = parts[1];
            int umur_or_berat = Integer.parseInt(parts[2]);
            int appliedItems = Integer.parseInt(parts[3]);
            Kartu card = createInstance(DictKartu.get(itemName));
            if(card instanceof Hewan){
              Hewan hewan = (Hewan) card;
              hewan.setBeratOrUmur(umur_or_berat);
              for(int i = 0; i< appliedItems; i++){
                Item item = (Item) createInstance(DictKartu.get(parts[4+i]));
                hewan.AppliedItem(item);
              }
              p.setLadang(hewan, pos);
            }else{
              Tanaman tanaman = (Tanaman) card;
              tanaman.setBeratOrUmur(umur_or_berat);
              for(int i = 0; i< appliedItems; i++){
                Item item = (Item) createInstance(DictKartu.get(parts[4+i]));
                tanaman.AppliedItem(item);
              }
              p.setLadang(tanaman, pos);
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
