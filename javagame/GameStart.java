package javagame;
//created by Haaroon Yousaf
//Based using the Slick Java Game library
//Archer, Warrior and Knight Sprite's taken from drawn.ca - Creative Commons license
//All other images created by myself
//this class is the main game class where all states are initialised
//Make an array List in the enemy, if you go to a certain point then an emeny spawns and attacks
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

//StateBasedGame allows game to have Menu, Pause menu and game state
public class GameStart extends StateBasedGame {
	
	//set up variables for the game
	//game name
	public static final String gamename = "Adventure Game!";
	//Game state values
	public static final int menu = 0;
	public static final int playW = 5;
	public static final int playA = 3;
	public static final int playK = 4;
	public static final int select = 2;
	public static final int missingno = 99;
	public static final int fight = 6;
	
	public GameStart(String gamename){
		//passing game-name into the game so that it is displayed in the window
		super(gamename); //adds title to game window
		this.addState(new MainMenu(menu)); //add the game menu to the game container
		this.addState(new PlayWarrior(playW));
		this.addState(new PlayArcher(playA));
		this.addState(new PlayKnight(playK));
		this.addState(new Select(select));
		this.addState(new Fight(fight));
		this.addState(new Missingno(missingno));
	}
	
	//method below included due to extending StateBasedGame
	public void initStatesList(GameContainer container) throws SlickException {
		this.getState(menu).init(container, this); //initialises game states to game
		this.enterState(menu);  //this tells program to first show menu when game is launched
		
		//these must retrieve the game states and initialise them into the states list
		this.getState(playW).init(container, this);
		this.getState(playA).init(container, this);
		this.getState(playK).init(container, this);
		this.getState(select).init(container, this); 
		this.getState(fight).init(container, this); 
		this.getState(missingno).init(container, this); 
		
		
	}
	
	public static void main(String[] args) {
		AppGameContainer appContainer; //object that contains the application
		try{
			appContainer = new AppGameContainer(new GameStart(gamename)); //creates a window to hold the game
			appContainer.setDisplayMode(700, 700, false); //set the size of the game window, this case 700pixels by 700pixels
			appContainer.start(); //this launches the game window when the app runs
			
		}catch(SlickException e){
			e.printStackTrace();
		}
	}

}
