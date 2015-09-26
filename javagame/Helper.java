package javagame;

//class to spawn the helper character
public class Helper extends Character
{
    
    public Helper(double S, double R, double I, double M, double H)
    {
       super(S,R,I,M,H,0,0,0);
    }

    
    public static Character create(String choice)
    {
       //depending on which character name is passed, that character is spawned and returned
        Character witch = new Helper(60.0, 60.0, 60.0, 60.0, 100.0);
        return witch;
    }
}
