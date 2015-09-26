package javagame;/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
 

import java.util.*;

import javax.swing.*;

/**
 *
 * @author Haaroon
 * This is apart of the level 1 method, this allows the person to simply
 * print out the statistics of the character by inputting their name. 
 */
public class Character  {
        //private questOLD questgame;
        //instance variables for the attributes of the character s
    protected double strength;
    protected double range; 
    protected double intelligence;
    protected double magic; 
    protected double health;
    protected double potion;
    protected double boost;
    protected double coins;
    
	
    
    
    public Character(double S, double R, double I, double M, double H, double P, double B, double C)
    {   
        //initialise the values per character 
        strength = S;
        range = R;
        intelligence = I;
        magic = M;
        health = H; 
        potion =P;
        boost =B;
        coins = C;
        
        
    }
    
    public static void main(String[] args)
    {
       
       //game();
       
    }
        

    
   /*public static void game() {
          //calls the method to print the Character stats, and also parses value inputted by user          
        String chchoice;
        chchoice = JOptionPane.showInputDialog("Choose a character: warrior, archer or knight?");
        Character player;     
       // player = Hero.createcharacter(chchoice);
        print("Well done, you are now a " + chchoice);

            
   } */
   
   public static void checkStats()
   {
       //method to check the charaters stats if needed
       characterprint(data("Which character's stats "
                       + "would you like to see? (magician, troll, dragon, warrior, archer, knight"));
   }
   //getter methods to return values if needed
    public double getStrength()
    {
        return strength;
    }
    
    public double getRange()
    {
        return range;
    }
    
    public double getIntelligence()
    {
        return intelligence;
    }
    
    public double getMagic()
    {
        return magic;
    }
    
     public double getHealth()
    {
        return health;
    }
     public double getPotion()
     {
         return potion;
     }
     public double getBoost()
     {
         return boost;
     }
     public double getCoins()
     {
         return coins;
     }
    
    //values for the characters
    public static void characterprint(String c)
    {
       //depending on which character name is passed, that character is spawned
        if (c.equalsIgnoreCase("magician"))
        {
            print( c + "s Stats. \n Strength = 40 \n Range = 80 \n Intelligence = 75 \n Magic = 80.");
        }
        else if (c.equalsIgnoreCase("troll"))
        {
           print( c + "s Stats \n Strength = 55 \n Range = 30 \n Intelligence = 20 \n Magic = 45.");
        }
        
        else if (c.equalsIgnoreCase("dragon"))
        {
            print( c + "s Stats \n Strength = 91 \n Range = 70 \n Intelligence = 50 \n Magic = 0.");
        }
        else if (c.equalsIgnoreCase("warrior"))
        {
           print( c + "s Stats \n Strength = 75 \n Range = 30 \n Intelligence = 70 \n Magic = 0.");
        }
        else if (c.equalsIgnoreCase("archer"))
        {
            print( c + "s Stats \n Strength = 68 \n Range = 80 \n Intelligence = 70 \n Magic = 0.");
        }
        else if (c.equalsIgnoreCase("knight"))
        {
            print( c + "s Stats \n Strength = 80 \n Range = 30 \n Intelligence = 70 \n Magic = 0.");
        }
        else
        {
            print("Not found.");
        }
        
    }
    public static String data(String n)
    {
        String value;
        value = JOptionPane.showInputDialog(n);
        return value;
    }
    public static void print(String n)
    {
        //gameResponse.setText(n);
        JOptionPane.showMessageDialog(null, n);    
    }
    
    public static Character create(String choice)
    {
        //interface to allow polymorphism
    	if (choice.equals(choice))
    	{
    	Character basicplayer = new Character(100,50,70 ,80 ,10 ,0 ,0 ,500);
        return basicplayer;
    	}
    	return null;
    }
    
}


    




























































































































