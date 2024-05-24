package com.lamongan234.gui.Models;
public class Main_Dummy {
    public static void main(String[] args) {
        Hewan hiuDarat = new HiuDarat();
        Tanaman jagung = new BijiJagung();
        Product daging = new DagingDomba();
        Product tempe = new Tempe();
        GameManager g = new GameManager();
        SaveAndLoad load = new TxtSaveAndLoad();
        System.out.println(" ");
        load.loadState(g, "statefiles");
        g.getToko().displayToko();
        g.getPlayer1().printDetails();
        g.getPlayer2().printDetails();

        load.saveState(g, "tes");

        //Test generating Cards
        Player p = new Player();
        p.generateRandomCards(40);
        p.printDeck();
        g.SeranganBeruang();
        //Test Using Items
        Item accelerate = new Accelerate();
        Item delay = new Delay();
        jagung.panenKartu();
        System.out.println("");
        System.out.println("berat awal hiu darat: " + hiuDarat.getUmurOrBerat());
        accelerate.Efek(hiuDarat);
        accelerate.Efek(hiuDarat);
        accelerate.Efek(hiuDarat);
        System.out.println("Accelerating!!!!");
        hiuDarat.Display();
        System.out.println(hiuDarat.getUmurOrBerat());
        delay.Efek(hiuDarat);
        delay.Efek(hiuDarat);
        delay.Efek(hiuDarat);
        System.out.println("Delaying!!!!");
        hiuDarat.Display();
        System.out.println(hiuDarat.getUmurOrBerat());
        hiuDarat.panenKartu();
        hiuDarat.makan(daging);
        System.out.println(hiuDarat.getUmurOrBerat());
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

        System.out.println("===========================================");

        p.startShuffle();

        p.setLadang(jagung, 0, 0);
        p.printLadang();
        System.out.println("Uang: " + p.getUang());
        p.addUang(100);
        System.out.println("Uang: " + p.getUang());
        p.addToActiveDeck(delay);
  }
}