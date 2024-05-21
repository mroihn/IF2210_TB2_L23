package com.if2210_tb2_l23;

/**
 * Note:
 * 1. Tab pada komentar hanya untuk mempermudah pembacaan, tidak ada tab pada saat melakukan print
 * suatu pesan.
 */

public class Kartu {
    private String name;
    private Kelas kelas;
    private Harvestable panen;
    private Eatable produk;
    private CanEat makan;
    
    // Konstruktor
    public Kartu(String name, Kelas kelas) {
        this.name = name;
        this.kelas = kelas;
        if(kelas instanceof Harvestable){
            panen = (Harvestable) kelas;
        }
        if(kelas instanceof CanEat){
            makan = (CanEat) kelas;
        }
        if(kelas instanceof Eatable){
            produk = (Eatable) kelas;
        }
    }

    // Getter and setter
    public void setKelas(Kelas kelas) {
        this.kelas = kelas;
    }

    public String getName() {
        return name;
    }
    public Kelas getKelas() {
        return kelas;
    }
    public Harvestable getHarvestable() {
        return panen;
    }
    public CanEat getCanEat() {
        return makan;
    }
    public Eatable getEatable() {
        return produk;
    }
    public boolean isHarvestable(){
        return !(panen==null);
    }
    public boolean isEatable(){
        return !(produk==null);
    }
}