public abstract class Omnivora extends Hewan {
  public Omnivora(int berat, int weight_to_harvest){
    super(berat,weight_to_harvest);
  }
  public void makan(Kartu makanan){
    System.out.println("Omnivora makan " + makanan.getName());
    berat += makanan.getEatable().getaddedweight();
  }
}
