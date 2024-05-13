public class Domba extends Herbivora {
  public Domba(){
    super(0, 12);
  }
  public Kartu panenKartu(){
    if(isSiapPanen()){
      System.out.println("Domba dipanen!");
      return new Kartu("daging Domba", new Domba());
    }else{
      System.out.println("Domba belum bisa dipanen!");
    }
    return null;
  }
  public void gunakanItem(Kartu self, Kartu target){
    
  }
}
