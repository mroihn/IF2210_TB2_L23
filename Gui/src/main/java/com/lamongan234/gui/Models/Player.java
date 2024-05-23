package com.lamongan234.gui.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
    private List<Kartu> deck;
    private List<Kartu> activeDeck;
    public Kartu[][] ladang;
    private int uang;

    public Player(){
        deck = new ArrayList<>();
        activeDeck = new ArrayList<>();
        ladang = new Kartu[4][5];
        uang = 0;
    }

    public int getUang(){
        return uang;
    }

    public void addUang(int uang){
        this.uang += uang;
    }

    public void printLadang(){
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 5; j++){
                if (ladang[i][j] != null){
                    System.out.println(ladang[i][j].getName()); 
                } else{
                    System.out.println(ladang[i][j]);
                }
            }
        }
    }

    public void setLadang(Kartu kartu, int x, int y){
        ladang[x][y] = kartu;
    }

    public void addToActiveDeck(Kartu kartu){
        if (activeDeck.size() < 6){
            activeDeck.add(kartu);
        }
    }

    public void printActiveDeck(){
        System.out.println("Active Deck: ");
        for(Kartu k : activeDeck){
            System.out.println(k.getName());
        }
    }

    public int sizeActiveDeck(){
        return activeDeck.size();
    }

    public void printDeck(){
        System.out.println("Deck: ");
        for (Kartu k : deck){
            System.out.println(k.getName());
        }
    }

    //bila deck kosong atau activeDeck penuh, return false
    //bila slot kosong di activeDeck kurang dari 4, shuffle kartu sejumlah slot kosong
    //else, shuffle sejumlah 4 kartu
    // kosongAwal = jumlah slot terisi activeDeck saat pemain shuffle di awal turn
    // return True kalau shuffle berhasil, False kalau tidak
    public boolean shuffleKartu(int isiAwalActiveDeck, boolean isAwalTurn){
        System.out.println("shuffleKartu running....");
        Random random = new Random();
        int randomNumber = random.nextInt(deck.size()); // generate inclusive 0-39
            // kalau ini adalah bukan awal turn maka hapus shuffle yang gak jadi
            if (!isAwalTurn){
                hapusShuffleSebelumnya(isiAwalActiveDeck, deck, activeDeck);
            }
            int reserveSlotActiveDeck = 6 - activeDeck.size();

            System.out.println("ukuran deck1: " + deck.size());
            System.out.println("ukuran activeDeck1: " + activeDeck.size());

            if(deck.isEmpty() || reserveSlotActiveDeck == 0){
                System.out.println("Deck kosong atau activeDeck penuh");
                return false;
            } else if(reserveSlotActiveDeck < 4){
                System.out.println("flag1");
                for(int i = 0; i < reserveSlotActiveDeck; i++){
                    activeDeck.add(deck.get(randomNumber));
                    deck.remove(randomNumber);
                }
            } else {
                System.out.println("flag2");
                for(int i = 0; i < 4; i++){
                    activeDeck.add(deck.get(randomNumber));
                    deck.remove(randomNumber);
                }
            }
            
            System.out.println("ukuran deck: " + deck.size());
            System.out.println("ukuran activeDeck: " + activeDeck.size());
            printDeck();
            printActiveDeck();
            return true;
    }

    public void hapusShuffleSebelumnya(int isiAwalActiveDeck, List<Kartu> deck, List<Kartu> activeDeck){
        int currentActiveDeckSize = activeDeck.size();
        for (int j = isiAwalActiveDeck; j < currentActiveDeckSize; j++){
            int lastIdx = activeDeck.size() - 1;
            Kartu balikinKeDeck = activeDeck.remove(lastIdx);
            deck.add(balikinKeDeck);
        }
    }

    public void generateRandomCards(int numberOfCards) {
        deck = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numberOfCards; i++) {
            int cardType = random.nextInt(24); // Assuming 3 types of cards
            switch (cardType) {
                case 0:
                    deck.add(new HiuDarat());
                    break;
                case 1:
                    deck.add(new Sapi());
                    break;
                case 2:
                    deck.add(new Domba());
                    break;
                case 3:
                    deck.add(new Kuda());
                    break;
                case 4:
                    deck.add(new Ayam());
                    break;
                case 5:
                    deck.add(new Beruang());
                    break;
                case 6:
                    deck.add(new BijiJagung());
                    break;
                case 7:
                    deck.add(new BijiLabu());
                    break;
                case 8:
                    deck.add(new BijiStroberi());
                    break;
                case 9:
                    deck.add(new Susu());
                    break;
                case 10:
                    deck.add(new Telur());
                    break;
                case 11:
                    deck.add(new SiripHiu());
                    break;
                case 12:
                    deck.add(new DagingKuda());
                    break;
                case 13:
                    deck.add(new DagingDomba());
                    break;
                case 14:
                    deck.add(new DagingBeruang());
                    break;
                case 15:
                    deck.add(new Accelerate());
                    break;
                case 16:
                    deck.add(new Delay());
                    break;
                case 17:
                    deck.add(new Destroy());
                    break;
                case 18:
                    deck.add(new Protect());
                    break;
                case 19:
                    deck.add(new Trap());
                    break;
                case 20:
                    deck.add(new Labu());
                    break;
                case 21:
                    deck.add(new Jagung());
                    break;
                case 22:
                    deck.add(new Stroberi());
                    break;
                case 23:
                    deck.add(new InstantHarvest());
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + cardType);
            }
        }
    }
}
