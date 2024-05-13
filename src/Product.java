public abstract class Product implements Eatable{
  private int added_weight;
  private int price;
  public Product(int added_weight, int price){
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
