public abstract class Tanaman implements Harvestable{
  int umur;
  int age_to_harvest;

  public Tanaman(int umur, int age_to_harvest) {
    this.umur = umur;
    this.age_to_harvest = age_to_harvest;
  }
  // Getter and setter
  public int getberat() {
    return umur;
  }
  public int getweight_to_harvest() {
    return age_to_harvest;
  }
  public void addweight(int w){
    umur+=w;
  }

  public boolean isSiapPanen(){
    return age_to_harvest<=umur;
  }
}
