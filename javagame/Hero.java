package javagame;

import java.util.ArrayList;

//super class to create the characters
public class Hero extends Character
{
    // instance variables - replace the example below with your own
	static ArrayList<Double> item = new ArrayList();

    /**
     * Constructor for objects of class Hero
     */
    public Hero(double S, double R, double I, double M, double H, double P, double B, double C)
    {
       super(S,R,I,M,H,P,B,C);

       item.add(P);
       item.add(B);
       item.add(C);
    }
    
 public static Character create(String choice){
       //depending on which character name is passed, that character is spawned and returned
     
       if (choice.equalsIgnoreCase("warrior"))
        {
            Character player = new Character(75.0, 30.0, 60.0, 0.0, 100.0,1,1,500);
            //print("Character created");
            return player; 
        }
        else if (choice.equalsIgnoreCase("archer"))
        {
             Character player = new Character(68.0, 80.0, 70.0, 0.0, 100.0,1,1,500);
             //print("Character created");
             return player;
        }
        else if (choice.equalsIgnoreCase("knight"))
        {
            Character player = new Character(80.0, 30.0, 80.0, 45.0, 100.0,1,1,500); 
            //print("Character created");
            return player;
        }
        else
        {
            System.out.println("Not found try again.");
            //print("Character created");           
            System.exit(0);
        }

        return null;
    }
}
