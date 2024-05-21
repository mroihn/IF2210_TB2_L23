public abstract class Product extends Kartu{
  private int added_weight;
  private int price;
  public Product(String nama, int added_weight, int price){
    super(nama);
    this.added_weight = added_weight;
    this.price = price;
  }

  public int getprice(){
    return price;
  }
  public int getaddedweight(){
    return added_weight;
  }
}
