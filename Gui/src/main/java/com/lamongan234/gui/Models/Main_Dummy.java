package com.lamongan234.gui.Models;

public class Main_Dummy {
    public static void main(String[] args) {
        //inisialisasi
        Hewan hiuDarat = new HiuDarat();
        Tanaman jagung = new BijiJagung();
        Product daging = new DagingDomba();
        Product tempe = new Tempe();


        // //SECTION UNTUK SEEDING GAMEMANAGER
        // TxtSaveAndLoad txtSaveAndLoad = new TxtSaveAndLoad();
        // GameManager gameManager = new GameManager();
        // txtSaveAndLoad.loadState(gameManager, "tes");
        // //cek isi
        // gameManager.getToko().displayToko();
        // gameManager.getPlayer1().printDetails();
        // gameManager.getPlayer2().printDetails();
        // //lakukan perubahan
        // gameManager.getPlayer1().setUang(1010);
        // //save state dan lihat pada direktori Gui\src\main\java\com\lamongan234\gui\Models\tes\
        // //apakah ada perubahan uang pada player1.txt
        // txtSaveAndLoad.saveState(gameManager, "tes");


        // //SECTION untuk mencoba save and load plugin, dalam hal ini test buat txt
        // GameManager gameManager2 = new GameManager();
        // String jarPath = "TxtSaveAndLoad.jar";
        // SaveAndLoad txtPluginSaveAndLoad = gameManager2.SaveAndLoadPlugin(jarPath, "com.lamongan234.gui.Models.TxtSaveAndLoad");
        // txtPluginSaveAndLoad.loadState(gameManager2, "tes");
        // //cek isi player2.txt
        // gameManager2.getPlayer2().printDetails();
        // gameManager2.getPlayer1().setUang(2024);
        // //simpan perubahan
        // txtPluginSaveAndLoad.saveState(gameManager2, "tes");
        // // harusnya uang berubah jadi 2024 untuk player1, lihat di player1.txt


        //SECTION tes XmlSaveAndLoad
        // GameManager gameManager = new GameManager();
        // XmlSaveAndLoad xmlSaveAndLoad = new XmlSaveAndLoad();
        // xmlSaveAndLoad.loadState(gameManager, "tes");
        // gameManager.getPlayer1().setUang(2215);
        // xmlSaveAndLoad.saveState(gameManager, "tes");
        // xmlSaveAndLoad.loadState(gameManager, "tes");
        // System.out.println("gla1");
        // gameManager.getPlayer1().printDetails();
        // System.out.println("gla2");

        //SECTION tes XMLSaveAndLoad.jar plugin
        // GameManager gameManager2 = new GameManager();
        // String jarPath = "XmlSaveAndLoad.jar";
        // SaveAndLoad xmlPluginSaveAndLoad = gameManager2.SaveAndLoadPlugin(jarPath, "com.lamongan234.gui.Models.XmlSaveAndLoad");
        // xmlPluginSaveAndLoad.loadState(gameManager2, "tes");
        // System.out.println("Uang Player 1 == " + gameManager2.getPlayer1().getUang());
        // gameManager2.getPlayer1().setUang(221513);
        // xmlPluginSaveAndLoad.saveState(gameManager2, "tes");

        //SECTION tes JsonSaveAndLoad
        // GameManager gameManager = new GameManager();
        // JsonSaveAndLoad jsonSaveAndLoad = new JsonSaveAndLoad();
        // jsonSaveAndLoad.loadState(gameManager, "tes");
        // gameManager.getPlayer1().setUang(115);
        // jsonSaveAndLoad.saveState(gameManager, "tes");

        //SECTION test JsonSaveAndLoad.jar plugin
        GameManager gameManager = new GameManager();
        String jarPath = "JsonSaveAndLoad.jar";
        SaveAndLoad jsonPluginSaveAndLoad = gameManager.SaveAndLoadPlugin(jarPath, "com.lamongan234.gui.Models.JsonSaveAndLoad");
        jsonPluginSaveAndLoad.loadState(gameManager, "tes");
        System.out.println("Uang Player 1 == " + gameManager.getPlayer1().getUang());
        gameManager.getPlayer1().setUang(252);
        jsonPluginSaveAndLoad.saveState(gameManager, "tes");

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

        p.setLadang(jagung, 0);
        p.printLadang();
        System.out.println("Uang: " + p.getUang());
        p.addUang(100);
        System.out.println("Uang: " + p.getUang());
        p.addToActiveDeck(delay);



        //tes serangan beruang
        // gameManager.SeranganBeruang();
    }
}