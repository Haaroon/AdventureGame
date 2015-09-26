package javagame;


//class to spawn bosses
public class Boss extends Character
{
    // instance variables - replace the example below with your own
    

    /**
     * Constructor for objects of class Boss
     */
    public Boss(double S, double R, double I, double M, int H)
    {
        // initialise instance variables
        super(S,R,I,M,H,0,0,0);
        
    }
    public static Character create(String choice)  {
        //depending on which character name is passed, that character is spawned and returned
        if (choice.equalsIgnoreCase("magician"))
        {
            Character magician = new Character(40.0, 80.0, 75.0, 80.0, 100,0,0,0);
            return magician;
        }
        else if (choice.equalsIgnoreCase("troll"))
        {
            Character troll = new Character(55.0, 30.0, 20.0, 45.0, 100.0,0,0,0);
            return troll;
        }
        else if (choice.equalsIgnoreCase("dragon"))
        {
            Character dragon = new Character(91.0, 70.0, 50.0, 0.0, 100.0,0,0,0);
            return dragon;
        }
        else 
        {
             Character troll = new Character(55.0, 30.0, 20.0, 45.0, 100.0,0,0,0);
             return troll;
        }
    }
    
}
