package com.lamongan234.gui.Models;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.*;

public class XmlSaveAndLoad implements SaveAndLoad {
    private Map<String, Kartu> DictKartu;

    public XmlSaveAndLoad() {
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
        String gamestate = fileDir + "\\gamestate.xml";
        String player1 = fileDir + "\\player1.xml";
        String player2 = fileDir + "\\player2.xml";

        saveGameState(g, gamestate);
        savePlayerState(g.getPlayer1(), player1);
        savePlayerState(g.getPlayer2(), player2);
    }

    private void saveGameState(GameManager g, String filePath) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element rootElement = doc.createElement("GameState");
            doc.appendChild(rootElement);

            Element turnElement = doc.createElement("Turn");
            turnElement.appendChild(doc.createTextNode(String.valueOf(g.getTurn())));
            rootElement.appendChild(turnElement);

            Element tokoElement = doc.createElement("Toko");
            tokoElement.setAttribute("uniqueItemCount", String.valueOf(g.getToko().getUniqueItemCnt()));
            rootElement.appendChild(tokoElement);

            for (Map.Entry<String, Integer> entry : g.getToko().getListQuantity().entrySet()) {
                Element itemElement = doc.createElement("Item");
                itemElement.setAttribute("name", entry.getKey().toUpperCase().replace(" ", "_"));
                itemElement.appendChild(doc.createTextNode(String.valueOf(entry.getValue())));
                tokoElement.appendChild(itemElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    private void savePlayerState(Player player, String filePath) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();

            Element playerElement = doc.createElement("Player");
            doc.appendChild(playerElement);

            Element uangElement = doc.createElement("Uang");
            uangElement.appendChild(doc.createTextNode(String.valueOf(player.getUang())));
            playerElement.appendChild(uangElement);

            Element deckSizeElement = doc.createElement("DeckSize");
            deckSizeElement.appendChild(doc.createTextNode(String.valueOf(player.sizeDeck())));
            playerElement.appendChild(deckSizeElement);

            Element activeDeckElement = doc.createElement("ActiveDeck");
            activeDeckElement.setAttribute("count", String.valueOf(player.filledSlotActiveDeck()));
            playerElement.appendChild(activeDeckElement);

            for (int i = 0; i < player.sizeActiveDeck(); i++) {
                if (player.getActiveDeck()[i] != null) {
                    Element cardElement = doc.createElement("Card");
                    cardElement.setAttribute("position", String.valueOf((char) ('A' + i)) + "01");
                    cardElement.appendChild(doc.createTextNode(player.getActiveDeck()[i].getName().toUpperCase().replace(" ", "_")));
                    activeDeckElement.appendChild(cardElement);
                }
            }

            Element ladangElement = doc.createElement("Ladang");
            ladangElement.setAttribute("count", String.valueOf(player.jumlahSlotLadangTerisi()));
            playerElement.appendChild(ladangElement);

            for (int i = 0; i < player.getLadang().length; i++) {
                if (player.getLadang()[i] != null) {
                    Element ladangSlotElement = doc.createElement("LadangSlot");
                    ladangSlotElement.setAttribute("position", String.valueOf((char) ('A' + (i / 5))) + String.format("%02d", (i / 5) + 1));
                    ladangSlotElement.setAttribute("umurOrBerat", String.valueOf(player.getLadang()[i].getUmurOrBerat()));
                    ladangSlotElement.setAttribute("name", player.getLadang()[i].getName().toUpperCase().replace(" ", "_"));

                    for (Map.Entry<String, Integer> entry : player.getLadang()[i].getListAppliedItem().entrySet()) {
                        Element appliedItemElement = doc.createElement("AppliedItem");
                        appliedItemElement.appendChild(doc.createTextNode(entry.getKey().toUpperCase().replace(" ", "_")));
                        ladangSlotElement.appendChild(appliedItemElement);
                    }

                    ladangElement.appendChild(ladangSlotElement);
                }
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File(filePath));
            transformer.transform(source, result);
        } catch (ParserConfigurationException | TransformerException e) {
            e.printStackTrace();
        }
    }

    public void loadState(GameManager g, String fileDir) {
        fileDir = "src\\main\\java\\com\\lamongan234\\gui\\Models\\" + fileDir;
        String gamestate = fileDir + "\\gamestate.xml";
        String player1 = fileDir + "\\player1.xml";
        String player2 = fileDir + "\\player2.xml";

        loadGameState(g, gamestate);
        loadPlayerState(g, player1, true);
        loadPlayerState(g, player2, false);
    }

    private void loadGameState(GameManager g, String filePath) {
        try {
            File file = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            int turnCount = Integer.parseInt(doc.getElementsByTagName("Turn").item(0).getTextContent());
            g.setTurn(turnCount);

            Element tokoElement = (Element) doc.getElementsByTagName("Toko").item(0);
            int uniqueItemCount = Integer.parseInt(tokoElement.getAttribute("uniqueItemCount"));
            Toko toko = new Toko();

            NodeList items = tokoElement.getElementsByTagName("Item");
            for (int i = 0; i < items.getLength(); i++) {
                Element itemElement = (Element) items.item(i);
                String itemName = itemElement.getAttribute("name");
                int itemQuantity = Integer.parseInt(itemElement.getTextContent());

                for (int j = 0; j < itemQuantity; j++) {
                    Product p = (Product) DictKartu.get(itemName);
                    toko.AddKartu(p);
                }
            }
            g.setToko(toko);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadPlayerState(GameManager g, String filePath, boolean isPlayer1) {
        try {
            File file = new File(filePath);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();

            Player player = new Player();

            int uang = Integer.parseInt(doc.getElementsByTagName("Uang").item(0).getTextContent());
            player.setUang(uang);

            int deckSize = Integer.parseInt(doc.getElementsByTagName("DeckSize").item(0).getTextContent());
            player.generateRandomCards(deckSize); // Assuming this generates a random deck

            Element activeDeckElement = (Element) doc.getElementsByTagName("ActiveDeck").item(0);
            NodeList cardNodes = activeDeckElement.getElementsByTagName("Card");
            for (int i = 0; i < cardNodes.getLength(); i++) {
                Element cardElement = (Element) cardNodes.item(i);
                String itemName = cardElement.getTextContent();
                Kartu card = DictKartu.get(itemName);
                player.addToActiveDeck(card);
            }

            Element ladangElement = (Element) doc.getElementsByTagName("Ladang").item(0);
            NodeList ladangSlots = ladangElement.getElementsByTagName("LadangSlot");
            for (int i = 0; i < ladangSlots.getLength(); i++) {
                Element ladangSlot = (Element) ladangSlots.item(i);
                String position = ladangSlot.getAttribute("position");
                String itemName = ladangSlot.getAttribute("name");
                int umurOrBerat = Integer.parseInt(ladangSlot.getAttribute("umurOrBerat"));
                NodeList appliedItems = ladangSlot.getElementsByTagName("AppliedItem");

                int col = position.charAt(0) - 'A';
                int row = Integer.parseInt(position.substring(1)) - 1;
                int pos = row * 5 + col;

                Kartu card = DictKartu.get(itemName);
                if (card instanceof Hewan) {
                    Hewan hewan = (Hewan) card;
                    hewan.setBeratOrUmur(umurOrBerat);
                    for (int j = 0; j < appliedItems.getLength(); j++) {
                        String appliedItemName = appliedItems.item(j).getTextContent();
                        Item item = (Item) DictKartu.get(appliedItemName);
                        hewan.AppliedItem(item);
                    }
                    player.setLadang(hewan, pos);
                } else if (card instanceof Tanaman) {
                    Tanaman tanaman = (Tanaman) card;
                    tanaman.setUmur(umurOrBerat);
                    for (int j = 0; j < appliedItems.getLength(); j++) {
                        String appliedItemName = appliedItems.item(j).getTextContent();
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

