package javagame;
//created by Haaroon Yousaf
//Based using the Slick Java Game library
//Archer, Warrior and Knight Sprite's taken from drawn.ca/ Creative Commons license
//All other images created by myself
//This class is the main menu of the game screen


import org.lwjgl.input.*;
import java.io.*;
import java.util.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.*;
import javax.swing.*;
import org.lwjgl.input.Mouse;


public class MainMenu extends BasicGameState{

	//globally create the variables and objects
	Image menuimage;
	int menuimageX = 0;
	int menuimageY = 0;
	
	public MainMenu(int state){
	}
	
	public void init(GameContainer container, StateBasedGame stateGame) throws SlickException {
		//create a new object for image and initiliase it
		menuimage = new Image("res/menuimage.png"); 

	}

	public void render(GameContainer container, StateBasedGame stateGame, Graphics g) throws SlickException {	
		//draw the images to the screen
		g.drawImage(menuimage, 0, 0 );
		
	}
	
	public void update(GameContainer container, StateBasedGame stateGame, int d) throws SlickException {	
		//this class constantly repeats and runs checking for any input
		Input input = container.getInput();
		int xpos = Mouse.getX();
		int ypos = Mouse.getY();
		if (xpos>300&&xpos<400 && (ypos>300&&ypos<400)){ 
			if(input.isMouseButtonDown(0)){
				stateGame.enterState(2);
		};}
	}
	
	public int getID(){
		//this method is created to return the ID of the class
		return 0;
	}
	
}
