package javagame;

import java.util.ArrayList;
import java.util.Random;
import org.lwjgl.input.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.*;
import org.newdawn.slick.fills.GradientFill;
import org.newdawn.slick.geom.Shape;
import java.awt.Rectangle;
import java.io.*;
import java.util.*;
import javax.swing.*;
import java.lang.*;

//The fight class
//this is where the fight simulation occurs
//this is where characters health is changed depending
//on the outcome of the fight
//created by Haaroon Yousaf
//Based using the Slick Java Game library
//Archer, Warrior and Knight Sprite's taken from drawn.ca/ Creative Commons license
//All other images created by myself

public class Fight extends Task 
{
	//declare the variables
    protected int turn;
    protected double playerAttackPoints;
    protected double enemyAttackPoints;
    protected double playerDefensePoints;
    protected double enemyDefensePoints;
    protected double hit;
    Image fightimage;
    float x = 100f, y = 100f;
    String mouse;
    Character player; 
    //create a default boss to prevent compilation errors
    Character enemy= Boss.create("troll"); 
    public int v;
    double playerIn; 
    double type;
    ArrayList<Boss> theEnemy = new ArrayList<Boss>();
    
    
    
    public Fight(int state) {
    
   }
   
    public void update(GameContainer container, StateBasedGame stateGame, int omega) throws SlickException {	
    	Input gameInput = container.getInput();
    	
    	//if the key is down the player runs away
    	if (gameInput.isKeyDown(Input.KEY_DOWN)){
    		//run away
    		try {
    			//find the previous state
				readState();
			} catch (IOException e) {
				e.printStackTrace();
			}
    		//then leave the state
    			stateGame.enterState(v);
    	}
    	//if the key is up then the player attacks
    	if (gameInput.isKeyDown(Input.KEY_UP)){
    		//attack
    		attack(stateGame); 
    		try {
    			//put CPU to sleep to prevent multiple attacks from one press
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	//if the key is up then the player attacks
    	if (gameInput.isKeyDown(Input.KEY_LEFT)){
    		//Boost
    		arraylistuseBoost();
    		try {
    			//put CPU to sleep to prevent multiple attacks from one press
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    		
    	}
    	//if the key is right then the player uses a potion
    	if (gameInput.isKeyDown(Input.KEY_RIGHT)){
    		//Potion
    		arraylistusePotion();
    		try {
    			//put CPU to sleep to prevent multiple attacks from one press
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}    
    }
    
    public void render(GameContainer container, StateBasedGame stateGame, Graphics screenGraphics) throws SlickException {	
    	screenGraphics.drawImage(fightimage, 0, 0 ); //draw image to screen
    	
    	//create the health bars
    	//first make the background red and place it at a position
    	screenGraphics.setColor(Color.red);
        screenGraphics.fillRect(40.0f, 100.0f, 200.0f, 20.0f);
        //then on top make a yellow health bar on the same position
        screenGraphics.setColor(Color.yellow);
        screenGraphics.fillRect(40.0f, 100.0f, (float)+(player.health / 100 * 200.0f), 20.0f);
    	
        //same is done for the enemy health bar
    	screenGraphics.setColor(Color.red);
        screenGraphics.fillRect(440.0f, 100.0f, 200.0f, 20.0f);
        screenGraphics.setColor(Color.green);
        screenGraphics.fillRect(440.0f, 100.0f, (float)+(enemy.health / 100* 200.0f), 20.0f);
        screenGraphics.drawString("Coins:" + Hero.item.get(2), 250,8);
		screenGraphics.drawString("Potions:" + Hero.item.get(0), 420,8);
		screenGraphics.drawString("Boost:" + Hero.item.get(1), 580,8);

    }
    
    public void init(GameContainer container, StateBasedGame stateGame) throws SlickException {
    	//import the player and the enemy 
    	try{ 
            importPlayer();
    		} catch (IOException e) {
    			e.printStackTrace();
    		} 
    			try {
    				importEnemy();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    	//initialise their attack values
    	playerAttackPoints = (player.strength/10); 
        enemyAttackPoints = (enemy.strength/10); 
        playerDefensePoints =(player.intelligence/10);
        enemyDefensePoints = (enemy.intelligence/10);		
    } 
        	
    public double calculateAttack(Character temp)
    {
        //this method calculates the characters attack points based on their attributes
        double atkPoints = (temp.strength/10) + (temp.magic/10);
        return atkPoints;
    }
    
    public double calculateDefense(Character temp)
    {
        //this method calculates the characters defence points based on their attributes
        double dfnsPoints = (temp.strength/10) + (temp.intelligence/10);
        return dfnsPoints;
    }
    
    public void importPlayer() throws IOException{
    	//this method imports the player stats from an external file
    	BufferedReader buffer; //create the buffer
    	//input the values
		try {
			buffer = new BufferedReader( new InputStreamReader(new FileInputStream("res/Player.txt")));
			
			double valueSt;
			double valueRa;
			double valueIn;
			double valueMa;
			double valueHe;
			double valuePo;
			double valueBo;
			double valueCo;
    	    valueSt = Double.parseDouble(buffer.readLine());
    	    valueRa = Double.parseDouble(buffer.readLine());
			valueIn = Double.parseDouble(buffer.readLine());
			playerIn = valueIn; 
			valueMa = Double.parseDouble(buffer.readLine());
			valueHe = Double.parseDouble(buffer.readLine());
			valuePo = Double.parseDouble(buffer.readLine());
			valueBo = Double.parseDouble(buffer.readLine());
			valueCo = Double.parseDouble(buffer.readLine());
			//create the player
			player = new Hero(valueSt, valueRa , valueIn, valueMa, valueHe, valuePo,valueBo,valueCo);
			//close the buffer
			buffer.close();
    	    
		} catch (FileNotFoundException e) {
			//catch and throw error if file isnt found
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error, File not found Player.txt. Closing");
			System.exit(0); 		
		}
    }
public void importEnemy() throws IOException, SlickException{
    	//this method imports the enemy type to use in the fight
    	BufferedReader buffer;
		try {
			buffer = new BufferedReader( new InputStreamReader(new FileInputStream("res/Enemy.txt")));
			//read the value then spawn that enemy type
    	    type = Double.parseDouble(buffer.readLine());    	    
    	    
    	    if (type==0)
    	    {
    	    	enemy = Boss.create("troll");
    	    	enemy.health = 100;
    	    }
    	    if (type==1)
    	    {
    	    	enemy = Boss.create("magician");
    	    	enemy.health = 100;
    	    }
    	    if (type==2)
    	    {
    	    	enemy = Boss.create("dragon");
    	    	enemy.health = 100;
    	    }
			buffer.close();
			
		} catch (FileNotFoundException e) {
			//catch and display any erros
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error, File not found Player.txt. Closing");
			System.exit(0); 		
		}
		//the following spawns the specified fight scene depending on the boss type and the players intelligence value
		if ((type==0) && (playerIn==60))
		{
			//spawn goblin and warrior
	    	fightimage = new Image("res/sprites/warrior/fightscene-goblin.png");
		}
		if ((type==0) && (playerIn==70))
		{
			//spawn goblin and archer
	    	fightimage = new Image("res/sprites/archer/fightscene-goblin.png");
		}
		if ((type==0) && (playerIn==80))
		{
			//spawn goblin and knight
	    	fightimage = new Image("res/sprites/knight/fightscene-goblin.png");
		}
		
		if ((type==1) && (playerIn==60))
		{
			//spawn wizard and warrior
	    	fightimage = new Image("res/sprites/warrior/fightscene-wizard.png");
		}
		if ((type==1) && (playerIn==70))
		{
			//spawn wizard and archer
	    	fightimage = new Image("res/sprites/archer/fightscene-wizard.png");
		}
		if ((type==1) && (playerIn==80))
		{
			//spawn wizard and knight
	    	fightimage = new Image("res/sprites/knight/fightscene-wizard.png");
		}
		
		if ((type==2) && (playerIn==60))
		{
			//spawn dragon and warrior
	    	fightimage = new Image("res/sprites/warrior/fightscene-dragon.png");
		}
		if ((type==2) && (playerIn==70))
		{
			//spawn dragon and archer
	    	fightimage = new Image("res/sprites/archer/fightscene-dragon.png");
		}
		if ((type==2) && (playerIn==80))
		{
			//spawn dragon and knight
	    	fightimage = new Image("res/sprites/knight/fightscene-dragon.png");
		}
    }

	public void readState() throws IOException{
	//this method detects the previous state using File IO
	BufferedReader buffer;
	try {
		//read the state
		buffer = new BufferedReader( new InputStreamReader(new FileInputStream("res/state.txt")));
		double thestate;
		//place this value into V
	    v = Integer.parseInt(buffer.readLine());
	    //close the buffer
	    buffer.close();
		
		} 
		catch (FileNotFoundException e) {
		//catch any errors found
		e.printStackTrace();
		JOptionPane.showMessageDialog(null, "Error, File not found Player.txt. Closing");
		System.exit(0); 		
		}
	}
    
    public void attack(StateBasedGame stateGame) {
    	//this method performs the attacks on the enemy and the player
     		 System.out.println(playerAttackPoints);
    	
    	if (enemyDefensePoints >= playerAttackPoints)
             {
                 //take health from the enemy
                 enemy.health = enemy.health - playerAttackPoints;
                 
             }
             else
             {
                //the player gets hit
                 enemy.health = enemy.health-playerAttackPoints; 
                 player.health = player.health - enemyAttackPoints;
                 
             }
     		 
    		//if the players health is less than 0 then its game over
     		if (player.health<=0)
            {
               gameOver(player); 
            }
    	 
     		//if the enemies health is less than 0 then the player wins and game ends
     		 if (enemy.health<=0)
     		 {
     			 JOptionPane.showMessageDialog(null, "Enemy defeated. ");
     			JOptionPane.showMessageDialog(null, "You win. ");
     			System.exit(0);
     		 }

    }
    
    public void usePotion() {
    	//to use a potion, if there any available, 20 health is added 
    	//and 1 potion is removed from the players list
    	if (player.potion>=1)
    	{
    		player.health = player.health+20;
    		player.potion=player.potion-1;
    	}
    	
    }
    
    public void arraylistusePotion() {

    	//to use a potion, if there any available, 20 health is added 
    	//and 1 potion is removed from the players list
    	double potion = Hero.item.get(0);
    	
    	
    	if (potion>=1)
    	{
    		player.health = player.health+20;
    		Hero.item.set(0, (Hero.item.get(0)-1)); 
    		if (player.health>=100)
    		{
    			player.health=100;
    		}
    	}
    	
    
    }
    
    public void arraylistuseBoost() {

    	//to use a potion, if there any available, 20 health is added 
    	//and 1 potion is removed from the players list
    	double boost = Hero.item.get(1);
    	
    	if (boost>=1)
    	{
    		player.strength = player.strength+30;
    		playerAttackPoints = (player.strength/10);
    		Hero.item.set(1, (Hero.item.get(1)-1)); 
    	}
    }
    
    public void useBoost() {
    	//to use a boost, if there any available, 30 strength points is added 
    	//and 1 boost is removed from the players list
    	if (player.boost>=1)
    	{
    		player.strength = player.strength+30;
    		playerAttackPoints = (player.strength/10);
    		player.boost=player.boost-1;
    	}
    	
    }
    
    
	public int getID(){
		return 6;
	}
}

/*//this method imports the player stats from an external file
    	BufferedReader buffer; //create the buffer
    	//input the values
		try {
			buffer = new BufferedReader( new InputStreamReader(new FileInputStream("res/Player.txt")));
			
			double valueSt;
			double valueRa;
			double valueIn;
			double valueMa;
			double valueHe;
			double valuePo;
			double valueBo;
			double valueCo;
    	    valueSt = Double.parseDouble(buffer.readLine());
    	    valueRa = Double.parseDouble(buffer.readLine());
			valueIn = Double.parseDouble(buffer.readLine());
			playerIn = valueIn; 
			valueMa = Double.parseDouble(buffer.readLine());
			valueHe = Double.parseDouble(buffer.readLine());
			valuePo = Double.parseDouble(buffer.readLine());
			valueBo = Double.parseDouble(buffer.readLine());
			valueCo = Double.parseDouble(buffer.readLine());
			//create the player
			player = new Hero(valueSt, valueRa , valueIn, valueMa, valueHe, valuePo,valueBo,valueCo);
			//close the buffer
			buffer.close();
    	    
		} catch (FileNotFoundException e) {
			//catch and throw error if file isnt found
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error, File not found Player.txt. Closing");
			System.exit(0); 		
		}*/
