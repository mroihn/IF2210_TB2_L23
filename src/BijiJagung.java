public class BijiJagung extends Tanaman{
  public BijiJagung(){
    super(0, 3);
  }
  public Kartu panenKartu(){
    if(isSiapPanen()){
      System.out.println("Biji Jagung berhasil dipanen");
      return new Kartu("Jagung", new Nabati(30, 10));
    }else{
      System.out.println("Umur belum cukup");
      return null;
    }
  }
}
