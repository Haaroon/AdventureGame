package javagame;

 

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.lang.*;
//Main game class
//this class is where the game starts, and this class calls 
//the relevant methods of the game. 

public class AdventureGame extends JFrame{
    

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public AdventureGame()
    { 
    }

    public static void main(String[] args) {
  
       //create a new player 
        // GUI rectObj = new GUI();
        Character player;
        String chchoice;
        //ask person what player they want
        chchoice = data("Choose a character: warrior, archer or knight?");
        //create this person
        player = Hero.create(chchoice);
        //link the levels
        //Task levels = new Quest();
        //Quest levels = new Quest();
        //levels.startGame(player);
           
    }
    
    public static void print(String n)
    {   //easy method to print out the game
        //gameResponse.setText(n);
        JOptionPane.showMessageDialog(null, n);    
    }
    public static String data(String n)
    {
        //short hand method to return valus
        String value;
        value = JOptionPane.showInputDialog(n);
        return value;
    }
    public void gameOver(Character player)
    {
        //game over method called when players health reaches 0 or lower
        String choice;
        print("You have been defeated");
        print("Game Over");
        //give them choice, if they continue spawn health otherwise close game
        choice = data("Continue? Y/N");
        if (choice.equalsIgnoreCase("N"))
        {
        System.exit(0);
        }
        else
        {
            player.health=100;
        }
    }
}
   


        