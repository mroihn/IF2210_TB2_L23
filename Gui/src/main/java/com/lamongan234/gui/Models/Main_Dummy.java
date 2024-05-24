package com.lamongan234.gui.Models;

public class Main_Dummy {
    public static void main(String[] args) {
        //inisialisasi
        Hewan hiuDarat = new HiuDarat();
        Tanaman jagung = new BijiJagung();
        Product daging = new DagingDomba();
        Product tempe = new Tempe();


        //SECTION UNTUK SEDDING GAMEMANAGER
        TxtSaveAndLoad txtSaveAndLoad = new TxtSaveAndLoad();
        GameManager gameManager = new GameManager();
        txtSaveAndLoad.loadState(gameManager, "tes");
        //cek isi
        gameManager.getToko().displayToko();
        gameManager.getPlayer1().printDetails();
        gameManager.getPlayer2().printDetails();
        //lakukan perubahan
        gameManager.getPlayer1().setUang(1010);
        //save state dan lihat pada direktori Gui\src\main\java\com\lamongan234\gui\Models\tes\
        //apakah ada perubahan uang pada player1.txt
        txtSaveAndLoad.saveState(gameManager, "tes");



        //SECTION UNTUK MANUAL MENGGUNAKAN PLAYER, tidak ada hubungan dengan GameManager di atas
        Player p = new Player();
        p.generateRandomCards(40);
        p.printDeck();
        System.out.println("hello");
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



        //tes serangan beruang
        gameManager.SeranganBeruang();
    }
}