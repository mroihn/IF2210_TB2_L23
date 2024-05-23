package com.lamongan234.gui.Models;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Player {
    private List<Kartu> deck;
    private Kartu[] shuffleArr;
    private List<Kartu> selectedKartu;
    private Kartu[] activeDeck;
    public Harvestable[] ladang;
    private int uang;

    public Player(){
        deck = new ArrayList<>();
        activeDeck = new Kartu[6];
        ladang = new Harvestable[20];
        shuffleArr = new Kartu[4];
        selectedKartu = new ArrayList<>();
        uang = 0;
    }

    public int getUang(){
        return uang;
    }
    public void setUang(int uang){
        this.uang = uang;
    }

    public void addUang(int uang){
        this.uang += uang;
    }

    public void printLadang(){
        for (int i = 0; i < 20; i++){
            if (ladang[i] != null){
                System.out.println(ladang[i].getName() + " " + i); 
            } else{
                System.out.println("coord: " + i);
            }
        }
    }

    public void setLadang(Kartu kartu, int x, int y){
        int row = x*5;
        int pos = row+y;
        if(kartu instanceof Harvestable){
            Harvestable h = (Harvestable) kartu;
            ladang[pos] = h;
        }
    }

    public void addToActiveDeck(Kartu kartu){
        for (int i = 0; i < 6; i++){
            if (activeDeck[i] == null){
                activeDeck[i] = kartu;
                break;
            }
        }
    }
    public void removeFromActiveDeck(Kartu kartu){
        for (int i = 0; i < 6; i++){
            if (activeDeck[i] == kartu){
                activeDeck[i] = null;
                break;
            }
        }
    }

    public void printActiveDeck(){
        System.out.println("Active Deck: ");
        for (int i = 0; i < 6; i++){
            if (activeDeck[i] != null){
                System.out.println(activeDeck[i].getName());
            } else{
                System.out.println(activeDeck[i]);
            }
        }
    }

    public int sizeActiveDeck(){
        return activeDeck.length;
    }
    
    public void printDeck(){
        System.out.println("Deck: ");
        for (Kartu k : deck){
            System.out.println(k.getName());
        }
    }

    public void printDetails(){
        System.out.println("Uang: " + uang);
        System.out.println("Deck Count: " + deck.size());
        printActiveDeck();
        System.out.println("Ladang:");
        printLadang();
    }

    //bila deck kosong atau activeDeck penuh, return false
    //bila slot kosong di activeDeck kurang dari 4, shuffle kartu sejumlah slot kosong
    //else, shuffle sejumlah 4 kartu
    // kosongAwal = jumlah slot terisi activeDeck saat pemain shuffle di awal turn
    // return True kalau shuffle berhasil, False kalau tidak
    public void Panen(int row, int cols){
        if(filledSlotActiveDeck()>5){
            System.out.println("Active Deck penuh");
        }
        int pos = row*5 + cols;
        Product hasilPanen = ladang[pos].panenKartu();
        addToActiveDeck(hasilPanen);
        ladang[pos] = null;
    }

        
    public int emptySlotActiveDeck(){
        int count = 0;
        for (int i = 0; i < 6; i++){
            if (activeDeck[i] == null){
                count++;
            }
        }
        return count;
    }

    public int filledSlotActiveDeck(){
        return 6 - emptySlotActiveDeck();
    }

    public boolean addShuffleArr(Kartu kartu){
        for (int i = 0; i < 4; i++){
            if (shuffleArr[i] == null){
                shuffleArr[i] = kartu;
                return true;
            }
        }
        return false;
    }

    public void addToShuffleArr(Kartu kartu){
        for (int i = 0; i < 4; i++){
            if (shuffleArr[i] == null){
                shuffleArr[i] = kartu;
                break;
            }
        }
    }

    void printShuffleArr(){
        System.out.println("ShuffleArr: ");
        for (int i = 0; i < 4; i++){
            if (shuffleArr[i] != null){
                System.out.println(shuffleArr[i].getName());
            } else{
                System.out.println(shuffleArr[i]);
            }
        }
    }

    public int filledSlotShuffleArr(){
        int count = 0;
        for (int i = 0; i < 4; i++){
            if (shuffleArr[i] != null){
                count++;
            }
        }
        return count;
    }

    //buat pop-up window untuk shuffle Kartu
    public Kartu[] shuffleKartu(){
        selectedKartu.clear();
        shuffleArr = new Kartu[4];

    public boolean shuffleKartu(int isiAwalActiveDeck, boolean isAwalTurn){
        System.out.println("shuffleKartu running....");
        Random random = new Random();
        List <Integer> alreadyRandomNumber = new ArrayList<>();
        while (alreadyRandomNumber.size() < 4){
            int randomIndex = random.nextInt(deck.size());
            if (!alreadyRandomNumber.contains(randomIndex)){
                alreadyRandomNumber.add(randomIndex);
                addShuffleArr(deck.get(randomIndex));
            }
        }
        return shuffleArr;
    }

    public void pilihSatuKartu(int index){
        selectedKartu.add(shuffleArr[index]);
    }

    public void batalkanPilihanSatuKartu(int index){
        selectedKartu.remove(index);
    }

    public void shuffleSelesai(){
        for (Kartu k : selectedKartu){
            deck.remove(k);
        }
        for (Kartu k : selectedKartu){
            addToActiveDeck(k);
        }
        selectedKartu.clear();
        shuffleArr = new Kartu[4];
    }

    public void startShuffle(){
        boolean lanjut = true;
        Scanner scanner = new Scanner(System.in);

        do {
            Kartu[] showed = shuffleKartu();
            System.out.println("Daftar kartu yang ada di pop shuffle: ");
            System.out.println(showed[0].getName());
            System.out.println(showed[1].getName());
            System.out.println(showed[2].getName());
            System.out.println(showed[3].getName());
            printShuffleArr();
            pilihSatuKartu(0);
            pilihSatuKartu(1);
            System.out.println("mau shuffle lagi? (y/n)");
            String choice = scanner.nextLine();
            System.out.println("masukan: " + choice);
            if (choice.equals("n")){
                shuffleSelesai();
                lanjut = false;
                System.out.println("ukuran deck: " + deck.size());
                System.out.println("ukuran active deck: " + filledSlotActiveDeck());
                System.out.println("ukuran shuffle arr: " + filledSlotShuffleArr());
            }
        } while (lanjut);
        scanner.close();
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
