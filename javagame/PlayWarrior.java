package javagame;
//created by Haaroon Yousaf
//Based using the Slick Java Game library
//Archer, Warrior and Knight Sprite's taken from drawn.ca/ Creative Commons license
//All other images created by myself

import org.lwjgl.input.*;
import java.io.*;
import java.util.*;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.*;
import javax.swing.*;


public class PlayWarrior extends BasicGameState{

	//variables for the game
	Image gamemap; //declare the game map
	float mapX = 0; //declare and initialise the maps co-ordinates
	float mapY = 0; 
	Animation characterSprite, up, down, left, right; //declare the animation for the characters
	float characterXposition = 0, characterYposition = 0; //declare the characters positions
	float startX= characterXposition+350, startY = characterYposition+150;
	Character player = Hero.create("archer"); //create a default character to prevent errors occuring during compilation
	File textfile = new File("res/characterchoice.txt"); //declare and initilise the text file that will be used to check which character has been chosen
	Random gen= new Random(); //random generator
	int r=0;
	public String randomnum = "none";
	public String currentPosition = "No input yet";
	public static int spawn = 0; //0 is goblin, 1 is dragon, 2 is wizard
	public static int currentstate=3; 
	Image shopimage; //declare the shop image
	int shopimageX = 0; //declare and initialise the sop images co-ordinates
	int shopimageY = 0;
	boolean shop = false; //boolean value that indicates if the shop is open or closed
	
	
	public PlayWarrior(int state){
		
	}
	
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		String chosen = Select.chosen; //value to check what state the choice came from
		shopimage = new Image("res/shopimage2.png"); //links the shop image
		
		
		
		//list to initialise and declare the sprite movements
		player = Hero.create("warrior");
    	Image up1 = new Image("res/sprites/warrior/avt1_bk1.png");
    	Image up2=  new Image("res/sprites/warrior/avt1_bk2.png");
    	Image down1 = new Image("res/sprites/warrior/avt1_fr1.png");
    	Image down2=  new Image("res/sprites/warrior/avt1_fr2.png");
    	Image left1 = new Image("res/sprites/warrior/avt1_lf1.png");
    	Image left2=  new Image("res/sprites/warrior/avt1_lf2.png");
    	Image right1 = new Image("res/sprites/warrior/avt1_rt1.png");
    	Image right2=  new Image("res/sprites/warrior/avt1_rt2.png");
  	    Image fightscene = new Image("res/sprites/warrior/fightscene-goblin.png");//image for the fightscene
       
         gamemap = new Image("res/gamemap.png"); //create new object for image
         
		//Array images for the players movement
        Image [] movingUp = {up1,  up2};
        Image [] movingDown = {down1, down2};
  		Image [] movingLeft = {left1, left2};
  		Image [] movingRight = {right1, right2};
		int [] duration = {300, 300}; //how quick to change sprites
		
		//animation will only change when the user presses a key
		up = new Animation(movingUp, duration, false);
		down = new Animation(movingDown, duration, false);
		left = new Animation(movingLeft, duration, false);
		right = new Animation(movingRight, duration, false); 

		characterSprite = right; //when starting this is the direction the character will be looking
	}

	public void render(GameContainer container, StateBasedGame stateGame, Graphics screenGraphics) throws SlickException {	
		 
		//this draws the game map onto the screeen
		gamemap.draw(characterXposition, characterYposition); 
		
		//screenGraphics.drawImage(gamemap,mapX, mapY);
		//this draws the sprite onto the map
		characterSprite.draw(startX, startY);
		
		//this draws the characters co-ordinates onto the map
		screenGraphics.drawString(currentPosition, 0,30);
		//g.drawString(randomnum, 450,00); 
		//the following lines draw the HUD to the screen
		screenGraphics.drawString("Coins:" + Hero.item.get(2), 250,8);
		screenGraphics.drawString("Potions:" + Hero.item.get(0), 420,8);
		screenGraphics.drawString("Boost:" + Hero.item.get(1), 580,8);
		
		//this method is done to display the shop image to the screen only when
		//the shop boolean is true, so the character is in the shop
		if (shop==true) {
			//show the shop in the top right corner
			screenGraphics.drawImage(shopimage, 500, 35);
			if(shop==false){
				//once they leave the shop remove the image
				screenGraphics.clear();
			}
		}
	     

	}
	
	public void update(GameContainer container, StateBasedGame stateGame, int omega) throws SlickException {	
		Input gameInput = container.getInput();
		//Display the co-ordinates of the character on screen
		currentPosition = "X:" + characterXposition + "Y: " + characterYposition; 
		//randomnum = "R IS: " + r; 

		//if the following is true then the character is moved
		if (gameInput.isKeyDown(Input.KEY_UP))
		{
			//turn him to face upwards
		    characterSprite = up;
		    //update this
		    characterSprite.update(omega);
		    //move the map a certain position
		    //mapY += 0.15;
		    //change the characters Y co-ordinate, The lower the value the slowest the sprite will animate
		    characterYposition += omega * 0.1f;
		    //then check if he is either in a shop zone or a fight zone
		    checkShop(stateGame);
		    checkFight(stateGame);
		    checkMissingno(stateGame);
		   //stop character moving off screen and stop map moving
		    if(characterYposition>150) {
		    	characterYposition -= omega * 0.1f;
		    	//mapY -= 0.15;
		    }
		}
		if (gameInput.isKeyDown(Input.KEY_DOWN))
		{
			//turn him to face downwards
		    characterSprite = down;
		    //update this
		    characterSprite.update(omega);
		    //move the map a certain position
		    //mapY -= 0.15;
		    //change the characters Y co-ordinate, The lower the value the slowest the sprite will animate
		    characterYposition -= omega * 0.1f;
		    //then check if he is either in a shop zone or a fight zone
		    checkShop(stateGame);
		    checkFight(stateGame);
		    checkMissingno(stateGame);
		    //stop character moving off screen and stop map moving
		    if(characterYposition<-1055) {
		    	characterYposition += omega * 0.1f;
		    	//mapY += 0.15;
		    }
		}   
		
		if (gameInput.isKeyDown(Input.KEY_LEFT))
		{
			//turn him to face leftwards
		    characterSprite = left;
		    //update this
		    characterSprite.update(omega);
		    //move the map a certain position
		    //mapX +=0.15;
		    //change the characters Y co-ordinate, The lower the value the slowest the sprite will animate
		    characterXposition += omega * 0.1f;
		    //then check if he is either in a shop zone or a fight zone
		    checkShop(stateGame);
		    checkFight(stateGame);
		    checkMissingno(stateGame);
		    //stop character moving off screen and stop map moving
		    if(characterXposition> 345 ) {
		    	characterXposition -= omega * 0.1f;
		    	//mapX -= 0.15;
		    }
		    
		}
		
		if (gameInput.isKeyDown(Input.KEY_RIGHT))
		{
			//turn him to face to the right
			characterSprite = right;
			//update this
		    characterSprite.update(omega);
		    //move the map a certain position
		    //mapX -=0.15;
		    //change the characters Y co-ordinate, The lower the value the slowest the sprite will animate
		    characterXposition -= omega * 0.1f;
		    //then check if he is either in a shop zone or a fight zone
		    checkShop(stateGame);
		    checkFight(stateGame);
		    checkMissingno(stateGame);
		    //stop character moving off screen and stop map moving
		    if(characterXposition< -862) {
		    	characterXposition += omega * 0.1f;
		    	//mapX += 0.15;
		    }
		    
		}
		
		//these statements are the orders for the shop
		//if the user is in the shop
		if (shop==true)
		{
			//if they press P
			if (gameInput.isKeyDown(Input.KEY_P))
			{
				//they will call method buyPotion
				arraylistbuyPotion();
				//then the cpu will sleep to prevent multiple purchases
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
						e.printStackTrace();
				} 
				
			}
			//if they  press B
			if (gameInput.isKeyDown(Input.KEY_B))
			{
				//they will call method buyBoost
				arraylistbuyBoost();
				//then the cpu will sleep to prevent multiple purchases
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				} 
			}
			//if they  press ESCAPE key
			if (gameInput.isKeyDown(Input.KEY_ESCAPE))
			{
				//they will then leave the shop
				leaveShop();
				//then the cpu will sleep to prevent multiple button presses
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			
			
			
		}
	
	}
	
	public void checkShop(StateBasedGame stateGame)
	{
		Graphics screenGraphics = null;
		//if the character is in the shop area then open shop screen
		if ( (characterXposition<-43 && characterXposition>-277) && (characterYposition>66&&characterYposition<100)){ 
				//export the players values and the state
				exportPlayer();
				exportState();
				shop=true; 
			}
		else
		{
			shop=false; 
		}
	}
	
	
	
	public void checkFight(StateBasedGame sbg)
	{
		//if in left wild then spawn Goblin
		if ( (characterXposition>-122 && characterXposition<297) && (characterYposition>-505&&characterYposition<-80)){ 
			//generate a random number
			r = gen.nextInt(999999);
			System.out.println(r);
			if (r>997990)
			{
				//export their states and the enemy type
				exportPlayer();
				exportEnemy("0");
				//put CPU to sleep to prevent multiple exports
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//enter fight state
				sbg.enterState(6);
			}
	    }
		//if in left wild then spawn wizard
		else if ((characterXposition>-825&& characterXposition<-472) && (characterYposition>-550&&characterYposition<-212)){
			//generate a random number
			r = gen.nextInt(999999);
			System.out.println(r);
			if (r>997990)
			{
				//export their states and the enemy type
				exportPlayer();
				exportEnemy("1");
				//put CPU to sleep to prevent multiple exports
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				//enter fight state
				sbg.enterState(6);
			}
		}
		//if in left wild then spawn dragon
		else if ((characterXposition>-672 && characterXposition<185) && (characterYposition<-711&&characterYposition>-973)){
			//generate a random number
			r = gen.nextInt(999999);
			System.out.println(r);
			if (r>997990)
			{
				//export their states and the enemy type
				exportPlayer();
				exportEnemy("2");
				//put CPU to sleep to prevent multiple exports
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				//enter fight state
				sbg.enterState(6);
			}
		}
		else {
			//do nothing
			}
	}
	
	public void checkMissingno(StateBasedGame sbg)
	{
		if ( (characterXposition>343 && characterXposition<345) && (characterYposition>-1055&&characterYposition<-900)){ 
			//generate a random number
			r = gen.nextInt(999999);
			System.out.println(r);
			if (r>998950)
			{
				//export their states and the enemy type
				exportPlayer();
				//put CPU to sleep to prevent multiple exports
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				//enter fight state
				sbg.enterState(99);
			}
			double potion = Hero.item.get(0);
			
			
			if (true)
			{
				//one potion is added
				Hero.item.set(0, (potion+99)); 
				//50 coins are removed
			}

	    }
		
		
	}
	
	public void exportPlayer(){
		//this methods exports the players stats to a text file
		PrintWriter printer; //create the printer 
		try {
			//link the file then print on each line
			printer = new PrintWriter("res/Player.txt");
				printer.println(player.strength);
			    printer.println(player.range); 
			    printer.println(player.intelligence);
			    printer.println(player.magic); 
			    printer.println(player.health);
			    printer.println(player.potion);
			    printer.println(player.boost);
			    printer.println(player.coins);
			//then close the printer to prevent leaks
			printer.close();
		} catch (FileNotFoundException e) {
			//if the file is not found the display message
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Player.txt file not found");
		}
		exportState();
		
	}
	
public void exportEnemy(String v){
	//this methods exports the enemy type to a text file
		PrintWriter printer;//create the printer 
		try {
			//link the file then print on each line
			printer = new PrintWriter("res/enemy.txt");
				printer.println(v);
		    //then close the printer to prevent leaks
			printer.close();
		} catch (FileNotFoundException e) {
			//if the file is not found the display message
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Enemy.txt file not found");
		}
		
	}
public void exportState(){
	//this methods exports the current state to a text file
	PrintWriter printer;//create the printer 
	try {
		//link the file then print on each line
		printer = new PrintWriter("res/state.txt");
			printer.println("5");
		//then close the printer to prevent leaks
		printer.close();
	} catch (FileNotFoundException e) {
		//if the file is not found the display message
		e.printStackTrace();
		JOptionPane.showMessageDialog(null, "Enemy.txt file not found");
	}
	
}

public void buyPotion()
{
	//Buy potion method, if the player has enough coins then
			if (player.coins>=50)
			{
				//one potion is added
				player.potion = player.potion+1; 
				//50 coins are removed
				player.coins = player.coins -50;
				
			}
			else
			{
				//nothing is bought
			}
}

public static void arraylistbuyPotion()
{
	double coins = Hero.item.get(2);
	double potion = Hero.item.get(0);
	
	
	if (coins >=50)
	{
		//one potion is added
		Hero.item.set(0, (potion+1)); 
		//50 coins are removed
		Hero.item.set(2, (coins-50));
		coins = Hero.item.get(2);
	}

}

public static void arraylistbuyBoost()
{
	double coins = Hero.item.get(2);
	double boost = Hero.item.get(1);
	
	
	if (coins >=150)
	{
		//one boost is added
		Hero.item.set(1, (boost+1)); 
		//150 coins are removed
		Hero.item.set(2, (coins-150));
		coins = Hero.item.get(2);
	}

}




public void buyBoost()
{
	//Buy Boost method, if the player has enough coins then
		    if (player.coins>=150)
			{
		    	//one boost is added
				player.boost = player.boost+1; 
				//150 coins are removed
				player.coins = player.coins -150; 
			}
			else
			{
				//nothing is bought
			}
	
}

public void leaveShop()
{
	//shop boolean is changed
	shop=false;
}
	
	public int getID(){
		return 5;
	}	
}