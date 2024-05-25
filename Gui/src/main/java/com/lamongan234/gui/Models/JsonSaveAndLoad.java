//package com.lamongan234.gui.Models;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.JsonArray;
//import com.google.gson.JsonObject;
//
//import java.io.*;
//import java.util.HashMap;
//import java.util.Map;
//
//public class JsonSaveAndLoad implements SaveAndLoad {
//    private Map<String, Kartu> DictKartu;
//    private Gson gson;
//
//    public JsonSaveAndLoad() {
//        DictKartu = new HashMap<>();
//        DictKartu.put("HIU_DARAT", new HiuDarat());
//        DictKartu.put("SAPI", new Sapi());
//        DictKartu.put("DOMBA", new Domba());
//        DictKartu.put("KUDA", new Kuda());
//        DictKartu.put("AYAM", new Ayam());
//        DictKartu.put("BERUANG", new Beruang());
//        DictKartu.put("LABU", new Labu());
//        DictKartu.put("JAGUNG", new Jagung());
//        DictKartu.put("STROBERI", new Stroberi());
//        DictKartu.put("SUSU", new Susu());
//        DictKartu.put("TELUR", new Telur());
//        DictKartu.put("SIRIP_HIU", new SiripHiu());
//        DictKartu.put("DAGING_DOMBA", new DagingDomba());
//        DictKartu.put("DAGING_KUDA", new DagingKuda());
//        DictKartu.put("DAGING_BERUANG", new DagingBeruang());
//        DictKartu.put("BIJI_LABU", new BijiLabu());
//        DictKartu.put("BIJI_JAGUNG", new BijiJagung());
//        DictKartu.put("BIJI_STROBERI", new BijiStroberi());
//        DictKartu.put("ACCELERATE", new Accelerate());
//        DictKartu.put("DELAY", new Delay());
//        DictKartu.put("DESTROY", new Destroy());
//        DictKartu.put("INSTANT_HARVEST", new InstantHarvest());
//        DictKartu.put("PROTECT", new Protect());
//        DictKartu.put("TRAP", new Trap());
//        gson = new GsonBuilder().setPrettyPrinting().create();
//    }
//
//    public void saveState(GameManager g, String fileDir) {
//        fileDir = "src\\main\\java\\com\\lamongan234\\gui\\Models\\" + fileDir;
//        String gamestate = fileDir + "\\gamestate.json";
//        String player1 = fileDir + "\\player1.json";
//        String player2 = fileDir + "\\player2.json";
//
//        JsonObject gameStateJson = new JsonObject();
//        gameStateJson.addProperty("turn", g.getTurn());
//        JsonObject tokoJson = new JsonObject();
//        tokoJson.addProperty("uniqueItemCount", g.getToko().getUniqueItemCnt());
//        JsonArray itemsJsonArray = new JsonArray();
//        for (Map.Entry<String, Integer> entry : g.getToko().getListQuantity().entrySet()) {
//            JsonObject itemJson = new JsonObject();
//            itemJson.addProperty("name", entry.getKey().toUpperCase().replace(" ", "_"));
//            itemJson.addProperty("quantity", entry.getValue());
//            itemsJsonArray.add(itemJson);
//        }
//        tokoJson.add("items", itemsJsonArray);
//        gameStateJson.add("toko", tokoJson);
//
//        try (Writer writer = new FileWriter(gamestate)) {
//            gson.toJson(gameStateJson, writer);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        savePlayerState(g.getPlayer1(), player1);
//        savePlayerState(g.getPlayer2(), player2);
//    }
//
//    private void savePlayerState(Player player, String filePath) {
//        JsonObject playerJson = new JsonObject();
//        playerJson.addProperty("uang", player.getUang());
//        playerJson.addProperty("deckSize", player.sizeDeck());
//
//        JsonArray activeDeckJsonArray = new JsonArray();
//        for (int i = 0; i < player.sizeActiveDeck(); i++) {
//            if (player.getActiveDeck()[i] != null) {
//                JsonObject cardJson = new JsonObject();
//                char rowChar = (char) ('A' + i);
//                cardJson.addProperty("position", rowChar + "01");
//                cardJson.addProperty("name", player.getActiveDeck()[i].getName().toUpperCase().replace(" ", "_"));
//                activeDeckJsonArray.add(cardJson);
//            }
//        }
//        playerJson.add("activeDeck", activeDeckJsonArray);
//
//        JsonArray ladangJsonArray = new JsonArray();
//        for (int i = 0; i < player.getLadang().length; i++) {
//            if (player.getLadang()[i] != null) {
//                JsonObject ladangJson = new JsonObject();
//                int row = i / 5;
//                char colNumber = (char) ('A' + row);
//                String rowLetter = String.format("%02d", row + 1);
//                ladangJson.addProperty("position", colNumber + rowLetter);
//                ladangJson.addProperty("name", player.getLadang()[i].getName().toUpperCase().replace(" ", "_"));
//                ladangJson.addProperty("umurOrBerat", player.getLadang()[i].getUmurOrBerat());
//
//                JsonArray appliedItemsJsonArray = new JsonArray();
//                for (Map.Entry<String, Integer> entry : player.getLadang()[i].getListAppliedItem().entrySet()) {
//                    appliedItemsJsonArray.add(entry.getKey().toUpperCase().replace(" ", "_"));
//                }
//                ladangJson.add("appliedItems", appliedItemsJsonArray);
//                ladangJsonArray.add(ladangJson);
//            }
//        }
//        playerJson.add("ladang", ladangJsonArray);
//
//        try (Writer writer = new FileWriter(filePath)) {
//            gson.toJson(playerJson, writer);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public void loadState(GameManager g, String fileDir) {
//        fileDir = "src\\main\\java\\com\\lamongan234\\gui\\Models\\" + fileDir;
//        String gamestate = fileDir + "\\gamestate.json";
//        String player1 = fileDir + "\\player1.json";
//        String player2 = fileDir + "\\player2.json";
//
//        try (Reader reader = new FileReader(gamestate)) {
//            JsonObject gameStateJson = gson.fromJson(reader, JsonObject.class);
//            int turnCount = gameStateJson.get("turn").getAsInt();
//            g.setTurn(turnCount);
//
//            JsonObject tokoJson = gameStateJson.getAsJsonObject("toko");
//            int uniqueItemCount = tokoJson.get("uniqueItemCount").getAsInt();
//            JsonArray itemsJsonArray = tokoJson.getAsJsonArray("items");
//
//            Toko toko = new Toko();
//            for (int i = 0; i < itemsJsonArray.size(); i++) {
//                JsonObject itemJson = itemsJsonArray.get(i).getAsJsonObject();
//                String itemName = itemJson.get("name").getAsString();
//                int itemQuantity = itemJson.get("quantity").getAsInt();
//                for (int j = 0; j < itemQuantity; j++) {
//                    Product p = (Product) DictKartu.get(itemName);
//                    toko.AddKartu(p);
//                }
//            }
//            g.setToko(toko);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        loadPlayerState(g, player1, true);
//        loadPlayerState(g, player2, false);
//    }
//
//    private void loadPlayerState(GameManager g, String filePath, boolean isPlayer1) {
//        try (Reader reader = new FileReader(filePath)) {
//            JsonObject playerJson = gson.fromJson(reader, JsonObject.class);
//
//            int uang = playerJson.get("uang").getAsInt();
//            Player player = new Player();
//            player.setUang(uang);
//
//            int deckSize = playerJson.get("deckSize").getAsInt();
//            player.generateRandomCards(deckSize);
//
//            JsonArray activeDeckJsonArray = playerJson.getAsJsonArray("activeDeck");
//            for (int i = 0; i < activeDeckJsonArray.size(); i++) {
//                JsonObject cardJson = activeDeckJsonArray.get(i).getAsJsonObject();
//                String itemName = cardJson.get("name").getAsString();
//                Kartu card = DictKartu.get(itemName);
//                player.addToActiveDeck(card);
//            }
//
//            JsonArray ladangJsonArray = playerJson.getAsJsonArray("ladang");
//            for (int i = 0; i < ladangJsonArray.size(); i++) {
//                JsonObject ladangJson = ladangJsonArray.get(i).getAsJsonObject();
//                String position = ladangJson.get("position").getAsString();
//                int col = position.charAt(0) - 'A';
//                int row = Integer.parseInt(position.substring(1)) - 1;
//                int pos = row * 5 + col;
//
//                String itemName = ladangJson.get("name").getAsString();
//                int umurOrBerat = ladangJson.get("umurOrBerat").getAsInt();
//                JsonArray appliedItemsJsonArray = ladangJson.getAsJsonArray("appliedItems");
//
//                Kartu card = DictKartu.get(itemName);
//                if (card instanceof Hewan) {
//                    Hewan hewan = (Hewan) card;
//                    hewan.setBeratOrUmur(umurOrBerat);
//                    for (int j = 0; j < appliedItemsJsonArray.size(); j++) {
//                        Item item = (Item) DictKartu.get(appliedItemsJsonArray.get(j).getAsString());
//                        hewan.AppliedItem(item);
//                    }
//                    player.setLadang(hewan, pos);
//                } else {
//                    Tanaman tanaman = (Tanaman) card;
//                    tanaman.setUmur(umurOrBerat);
//                    for (int j = 0; j < appliedItemsJsonArray.size(); j++) {
//                        Item item = (Item) DictKartu.get(appliedItemsJsonArray.get(j).getAsString());
//                        tanaman.AppliedItem(item);
//                    }
//                    player.setLadang(tanaman, pos);
//                }
//            }
//
//            if (isPlayer1) {
//                g.setPlayer1(player);
//            } else {
//                g.setPlayer2(player);
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//}
