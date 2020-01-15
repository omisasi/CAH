/**
 * ICS4U FINAL PROJECT
 * OLIVIA MISASI
 */
import java.util.ArrayList;
public class Player
{
  private String name;
  private ArrayList<Card> hand;
  private int score;
  
  public Player(String name) 
  {
    this.name = name;
    hand = new ArrayList<Card>();
    score = 0;
  }  
  public String getName()
  {
    return name;
  }
  public ArrayList<Card> getHand()
  {
    return hand;
  }
  public void showHand()
  {
    for(Card c: hand)
    {
      System.out.println(c.toString());
    }
  }
  public int getHandSize()
  {
    return hand.size();
  }
  public void addCard(Card card)
  {
    hand.add(card);
  }
  public Card removeCard(int card)
  {
    return hand.remove(card);
  }
  public boolean handIsEmpty()
  {
    if(getHandSize() == 0)
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  public void incrementScore()
  {
    score++;
  }
  public int getScore()
  {
    return score;
  }
}
