/**
 * ICS4U FINAL PROJECT
 * OLIVIA MISASI
 */
import java.util.ArrayList;
public class Stack
{
  private int top;
  private ArrayList<Card> stack;
  
  public Stack()
  {
    stack = new ArrayList<Card>();
    top = 0;
  }
  
  public void push(Card obj)
  {
    if(obj == null)
    {
      
    }
    else
    {
      stack.add(obj);
      top++;
    }
  }
  
  public String pop() //must be String method, otherwise returns the Card address
  {
    if(stack.size() != 0)
    {
      Card temp = stack.remove(top - 1);
      top--;
      return temp.toString(); //returns ONLY the word, not the colour
    }
    else
    {
      return null;
    }
  }
  
//  public Object peek()
//  {
//    if(top > 0)
//    {
//      return stack.get(top - 1);
//    }
//    else
//    {
//      return 0;
//    }
//  }
  
  public int size()
  {
    return stack.size();
  }  
  
  public boolean isEmpty()
  {
    if(stack.size() == 0)
    {
      return true;
    }
    else
    {
      return false;
    }
  }
}
