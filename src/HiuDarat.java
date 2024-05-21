public class HiuDarat extends Karnivora {
  public HiuDarat(){
    super("Hiu Darat", 0, 20);
  }
  public Product panenKartu(){
    if(isSiapPanen()){
      System.out.println("Hiu darat dipanen!");
      return new DagingDomba();
    }else{
      System.out.println("Hiu darat belum bisa dipanen!");
    }
    return null;
  }
  public void gunakanItem(Kartu self, Kartu target){
    
  }
}
