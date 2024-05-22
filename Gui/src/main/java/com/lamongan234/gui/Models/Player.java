import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
  private List<Kartu> Deck;
  private List<Kartu> activeDeck;
  public Player(){
    Deck = new ArrayList<>();
    activeDeck = new ArrayList<>();
  }
  public void shuffle(){

  }
  public void printDeck(){
    for (Kartu k : Deck){
      System.out.println(k.getName());
    }
  }
  public void generateRandomCards(int numberOfCards) {
        Deck = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < numberOfCards; i++) {
            int cardType = random.nextInt(24); // Assuming 3 types of cards
            switch (cardType) {
                case 0:
                    Deck.add(new HiuDarat());
                    break;
                case 1:
                    Deck.add(new Sapi());
                    break;
                case 2:
                    Deck.add(new Domba());
                    break;
                case 3:
                    Deck.add(new Kuda());
                    break;
                case 4:
                    Deck.add(new Ayam());
                    break;
                case 5:
                    Deck.add(new Beruang());
                    break;
                case 6:
                    Deck.add(new BijiJagung());
                    break;
                case 7:
                    Deck.add(new BijiLabu());
                    break;
                case 8:
                    Deck.add(new BijiStroberi());
                    break;
                case 9:
                    Deck.add(new Susu());
                    break;
                case 10:
                    Deck.add(new Telur());
                    break;
                case 11:
                    Deck.add(new SiripHiu());
                    break;
                case 12:
                    Deck.add(new DagingKuda());
                    break;
                case 13:
                    Deck.add(new DagingDomba());
                    break;
                case 14:
                    Deck.add(new DagingBeruang());
                    break;
                case 15:
                    Deck.add(new Accelerate());
                    break;
                case 16:
                    Deck.add(new Delay());
                    break;
                case 17:
                    Deck.add(new Destroy());
                    break;
                case 18:
                    Deck.add(new Protect());
                    break;
                case 19:
                    Deck.add(new Trap());
                    break;
                case 20:
                    Deck.add(new Labu());
                    break;
                case 21:
                    Deck.add(new Jagung());
                    break;
                case 22:
                    Deck.add(new Stroberi());
                    break;
                case 23:
                    Deck.add(new InstantHarvest());
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + cardType);
            }
        }
    }
}
