package com.lamongan234.gui.Models;

public class Main_Dummy {
    public static void main(String[] args) {
        Hewan hiuDarat = new HiuDarat();
        Tanaman jagung = new BijiJagung();
        Product daging = new DagingDomba();
        Product tempe = new Tempe();

        Player p = new Player();
        p.generateRandomCards(40);
        p.printDeck();
        Item accelerate = new Accelerate();
        Item delay = new Delay();
        jagung.panenKartu();
        System.out.println("");
        System.out.println("berat awal hiu darat: " + hiuDarat.getberat());
        accelerate.Efek(hiuDarat);
        System.out.println("Accelerating!!!!");
        System.out.println(hiuDarat.getberat());
        delay.Efek(hiuDarat);
        System.out.println("Delaying!!!!");
        System.out.println(hiuDarat.getberat());
        hiuDarat.panenKartu();
        hiuDarat.makan(daging);
        System.out.println(hiuDarat.getberat());
        hiuDarat.panenKartu();
        hiuDarat.makan(tempe);
        
  }
}
