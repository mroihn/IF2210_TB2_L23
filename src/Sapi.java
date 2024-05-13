public class Sapi extends Herbivora {
  public Sapi(){
    super(0, 10);
  }
  public Kartu panenKartu(){
    if(isSiapPanen()){
      System.out.println("Sapi dipanen!");
      return new Kartu("daging sapi", new Sapi());
    }else{
      System.out.println("Sapi belum bisa dipanen!");
    }
    return null;
  }
  public void gunakanItem(Kartu self, Kartu target){
    
  }
}
