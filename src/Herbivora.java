public abstract class Herbivora extends Hewan {
  public Herbivora(int berat, int weight_to_harvest){
    super(berat,weight_to_harvest);
  }
  public void makan(Kartu makanan){
    if(makanan.isEatable() && makanan.getEatable() instanceof Nabati){
      System.out.println("Herbivora makan " + makanan.getName());
      berat += makanan.getEatable().getaddedweight();
    }else{
      System.out.println("Makanan tidak cocok");
    }
  }
}
