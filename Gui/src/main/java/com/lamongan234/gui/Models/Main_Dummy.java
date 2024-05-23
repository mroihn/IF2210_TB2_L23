package com.lamongan234.gui.Models;

import java.util.Scanner;
public class Main_Dummy {
    public static void main(String[] args) {
        Hewan hiuDarat = new HiuDarat();
        Tanaman jagung = new BijiJagung();
        Product daging = new DagingDomba();
        Product tempe = new Tempe();

        //Test generating Cards
        Player p = new Player();
        p.generateRandomCards(40);
        p.printDeck();

        //Test Using Items
        Item accelerate = new Accelerate();
        Item delay = new Delay();
        jagung.panenKartu();
        System.out.println("");
        System.out.println("berat awal hiu darat: " + hiuDarat.getberat());
        accelerate.Efek(hiuDarat);
        accelerate.Efek(hiuDarat);
        accelerate.Efek(hiuDarat);
        System.out.println("Accelerating!!!!");
        hiuDarat.Display();
        System.out.println(hiuDarat.getberat());
        delay.Efek(hiuDarat);
        delay.Efek(hiuDarat);
        delay.Efek(hiuDarat);
        System.out.println("Delaying!!!!");
        hiuDarat.Display();
        System.out.println(hiuDarat.getberat());
        hiuDarat.panenKartu();
        hiuDarat.makan(daging);
        System.out.println(hiuDarat.getberat());
        hiuDarat.panenKartu();
        hiuDarat.makan(tempe);
        
        p.printDeck();
        p.printActiveDeck();

        Toko t = new Toko();
        System.out.println("Uang: " + p.getUang());
        t.beli(tempe, p);
        System.out.println("Uang: " + p.getUang());
        t.displayToko();


        //tes shuffle
        boolean mauShuffleLagi;
        boolean isShuffleDimungkinkan;
        Scanner scanner = new Scanner(System.in);
        boolean isAwalTurn = true;
        int isiAwalActiveDeck = p.sizeActiveDeck();

        System.out.println("===========================================");

        do{
          System.out.println("isi awal: " + isiAwalActiveDeck);
          System.out.println(isAwalTurn);
          isShuffleDimungkinkan = p.shuffleKartu(isiAwalActiveDeck, isAwalTurn);
          isAwalTurn = false;
          if (isShuffleDimungkinkan) {
            System.out.println("Shuffle lagi? (y/n)");
            String shuffleLagi = scanner.nextLine();
            if (shuffleLagi.equals("n")) {
            mauShuffleLagi = false;
            } else{
              mauShuffleLagi = true;
              System.out.println("Shuffling....");
            }
          } else {
            mauShuffleLagi = false;
          }
        } while (isShuffleDimungkinkan && mauShuffleLagi);
        // scanner.close();

        // isiAwalActiveDeck = p.sizeActiveDeck();
        // do{
        //   System.out.println("isi awal: " + isiAwalActiveDeck);
        //   System.out.println(isAwalTurn);
        //   isShuffleDimungkinkan = p.shuffleKartu(isiAwalActiveDeck, isAwalTurn);
        //   isAwalTurn = false;
        //   if (isShuffleDimungkinkan) {
        //     System.out.println("Shuffle lagi? (y/n)");
        //     String shuffleLagi = scanner.nextLine();
        //     if (shuffleLagi.equals("n")) {
        //     mauShuffleLagi = false;
        //     } else{
        //       mauShuffleLagi = true;
        //       System.out.println("Shuffling....");
        //     }
        //   } else {
        //     mauShuffleLagi = false;
        //   }
        // } while (isShuffleDimungkinkan && mauShuffleLagi);
        scanner.close();

        p.setLadang(jagung, 0, 0);
        p.printLadang();
        System.out.println("Uang: " + p.getUang());
        p.addUang(100);
        System.out.println("Uang: " + p.getUang());
        p.addToActiveDeck(delay);
        
  }
}