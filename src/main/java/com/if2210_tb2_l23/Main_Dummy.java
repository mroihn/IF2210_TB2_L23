package com.if2210_tb2_l23;

public class Main_Dummy {
    public static void main(String[] args) {
        Kartu hiuDarat = new Kartu("Hiu Darat", new HiuDarat());
        Kartu daging = new Kartu("daging sapi", new Hewani(20, 10));
        Kartu tempe = new Kartu("tempe", new Nabati(20, 10));

        System.out.println();
        System.out.println(hiuDarat.getHarvestable().getberat());
        hiuDarat.getHarvestable().panenKartu();
        hiuDarat.getCanEat().makan(daging);
        System.out.println(hiuDarat.getHarvestable().getberat());
        hiuDarat.getHarvestable().panenKartu();
        hiuDarat.getCanEat().makan(tempe);
        
  }
}
