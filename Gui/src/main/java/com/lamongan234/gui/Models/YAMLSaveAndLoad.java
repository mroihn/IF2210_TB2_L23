package com.lamongan234.gui.Models;

import org.yaml.snakeyaml.Yaml;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;

public class YAMLSaveAndLoad implements SaveAndLoad {

    public void saveState(GameManager g, String fileDir) {
        fileDir = "src\\main\\java\\com\\lamongan234\\gui\\Models\\" + fileDir;
        String gamestate = fileDir + "\\gamestate.yaml";
        String player1 = fileDir + "\\player1.yaml";
        String player2 = fileDir + "\\player2.yaml";

        saveGameState(g, gamestate);
        savePlayerState(g.getPlayer1(), player1);
        savePlayerState(g.getPlayer2(), player2);
    }

    private void saveGameState(GameManager g, String filePath) {
        Yaml yaml = new Yaml();
        try (FileWriter writer = new FileWriter(filePath)) {
            yaml.dump(Map.of(
                "Turn", g.getTurn(),
                "Toko", Map.of(
                    "uniqueItemCount", g.getToko().getUniqueItemCnt(),
                    "items", g.getToko().getListQuantity()
                )
            ), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void savePlayerState(Player player, String filePath) {
        Yaml yaml = new Yaml();
        try (FileWriter writer = new FileWriter(filePath)) {
            yaml.dump(Map.of(
                "Uang", player.getUang(),
                "DeckSize", player.sizeDeck(),
                "ActiveDeck", Map.of(
                    "count", player.filledSlotActiveDeck(),
                    "cards", player.getActiveDeckNames()
                ),
                "Ladang", Map.of(
                    "count", player.jumlahSlotLadangTerisi(),
                    "slots", player.getLadangInfo()
                )
            ), writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadState(GameManager g, String fileDir) {
        fileDir = "src\\main\\java\\com\\lamongan234\\gui\\Models\\" + fileDir;
        String gamestate = fileDir + "\\gamestate.yaml";
        String player1 = fileDir + "\\player1.yaml";
        String player2 = fileDir + "\\player2.yaml";

        loadGameState(g, gamestate);
        loadPlayerState(g, player1, true);
        loadPlayerState(g, player2, false);
    }

    private void loadGameState(GameManager g, String filePath) {
        Yaml yaml = new Yaml();
        try (FileReader reader = new FileReader(filePath)) {
            Map<String, Object> gameState = yaml.load(reader);
            g.setTurn((int) gameState.get("Turn"));

            Map<String, Object> toko = (Map<String, Object>) gameState.get("Toko");
            Toko tokoObj = new Toko();
            tokoObj.setUniqueItemCnt((int) toko.get("uniqueItemCount"));
            tokoObj.setListQuantity((Map<String, Integer>) toko.get("items"));
            g.setToko(tokoObj);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadPlayerState(GameManager g, String filePath, boolean isPlayer1) {
        Yaml yaml = new Yaml();
        try (FileReader reader = new FileReader(filePath)) {
            Map<String, Object> playerState = yaml.load(reader);
            Player player = new Player();
            player.setUang((int) playerState.get("Uang"));

            Map<String, Object> activeDeck = (Map<String, Object>) playerState.get("ActiveDeck");
            player.generateRandomCards((int) playerState.get("DeckSize"));
            for (String cardName : (Iterable<String>) activeDeck.get("cards")) {
                player.addToActiveDeck(DictKartu.get(cardName));
            }

            Map<String, Object> ladang = (Map<String, Object>) playerState.get("Ladang");
            for (Map<String, Object> slotInfo : (Iterable<Map<String, Object>>) ladang.get("slots")) {
                String itemName = (String) slotInfo.get("name");
                int pos = (int) slotInfo.get("position");
                int umurOrBerat = (int) slotInfo.get("umurOrBerat");
                Map<String, Integer> appliedItems = (Map<String, Integer>) slotInfo.get("appliedItems");

                Kartu card = DictKartu.get(itemName);
                if (card instanceof Hewan) {
                    Hewan hewan = (Hewan) card;
                    hewan.setBeratOrUmur(umurOrBerat);
                    for (Map.Entry<String, Integer> entry : appliedItems.entrySet()) {
                        Item item = (Item) DictKartu.get(entry.getKey());
                        hewan.AppliedItem(item);
                    }
                    player.setLadang(hewan, pos);
                } else if (card instanceof Tanaman) {
                    Tanaman tanaman = (Tanaman) card;
                    tanaman.setUmur(umurOrBerat);
                    for (Map.Entry<String, Integer> entry : appliedItems.entrySet()) {
                        Item item = (Item) DictKartu.get(entry.getKey());
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
