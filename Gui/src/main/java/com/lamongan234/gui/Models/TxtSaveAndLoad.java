
package com.lamongan234.gui.Models;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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
        if (entry.getValue() > 0) {
          bw.write(entry.getKey().toUpperCase().replace("-", "_") + " " + entry.getValue());
          bw.newLine();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    savePlayerState(g.getPlayer1(), player1);
    savePlayerState(g.getPlayer2(), player2);
  }
  private void savePlayerState(Player player, String filePath) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
        System.out.println("Saving player state to: " + filePath);
        bw.write(String.valueOf(player.getUang()));
        bw.newLine();
        bw.write(String.valueOf(player.sizeDeck()));
        bw.newLine();
        
        // Debug print
        System.out.println("Player uang: " + player.getUang());
        System.out.println("Player deck size: " + player.sizeDeck());

        // Save deck state
        // for (Kartu kartu : player.getDeck()) {
        //     bw.write(kartu.getName());
        //     bw.newLine();
        // }
        // Save active deck state
        bw.write(String.valueOf(player.filledSlotActiveDeck()));
        bw.newLine();
        
        // Debug print
        System.out.println("Player filled active deck slots: " + player.filledSlotActiveDeck());
        System.out.println("+++printladang");
        player.printLadang();
        System.out.println("+++printladang");

        for (int i = 0; i < player.sizeActiveDeck(); i++) {
            System.out.println("iterasi ke: " + i);
            if (player.getActiveDeck()[i] != null) {
                char rowChar = (char) ('A' + i);
                bw.write(rowChar + "01" + " " + player.getActiveDeck()[i].getName().toUpperCase().replace(" ", "_"));
                System.out.println("rowChar: " + rowChar + "01" + " " + player.getActiveDeck()[i].getName().toUpperCase().replace(" ", "_"));
                bw.newLine();

                // Debug print
                System.out.println("Active deck card: " + player.getActiveDeck()[i].getName());
            }
        }
        // Save ladang state
        bw.write(String.valueOf(player.jumlahSlotLadangTerisi()));
        bw.newLine();
        
        // Debug print
        System.out.println("Player filled ladang slots: " + player.jumlahSlotLadangTerisi());

        for (int i = 0; i < player.getLadang().length; i++) {
            if (player.getLadang()[i] != null) {
                int row = i % 5;
                int col = i /5 ;
                char colNumber = (char) ('A' + row);
                String rowLetter = String.format("%02d", col + 1);
                bw.write(colNumber + rowLetter + " " + player.getLadang()[i].getName().toUpperCase().replace(" ", "_") + " " +
                        player.getLadang()[i].getUmurOrBerat() + " " + player.getLadang()[i].getListAppliedItem().size());
                for (Map.Entry<String, Integer> entry : player.getLadang()[i].getListAppliedItem().entrySet()) {
                    bw.write(" " + entry.getKey().toUpperCase().replace(" ", "_"));
                }
                bw.newLine();
                
                // Debug print
                System.out.println("Ladang card: " + player.getLadang()[i].getName());
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
            int col = position.charAt(0) - 'A';
            int row = Integer.parseInt(position.substring(1)) - 1;
            System.out.println("coordinates" + row + " " + col);
            int pos = row*5 + col;
            String itemName = parts[1];
            int umur_or_berat = Integer.parseInt(parts[2]);
            int appliedItems = Integer.parseInt(parts[3]);
            Kartu card = DictKartu.get(itemName);
            if(card instanceof Hewan){
              Hewan hewan = (Hewan) card;
              hewan.setBeratOrUmur(umur_or_berat);
              for(int i = 0; i< appliedItems; i++){
                Item item = (Item) DictKartu.get(parts[4+i]);
                hewan.AppliedItem(item);
              }
              System.out.println("flag1");
              p.setLadang(hewan, pos);
            }else{
              Tanaman tanaman = (Tanaman) card;
              tanaman.setBeratOrUmur(umur_or_berat);
              for(int i = 0; i< appliedItems; i++){
                Item item = (Item) DictKartu.get(parts[4+i]);
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
            int col = position.charAt(0) - 'A';
            int row = Integer.parseInt(position.substring(1)) - 1;
            int pos = row*5+col;
            String itemName = parts[1];
            int umur_or_berat = Integer.parseInt(parts[2]);
            int appliedItems = Integer.parseInt(parts[3]);
            Kartu card = DictKartu.get(itemName);
            if(card instanceof Hewan){
              Hewan hewan = (Hewan) card;
              hewan.setBeratOrUmur(umur_or_berat);
              for(int i = 0; i< appliedItems; i++){
                Item item = (Item) DictKartu.get(parts[4+i]);
                hewan.AppliedItem(item);
              }
              p.setLadang(hewan, pos);
            }else{
              Tanaman tanaman = (Tanaman) card;
              tanaman.setBeratOrUmur(umur_or_berat);
              for(int i = 0; i< appliedItems; i++){
                Item item = (Item) DictKartu.get(parts[4+i]);
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
