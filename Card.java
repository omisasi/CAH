/*
 * ICS4U FINAL PROJECT
 * OLIVIA MISASI
 */
public class Card
{ 
  private String colour;
  private String word;
  private int player;
  
  public Card()
  {    
  }
  public Card(String colour, String word) 
  { 
    this.colour = colour;
    this.word = word;
    int player = 0;
  }
  public String getColour()
  {
    return this.colour;
  }
  public String toString()
  {
    return this.word;
  }
  public int getPlayer()
  {
    return this.player;
  }
  public void setPlayer(int player)
  {
    this.player = player;
  }
  public void setColour(String colour)
  {
    colour = colour.toLowerCase();
    if(colour.equals("white")|| colour.equals("black"))
    {
      this.colour = colour;
    }
    else
    {
      System.out.println("INVALID INPUT");
    }
  }
  public void setString(String word)
  {
    this.word = word;
  }
}
