public abstract class Hewan implements Harvestable, CanEat{
  protected int berat;
  protected int weight_to_harvest;
  public Hewan(int berat, int weight_to_harvest) {
    this.berat = berat;
    this.weight_to_harvest = weight_to_harvest;
  }
  // Getter and setter
  public int getberat() {
    return berat;
  }
  public int getweight_to_harvest() {
    return weight_to_harvest;
  }
  public void addweight(int w){
    berat+=w;
  }

  public boolean isSiapPanen(){
    return weight_to_harvest<=berat;
  }

}

