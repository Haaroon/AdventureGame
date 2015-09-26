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

public class Missingno extends Task 
{
	//declare the variables
    protected int turn;
    protected double playerAttackPoints;
    protected double enemyAttackPoints;
    protected double playerDefensePoints;
    protected double enemyDefensePoints;
    protected double hit;
    Image fightimage ;
    float x = 100f, y = 100f;
    String mouse;
    Character player; 
    //create a default boss to prevent compilation errors
    Character enemy= Boss.create("troll"); 
    public int v;
    double playerIn; 
    double type;
    ArrayList<Boss> theEnemy = new ArrayList<Boss>();
    
    
    
    public Missingno(int state) {
    
   }
   
    public void update(GameContainer container, StateBasedGame stateGame, int omega) throws SlickException {	
    	Input gameInput = container.getInput();
    	
    	//if the key is down the player runs away
    	if (gameInput.isKeyDown(Input.KEY_DOWN)){
    	}
    	//if the key is up then the player attacks
    	if (gameInput.isKeyDown(Input.KEY_UP)){
    		//attack
    	
    		try {
    			//put CPU to sleep to prevent multiple attacks from one press
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}
    	//if the key is up then the player attacks
    	if (gameInput.isKeyDown(Input.KEY_LEFT)){
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
    	//if the key is right then the player uses a potion
    	if (gameInput.isKeyDown(Input.KEY_RIGHT)){
    		//Potion
    		
    		try {
    			//put CPU to sleep to prevent multiple attacks from one press
				Thread.sleep(250);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
    	}    
    }
    
    public void render(GameContainer container, StateBasedGame stateGame, Graphics screenGraphics) throws SlickException {	
    	fightimage = new Image("res/missingno.png");
    	screenGraphics.drawImage(fightimage, 0, 0 ); //draw image to screen
    }
    
    public void init(GameContainer container, StateBasedGame stateGame) throws SlickException {
    	//import the player and the enemy 
    	try{ 
            importPlayer();
    		} catch (IOException e) {
    			e.printStackTrace();
    		} 
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
			valuePo = 99;
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
    
  
    
    public void usePotion() {
    	//to use a potion, if there any available, 20 health is added 
    	//and 1 potion is removed from the players list
    	if (player.potion>=1)
    	{
    		player.health = player.health+20;
    		player.potion=player.potion-1;
    	}
    	
    }
    
    
	public int getID(){
		return 99;
	}
}