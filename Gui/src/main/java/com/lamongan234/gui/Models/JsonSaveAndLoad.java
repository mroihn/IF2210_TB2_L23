package com.lamongan234.gui.Models;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

public class JsonSaveAndLoad implements SaveAndLoad {
    private Map<String, Kartu> DictKartu;

    public JsonSaveAndLoad() {
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
        String gamestate = fileDir + "\\gamestate.json";
        String player1 = fileDir + "\\player1.json";
        String player2 = fileDir + "\\player2.json";

        saveGameState(g, gamestate);
        savePlayerState(g.getPlayer1(), player1);
        savePlayerState(g.getPlayer2(), player2);
    }

    private void saveGameState(GameManager g, String filePath) {
        JSONObject gameStateJson = new JSONObject();

        gameStateJson.put("Turn", g.getTurn());

        JSONObject tokoJson = new JSONObject();
        tokoJson.put("uniqueItemCount", g.getToko().getUniqueItemCnt());

        JSONArray itemsJson = new JSONArray();
        for (Map.Entry<String, Integer> entry : g.getToko().getListQuantity().entrySet()) {
            JSONObject itemJson = new JSONObject();
            itemJson.put("name", entry.getKey().toUpperCase().replace(" ", "_"));
            itemJson.put("quantity", entry.getValue());
            itemsJson.put(itemJson);
        }
        tokoJson.put("items", itemsJson);

        gameStateJson.put("Toko", tokoJson);

        try {
            Files.write(Paths.get(filePath), gameStateJson.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void savePlayerState(Player player, String filePath) {
        JSONObject playerJson = new JSONObject();

        playerJson.put("Uang", player.getUang());
        playerJson.put("DeckSize", player.sizeDeck());

        JSONObject activeDeckJson = new JSONObject();
        activeDeckJson.put("count", player.filledSlotActiveDeck());

        JSONArray cardsJson = new JSONArray();
        for (int i = 0; i < player.sizeActiveDeck(); i++) {
            if (player.getActiveDeck()[i] != null) {
                JSONObject cardJson = new JSONObject();
                cardJson.put("position", String.valueOf((char) ('A' + i)) + "01");
                cardJson.put("name", player.getActiveDeck()[i].getName().toUpperCase().replace(" ", "_"));
                cardsJson.put(cardJson);
            }
        }
        activeDeckJson.put("cards", cardsJson);

        playerJson.put("ActiveDeck", activeDeckJson);

        JSONObject ladangJson = new JSONObject();
        ladangJson.put("count", player.jumlahSlotLadangTerisi());

        JSONArray ladangSlotsJson = new JSONArray();
        for (int i = 0; i < player.getLadang().length; i++) {
            if (player.getLadang()[i] != null) {
                JSONObject ladangSlotJson = getJsonObject(player, i);

                ladangSlotsJson.put(ladangSlotJson);
            }
        }
        ladangJson.put("slots", ladangSlotsJson);

        playerJson.put("Ladang", ladangJson);

        try {
            Files.write(Paths.get(filePath), playerJson.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static JSONObject getJsonObject(Player player, int i) {
        JSONObject ladangSlotJson = new JSONObject();
        ladangSlotJson.put("position", String.valueOf((char) ('A' + (i / 5))) + String.format("%02d", (i / 5) + 1));
        ladangSlotJson.put("umurOrBerat", player.getLadang()[i].getUmurOrBerat());
        ladangSlotJson.put("name", player.getLadang()[i].getName().toUpperCase().replace(" ", "_"));

        JSONArray appliedItemsJson = new JSONArray();
        for (Map.Entry<String, Integer> entry : player.getLadang()[i].getListAppliedItem().entrySet()) {
            JSONObject appliedItemJson = new JSONObject();
            appliedItemJson.put("name", entry.getKey().toUpperCase().replace(" ", "_"));
            appliedItemsJson.put(appliedItemJson);
        }
        ladangSlotJson.put("appliedItems", appliedItemsJson);
        return ladangSlotJson;
    }

    public void loadState(GameManager g, String fileDir) {
        fileDir = "src\\main\\java\\com\\lamongan234\\gui\\Models\\" + fileDir;
        String gamestate = fileDir + "\\gamestate.json";
        String player1 = fileDir + "\\player1.json";
        String player2 = fileDir + "\\player2.json";

        loadGameState(g, gamestate);
        loadPlayerState(g, player1, true);
        loadPlayerState(g, player2, false);
    }

    private void loadGameState(GameManager g, String filePath) {
        try {
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONObject gameStateJson = new JSONObject(content);

            int turnCount = gameStateJson.getInt("Turn");
            g.setTurn(turnCount);

            JSONObject tokoJson = gameStateJson.getJSONObject("Toko");
            int uniqueItemCount = tokoJson.getInt("uniqueItemCount");
            Toko toko = new Toko();

            JSONArray itemsJson = tokoJson.getJSONArray("items");
            for (int i = 0; i < itemsJson.length(); i++) {
                JSONObject itemJson = itemsJson.getJSONObject(i);
                String itemName = itemJson.getString("name");
                int itemQuantity = itemJson.getInt("quantity");

                for (int j = 0; j < itemQuantity; j++) {
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
            String content = new String(Files.readAllBytes(Paths.get(filePath)));
            JSONObject playerJson = new JSONObject(content);

            Player player = new Player();

            int uang = playerJson.getInt("Uang");
            player.setUang(uang);

            int deckSize = playerJson.getInt("DeckSize");
            player.generateRandomCards(deckSize); // Assuming this generates a random deck

            JSONObject activeDeckJson = playerJson.getJSONObject("ActiveDeck");
            JSONArray cardsJson = activeDeckJson.getJSONArray("cards");
            for (int i = 0; i < cardsJson.length(); i++) {
                JSONObject cardJson = cardsJson.getJSONObject(i);
                String itemName = cardJson.getString("name");
                Kartu card = DictKartu.get(itemName);
                player.addToActiveDeck(card);
            }

            JSONObject ladangJson = playerJson.getJSONObject("Ladang");
            JSONArray ladangSlotsJson = ladangJson.getJSONArray("slots");
            for (int i = 0; i < ladangSlotsJson.length(); i++) {
                JSONObject ladangSlotJson = ladangSlotsJson.getJSONObject(i);
                String position = ladangSlotJson.getString("position");
                String itemName = ladangSlotJson.getString("name");
                int umurOrBerat = ladangSlotJson.getInt("umurOrBerat");
                JSONArray appliedItemsJson = ladangSlotJson.getJSONArray("appliedItems");

                int col = position.charAt(0) - 'A';
                int row = Integer.parseInt(position.substring(1)) - 1;
                int pos = row * 5 + col;

                Kartu card = DictKartu.get(itemName);
                if (card instanceof Hewan) {
                    Hewan hewan = (Hewan) card;
                    hewan.setBeratOrUmur(umurOrBerat);
                    for (int j = 0; j < appliedItemsJson.length(); j++) {
                        String appliedItemName = appliedItemsJson.getJSONObject(j).getString("name");
                        Item item = (Item) DictKartu.get(appliedItemName);
                        hewan.AppliedItem(item);
                    }
                    player.setLadang(hewan, pos);
                } else if (card instanceof Tanaman) {
                    Tanaman tanaman = (Tanaman) card;
                    tanaman.setBeratOrUmur(umurOrBerat);
                    for (int j = 0; j < appliedItemsJson.length(); j++) {
                        String appliedItemName = appliedItemsJson.getJSONObject(j).getString("name");
                        Item item = (Item) DictKartu.get(appliedItemName);
                        tanaman.AppliedItem(item);
                    }
                    player.setLadang(tanaman, pos);
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
