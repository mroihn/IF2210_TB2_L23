public class BijiJagung extends Tanaman{
  public BijiJagung(){
    super("Biji Jagung", 0, 3);
  }
  public Product panenKartu(){
    if(isSiapPanen()){
      System.out.println("Biji Jagung berhasil dipanen");
      return new DagingDomba();
    }else{
      System.out.println("Umur belum cukup");
      return null;
    }
  }
}
