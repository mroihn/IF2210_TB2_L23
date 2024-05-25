package com.lamongan234.gui.Models;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.representer.Representer;

public class YAMLSaveAndLoad implements SaveAndLoad {
    private Map<String, Kartu> DictKartu;

    public YAMLSaveAndLoad() {
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
        fileDir = "src/main/java/com/lamongan234/gui/Models/" + fileDir;
        String gamestate = fileDir + "/gamestate.yaml";
        String player1 = fileDir + "/player1.yaml";
        String player2 = fileDir + "/player2.yaml";

        saveGameState(g, gamestate);
        savePlayerState(g.getPlayer1(), player1);
        savePlayerState(g.getPlayer2(), player2);
    }

    private void saveGameState(GameManager g, String filePath) {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("Turn", g.getTurn());

            Map<String, Object> tokoData = new HashMap<>();
            tokoData.put("uniqueItemCount", g.getToko().getUniqueItemCnt());

            Map<String, Integer> items = g.getToko().getListQuantity();
            for (Map.Entry<String, Integer> entry : items.entrySet()) {
                tokoData.put(entry.getKey().toUpperCase().replace(" ", "_"), entry.getValue());
            }
            data.put("Toko", tokoData);

            DumperOptions options = new DumperOptions();
            options.setIndent(2);
            options.setPrettyFlow(true);
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            Yaml yaml = new Yaml(options);

            try (FileWriter writer = new FileWriter(new File(filePath))) {
                yaml.dump(data, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void savePlayerState(Player player, String filePath) {
        try {
            Map<String, Object> data = new HashMap<>();
            data.put("Uang", player.getUang());
            data.put("DeckSize", player.sizeDeck());

            Map<String, Object> activeDeckData = new HashMap<>();
            activeDeckData.put("count", player.filledSlotActiveDeck());

            for (int i = 0; i < player.sizeActiveDeck(); i++) {
                if (player.getActiveDeck()[i] != null) {
                    activeDeckData.put(String.valueOf((char) ('A' + i)) + "01", player.getActiveDeck()[i].getName().toUpperCase().replace(" ", "_"));
                }
            }
            data.put("ActiveDeck", activeDeckData);

            Map<String, Object> ladangData = new HashMap<>();
            ladangData.put("count", player.jumlahSlotLadangTerisi());

            for (int i = 0; i < player.getLadang().length; i++) {
                if (player.getLadang()[i] != null) {
                    Map<String, Object> ladangSlotData = new HashMap<>();
                    ladangSlotData.put("umurOrBerat", player.getLadang()[i].getUmurOrBerat());
                    ladangSlotData.put("name", player.getLadang()[i].getName().toUpperCase().replace(" ", "_"));

                    for (Map.Entry<String, Integer> entry : player.getLadang()[i].getListAppliedItem().entrySet()) {
                        ladangSlotData.put(entry.getKey().toUpperCase().replace(" ", "_"), entry.getValue());
                    }
                    ladangData.put(String.valueOf((char) ('A' + (i / 5))) + String.format("%02d", (i / 5) + 1), ladangSlotData);
                }
            }
            data.put("Ladang", ladangData);

            DumperOptions options = new DumperOptions();
            options.setIndent(2);
            options.setPrettyFlow(true);
            options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
            Yaml yaml = new Yaml(options);

            try (FileWriter writer = new FileWriter(new File(filePath))) {
                yaml.dump(data, writer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadState(GameManager g, String fileDir) {
        fileDir = "src/main/java/com/lamongan234/gui/Models/" + fileDir;
        String gamestate = fileDir + "/gamestate.yaml";
        String player1 = fileDir + "/player1.yaml";
        String player2 = fileDir + "/player2.yaml";

        loadGameState(g, gamestate);
        loadPlayerState(g, player1, true);
        loadPlayerState(g, player2, false);
    }

    private void loadGameState(GameManager g, String filePath) {
        try {
            Yaml yaml = new Yaml(new Constructor(Map.class));
            FileInputStream fis = new FileInputStream(new File(filePath));
            Map<String, Object> data = yaml.load(fis);

            int turnCount = (int) data.get("Turn");
            g.setTurn(turnCount);

            Map<String, Object> tokoData = (Map<String, Object>) data.get("Toko");
            int uniqueItemCount = (int) tokoData.get("uniqueItemCount");
            Toko toko = new Toko();

            tokoData.remove("uniqueItemCount");
            for (Map.Entry<String, Object> entry : tokoData.entrySet()) {
                String itemName = entry.getKey();
                int itemQuantity = (int) entry.getValue();

                for (int i = 0; i < itemQuantity; i++) {
                    Product p = (Product) DictKartu.get(itemName);
                    toko.AddKartu(p);
                }
            }
            g.setToko(toko);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadPlayerState(GameManager g, String filePath, boolean isPlayer1) {
        try {
            Yaml yaml = new Yaml(new Constructor(Map.class));
            FileInputStream fis = new FileInputStream(new File(filePath));
            Map<String, Object> data = yaml.load(fis);

            Player player = new Player();

            int uang = (int) data.get("Uang");
            player.setUang(uang);

            int deckSize = (int) data.get("DeckSize");
            player.generateRandomCards(deckSize); // Assuming this generates a random deck

            Map<String, Object> activeDeckData = (Map<String, Object>) data.get("ActiveDeck");
            for (Map.Entry<String, Object> entry : activeDeckData.entrySet()) {
                if (!entry.getKey().equals("count")) {
                    String itemName = (String) entry.getValue();
                    Kartu card = DictKartu.get(itemName);
                    player.addToActiveDeck(card);
                }
            }

            Map<String, Object> ladangData = (Map<String, Object>) data.get("Ladang");
            for (Map.Entry<String, Object> entry : ladangData.entrySet()) {
                if (!entry.getKey().equals("count")) {
                    String position = entry.getKey();
                    Map<String, Object> ladangSlotData = (Map<String, Object>) entry.getValue();
                    String itemName = (String) ladangSlotData.get("name");
                    int umurOrBerat = (int) ladangSlotData.get("umurOrBerat");

                    int col = position.charAt(0) - 'A';
                    int row = Integer.parseInt(position.substring(1)) - 1;
                    int pos = row * 5 + col;

                    Kartu card = DictKartu.get(itemName);
                    if (card instanceof Hewan) {
                        Hewan hewan = (Hewan) card;
                        hewan.setBeratOrUmur(umurOrBerat);
                        for (Map.Entry<String, Object> appliedItemEntry : ladangSlotData.entrySet()) {
                            if (!appliedItemEntry.getKey().equals("umurOrBerat") && !appliedItemEntry.getKey().equals("name")) {
                                String appliedItemName = appliedItemEntry.getKey();
                                Item item = (Item) DictKartu.get(appliedItemName);
                                hewan.AppliedItem(item);
                            }
                        }
                        player.setLadang(hewan, pos);
                    } else if (card instanceof Tanaman) {
                        Tanaman tanaman = (Tanaman) card;
                        tanaman.setBeratOrUmur(umurOrBerat);
                        for (Map.Entry<String, Object> appliedItemEntry : ladangSlotData.entrySet()) {
                            if (!appliedItemEntry.getKey().equals("umurOrBerat") && !appliedItemEntry.getKey().equals("name")) {
                                String appliedItemName = appliedItemEntry.getKey();
                                Item item = (Item) DictKartu.get(appliedItemName);
                                tanaman.AppliedItem(item);
                            }
                        }
                        player.setLadang(tanaman, pos);
                    }
                }
            }

            if (isPlayer1) {
                g.setPlayer1(player);
            } else {
                g.setPlayer2(player);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
