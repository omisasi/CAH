/**
 * ICS4U FINAL PROJECT
 * OLIVIA MISASI
 */
import java.util.Random;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.PrintStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.Thread; //Thread.sleep();
public class CAH 
{
  public static void main(String[] args) throws FileNotFoundException, IOException
  {
    Scanner input = new Scanner(System.in);
    int game = 0;
    int nPlayers = 0;
    Stack blackCards = new Stack();
    Stack whiteCards = new Stack();
    
    System.out.println("BEFORE WE BEGIN, PLEASE SET THE CONSOLE WINDOW TO ITS FULL SIZE");   
    
    sleep();
    
    System.out.println("PRESS ENTER TO START");
    input.nextLine();
    
    while(true)
    {
      System.out.println("WHAT VERSION OF THE GAME WOULD YOU LIKE TO PLAY? CLEAN (1) OR DIRTY (2): ");
      game = input.nextInt();

      if(game == 1 ^ game == 2)
      {
        ArrayList<Card> wcDeck = fillArrayListWhite(game);
        ArrayList<Card> bcDeck = fillArrayListBlack();
        
        whiteCards = fillStack(wcDeck);
        blackCards = fillStack(bcDeck);
        
        System.out.println("HOW MANY PLAYERS ARE IN THIS GAME (3-5): ");
        nPlayers = input.nextInt();
        if(nPlayers >= 3 && nPlayers <= 5)
        {
          ArrayList<Player> players = new ArrayList<Player>();
          initiatePlayers(players, nPlayers); //fill the arraylist of players 
          
          System.out.println("DEALING CARDS...");
          
          sleep();
          
          System.out.println("CARDS DEALT");
          dealCards(players, whiteCards); //each player gets a hand of 5 cards
         
          runRound(players, whiteCards, blackCards);
        }
        break;
      }
      else
      {
        System.out.println("INVALID INPUT");
      }
    }
    input.close();
  }
  public static ArrayList<Card> fillArrayListWhite(int game) throws FileNotFoundException
  {
    ArrayList<Card> deck = new ArrayList<Card>();
    if(game == 1)
    {
      Scanner input = new Scanner(new File("WCClean.txt"));
      while(input.hasNextLine())
      {
        String word = input.nextLine();
        Card card = new Card("white", word);
        deck.add(card);
      }
      input.close();
    }
    else
    {
      Scanner input = new Scanner(new File("WCDirty.txt"));
      while(input.hasNextLine())
      {
        String word = input.nextLine();
        Card card = new Card("white", word);
        deck.add(card);
      }
      input.close();
    }
    shuffle(deck);
    return deck;
  }
  
  public static ArrayList<Card> fillArrayListBlack() throws FileNotFoundException
  {
    ArrayList<Card> deck = new ArrayList<Card>();
    Scanner input = new Scanner(new File("BCany.txt"));
    while(input.hasNextLine())
    {
      String word = input.nextLine();
      Card card = new Card("black", word);
      deck.add(card);
    }
    input.close();
    shuffle(deck);
    return deck;
  }
  
  public static Stack fillStack(ArrayList<Card> list)
  {
    Stack deck = new Stack();
    
    for(int i = 0; i < list.size(); i++)
    {
      Card card = new Card(list.get(i).getColour(), list.get(i).toString());
      deck.push(card); //adds the card at the index i of the ArrayList into the Stack
    }    
    return deck;
  }
  
  public static void shuffle(ArrayList<Card> list)
  {
    Random rInt = new Random();
    
    for(int i = 0; i < list.size(); i++) //traverse the list
    {
      int rPosition = rInt.nextInt(list.size()); //Random number lies within the size of the ArrayList
      Card temp = new Card(list.get(i).getColour(), list.get(i).toString());
      Card rand = new Card(list.get(rPosition).getColour(), list.get(rPosition).toString());//hold the Card of ArrayList at index i
      list.set(i, rand); //sets the original Card at index i to the Card at the random index
      list.set(rPosition, temp); //sets the Card at the random index to the original card      
    }
  }
  
  public static void initiatePlayers(ArrayList<Player> players, int nPlayers)
  {
    Scanner input = new Scanner(System.in);
    String name = "";
    for(int i = 0; i < nPlayers; i++)
    {
      System.out.println("NEW PLAYER, WHAT IS YOUR NAME: ");
      name = input.nextLine();
      Player player = new Player(name);
      players.add(player);
    }
    input.close();
  }
  
  public static void dealCards(ArrayList<Player> players, Stack deck)
  {
    int nSize = deck.size() - (players.size() * 6);
    while(deck.size() > nSize)
    {
      for(int i = 0; i < players.size(); i++)
      {
        Card card = new Card("white", deck.pop());
        card.setPlayer(i);
        players.get(i).addCard(card);
      }
    }
  }
  
  public static int generateRandom(ArrayList<Player> players)
  {
    Random rand = new Random();
    int rInt = rand.nextInt(players.size() - 1);
    
    return rInt;
  }
  
  public static void runRound(ArrayList<Player> players, Stack whiteCards, Stack blackCards) throws FileNotFoundException
  {
    int judge = generateRandom(players);
    final int lines = 30;
    boolean instructions = true;
    Scanner input = new Scanner(System.in);
    int card = 0;
    Card getCard = new Card();
    
    ArrayList<Card> chosenCards = new ArrayList<Card>();
    int winner = 0;
    int pWin = 0;
    
    while(instructions)
    {
      final String blackCard = blackCards.pop();
      System.out.println("JUDGE FOR THE ROUND IS: ");
      System.out.println(players.get(judge).getName().toUpperCase());
      System.out.println(" ");
      
      sleep();
      
      for(int i = 0; i < players.size(); i++)
      {
        if(i != judge)
        {
          System.out.println("THE BLACK CARD FOR THE ROUND IS: ");
          System.out.println(blackCard);
          System.out.println(" ");
          System.out.println(players.get(i).getName().toUpperCase() + " HERE IS YOUR HAND: ");
          players.get(i).showHand();
          System.out.println("PICK A CARD TO PLAY (enter card number)");
          card = input.nextInt();
          
          if(card > players.get(i).getHandSize())
          {
            instructions = false;
            System.out.println("GAME HAS ENDED. YOU CAN'T FOLLOW INSTRUCTIONS.");
            break;
          }
          //remove choice from hand and put it in a new list for the judge to choose from
          getCard = players.get(i).removeCard(card - 1);
          chosenCards.add(getCard); 
          
          clear(lines);
        }
      }
      if(instructions == false)
      {
        break;
      }
      else {
        System.out.println("JUDGE, HERE ARE YOUR CARDS TO CHOOSE FROM");
      for(Card c: chosenCards)
      {
        System.out.println(c.toString());
      }
      System.out.println("ENTER THE NUMBER OF THE WINNING CARD: ");
      winner = input.nextInt();  
      
      if(winner > chosenCards.size())
      {
        System.out.println("THANKS FOR ENDING THE GAME EARLY");
      }
      
      pWin = chosenCards.get(winner - 1).getPlayer();
      players.get(pWin).incrementScore();
      
      chosenCards.clear(); //discards the cards played in the round
      
      //changes the judge everytime
      if(judge < players.size() - 1)
      {
        judge++;
      }
      else
      {
        judge = 0;
      }
  
      //if everyone has no cards left to play
      if(checkHands(players))
      {
        String again = "";
        System.out.println("WOULD YOU LIKE TO PLAY ANOTHER ROUND: (yes OR no)");
        input.nextLine();
        again = input.nextLine();
        
        if(again.toUpperCase().equals("yes"))
        {
          System.out.println("DEALING CARDS...");
          
          sleep();
          
          System.out.println("CARDS DEALT");
          dealCards(players, whiteCards); //each player gets a hand of 5 cards
         
          runRound(players, whiteCards, blackCards);
          input.close();
        }
        else
        {
        declareWinner(players);
        System.out.println("THANKS FOR PLAYING");
        input.close();
        }
        break;
      }
      }
    }
  }
  
  public static boolean checkHands(ArrayList<Player> players)
  {
    int count = 0;
    Boolean bool = true;
    for(int i = 0; i < players.size(); i++)
    {
      if(players.get(i).handIsEmpty() == false)
      {
        count++;
      }
    }
    if(count != 0)
    {
      bool = false;
    }
    return bool;
  }
  
  public static void clear(int lines)
  {
    for(int i = 0; i < lines; i++)
    {
      System.out.println(" ");
    }
  }
  
  public static void declareWinner(ArrayList<Player> players) throws FileNotFoundException
  {
    int highscore = players.get(0).getScore(); //score of first player
    int change = 0; //the winning player
    
    for(int i = 0; i < players.size(); i++)
    {
      if(players.get(i).getScore() > highscore) // if another player's score is higher, change score and player
      {
        highscore = players.get(i).getScore();
        change = i;
      }
    }   
    PrintStream output = new PrintStream("CAHWinner.txt");
    output.println("WINNER IS: " + players.get(change).getName());
    output.println("SCORE: " + highscore);
    output.close();
  }
  
  public static void sleep()
  {
    try
      {
        Thread.sleep(3000);
      }
      catch (InterruptedException ex)
      {
        
      }
  }
}