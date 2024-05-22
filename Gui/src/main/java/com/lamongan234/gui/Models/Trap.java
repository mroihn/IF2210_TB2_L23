public class Trap extends Item{
  public Trap(){
    super("Trap");
  }
  public void Efek(Kartu target){
    if(target instanceof Hewan){
      Hewan h = (Hewan) target;
      h.setTrap();
    }else if(target instanceof Tanaman){
      Tanaman t = (Tanaman) target;
      t.setTrap();
    }else{
      System.err.println("Invalid move");
    }
  }
}
