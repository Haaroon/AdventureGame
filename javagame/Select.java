package javagame;
//created by Haaroon Yousaf
//Based using the Slick Java Game library
//Archer, Warrior and Knight Sprite's taken from drawn.ca/ Creative Commons license
//All other images created by myself
//This class is the character select menu of the game screen

import org.lwjgl.input.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.*;
import java.io.*;
import java.util.*;
import javax.swing.*;



public class Select extends BasicGameState{

	Character player;
	Image chooseimage;
	int chooseimageX = 0;
	int chooseimageY = 0;
	File textfile = new File("res/characterchoice.txt");
	public static String chosen = "3"; 
	
	public Select(int state){
	}
	
	public void init(GameContainer container, StateBasedGame stateGame) throws SlickException {
		//initialise the image to the object
		chooseimage = new Image("res/chooseimage.png"); 

	}

	public void render(GameContainer container, StateBasedGame stateGame, Graphics screenGraphics) throws SlickException {	
		//draw the image to the sceen at the specified co-ordinates
		screenGraphics.drawImage(chooseimage, chooseimageX, chooseimageY);
	}
	
	public void update(GameContainer container, StateBasedGame stateGame, int omega) throws SlickException {	
		//this class will always run checking for inputs
		//intialise that there will be inputs drawn into the game
		Input gameInput = container.getInput();
		
		//initialise the positions of the users mouse 
		int xPosition = Mouse.getX(); 
		int yPosition = Mouse.getY();
		

		//Knight, if the user clicks this section of the screen then perform to spawn that character
		if (xPosition>46&&xPosition<146 && (yPosition>4&&yPosition<146)){ 
			if(gameInput.isMouseButtonDown(0)){
				chosen="1";
				JOptionPane.showMessageDialog(null, "You have chosen a Knight - "+ chosen);
				//enter the Knights game state
				stateGame.enterState(4);
			}
		};
		
		//warrior, if the user clicks this section of the screen then perform to spawn that character
		if (xPosition>272&&xPosition<372 && (yPosition>4&&yPosition<146)){ 
			if(gameInput.isMouseButtonDown(0)){
				chosen="2";
				JOptionPane.showMessageDialog(null, "You have chosen a Warrior  - " + chosen);
				//enter the warriors game state
				stateGame.enterState(5);
			}
		};
		//archer,if the user clicks this section of the screen then perform to spawn that character
		if (xPosition>524&&xPosition<624 && (yPosition>4&&yPosition<146)){ 
			if(gameInput.isMouseButtonDown(0)){
				chosen="3"; 
				JOptionPane.showMessageDialog(null,"You have chosen an Archer - " + chosen);
				//enter the archers game state
				stateGame.enterState(3);
			}
		};
	}
	
	
	public int getID(){
		return 2;
	}
	
}
