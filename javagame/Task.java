package javagame;//interface method to link the quest and the fights together
import javax.swing.*;

import org.newdawn.slick.state.BasicGameState;
public abstract class Task extends BasicGameState
{
    // instance variables - replace the example below with your own
    
    public void startGame(Character player)
    {
        // interface
        
    }
    public static void print(String n)
    {
        //short hand method to print 
        //gameResponse.setText(n);
        JOptionPane.showMessageDialog(null, n);    
    }
    public static String data(String n)
    {
        //short hand method to return values
        String value;
        value = JOptionPane.showInputDialog(n);
        return value;
    }
    public void gameOver(Character player)
    {   
        //game over method, allows person to continue or quit game
        String choice;
        print("You have been defeated");
        print("Game Over");
        System.exit(0);
        player.health=100;
        
    }
    public void attack(Character a, Character b)
    {
    }
}
