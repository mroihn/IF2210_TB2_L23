public abstract class Karnivora extends Hewan {
  public Karnivora(int berat, int weight_to_harvest){
    super(berat,weight_to_harvest);
  }
  public void makan(Kartu makanan){
    if(makanan.isEatable() && makanan.getEatable() instanceof Hewani){
      System.out.println("Karnivora makan " + makanan.getName());
      berat += makanan.getEatable().getaddedweight();
    }else{
      System.out.println("Makanan tidak cocok");
    }
  }
}
