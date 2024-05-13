public class BijiLabu extends Tanaman{
  public BijiLabu(){
    super(0, 5);
  }
  public Kartu panenKartu(){
    if(isSiapPanen()){
      System.out.println("Biji Labu berhasil dipanen");
      return new Kartu("Labu", new Nabati(30, 10));
    }else{
      System.out.println("Umur belum cukup");
      return null;
    }
  }
}
