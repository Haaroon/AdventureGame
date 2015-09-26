package javagame;

import org.lwjgl.input.*;
import java.io.*;
import java.util.*;
import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.newdawn.slick.tiled.TiledMap;
import org.newdawn.*;
import javax.swing.*;


public class Play extends BasicGameState{

	
	//TiledMap grassMap;
	Image gamemap;
	float mapX = 0; 
	float mapY = 0; 
	Animation sprite, up, down, left, right;
	float x = 100f, y = 100f;
	boolean[][] blocked;
	static final int SIZE = 34;
	Character player; 
	File textfile = new File("res/characterchoice.txt");
	 
	public String mouse = "No input yet";
	
	public Play(int state){
		
	}
	
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		 //Scanner linecheck;
		 //double number; 
         //Scanner Input = null;
         //Input = new Scanner("textfile.txt");
         //number = Input.nextDouble();
		Image up1 = new Image("res/sprites/knight/knt3_bk1.png"); 
		Image up2 = new Image("res/sprites/knight/knt3_bk2.png");
		Image down1 =  new Image("res/sprites/knight/knt3_fr1.png"); 
		Image down2 =  new Image("res/sprites/knight/knt3_fr2.png"); 
		Image left1 =  new Image("res/sprites/knight/knt3_lf1.png"); 
		Image left2 = new Image("res/sprites/knight/knt3_lf2.png"); 
		Image right1 = new Image("res/sprites/knight/knt3_rt1.png"); 
		Image right2 = new Image("res/sprites/knight/knt3_rt2.png"); 
		Image fightscene = new Image("res/sprites/knight/fightscene-goblin.png");
		String chosen = Select.chosen; 
		
		if (chosen.equals("1"))
		 {
       	 	player = Hero.create("knight"); 
       	        	
		 }
        else if (chosen.equals("2"))
        {
        	player = Hero.create("warrior");
        	up1 = new Image("res/sprites/warrior/avt1_bk1.png");
       		up2=  new Image("res/sprites/warrior/avt1_bk2.png");
       		down1 = new Image("res/sprites/warrior/avt1_fr1.png");
      	    down2=  new Image("res/sprites/warrior/avt1_fr2.png");
      	    left1 = new Image("res/sprites/warrior/avt1_lf1.png");
      	    left2=  new Image("res/sprites/warrior/avt1_lf2.png");
      	    right1 = new Image("res/sprites/warrior/avt1_rt1.png");
      	    right2=  new Image("res/sprites/warrior/avt1_rt2.png");
      	    fightscene = new Image("res/sprites/warrior/fightscene-goblin.png");
      	    //sbg.enterState(1);
        }
        else if (chosen.equals("3"))
        {
        	player = Hero.create("archer");
       		up1 = new Image("res/sprites/archer/ftr2_bk1.png");
        	up2=  new Image("res/sprites/archer/ftr2_bk2.png");
        	down1 = new Image("res/sprites/archer/ftr2_fr1.png");
        	down2=  new Image("res/sprites/archer/ftr2_fr2.png");
        	left1 = new Image("res/sprites/archer/ftr2_lf1.png");
        	left2=  new Image("res/sprites/archer/ftr2_lf2.png");
        	right1 = new Image("res/sprites/archer/ftr2_rt1.png");
        	right2=  new Image("res/sprites/archer/ftr2_rt2.png");
        	fightscene = new Image("res/sprites/archer/fightscene-goblin.png");
        }
		
		//grassMap = new TiledMap("res/maps/test.tmx");
         gamemap = new Image("res/gamemap.png"); //create new object for image
         
		//Array images for the players movement
        Image [] movementUp = {up1,  up2};
        Image [] movementDown = {down1, down2};
  		Image [] movementLeft = {left1, left2};
  		Image [] movementRight = {right1, right2};
		int [] duration = {300, 300}; 
		
		/*
		* false variable means do not auto update the animation.
		* By setting it to false animation will update only when
		* the user presses a key.
		*/
		up = new Animation(movementUp, duration, false);
		down = new Animation(movementDown, duration, false);
		left = new Animation(movementLeft, duration, false);
		right = new Animation(movementRight, duration, false); 

		// Original orientation of the sprite. It will look right.
		sprite = right; 
		
		// build a collision map based on tile properties in the TileD map 
		/*blocked = new boolean[grassMap.getWidth()][grassMap.getHeight()];
		for (int xAxis=0;xAxis<grassMap.getWidth(); xAxis++)
		{
		             for (int yAxis=0;yAxis<grassMap.getHeight(); yAxis++)
		             {
		                 int tileID = grassMap.getTileId(xAxis, yAxis, 0);
		                 String value = grassMap.getTileProperty(tileID, "blocked", "false");
		                 if ("true".equals(value))
		                 {
		                     blocked[xAxis][yAxis] = true;
		                 }
		             }
		 } */
		
	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {	
		
		g.drawImage(gamemap,mapX, mapY);
		sprite.draw((int)x, (int)y);
		g.drawString(mouse, 100,100);
		g.drawString(Select.chosen, 150,150);


	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {	
		Input input = gc.getInput();
		//coord debug
		int xpos = Mouse.getX();
		int ypos = Mouse.getY(); 
		mouse = "XPOS :" + x + "YPOS: " + y; 
		
		//Random Fight generator
				Random gen= new Random(10);
				int r=0;
		
		if (input.isKeyDown(Input.KEY_UP))
		{
		    sprite = up;
		    //if (!isBlocked(x, y - delta * 0.1f)) {
		    sprite.update(delta);
		    // The lower the delta the slowest the sprite will animate.
		    mapY += 0.15;
		    y -= delta * 0.05f;
		    r = gen.nextInt();
		   //stop character moving off screen and stop map moving
		    if(y< 75) {
		    	y += delta * 0.05f;
		    	mapY -= 0.15;
		    }
		}
		if (input.isKeyDown(Input.KEY_DOWN))
		{
		    sprite = down;
		   // if (!isBlocked(x, y - delta * 0.1f)) {
		    sprite.update(delta);
		    mapY -= 0.15;
		    y += delta * 0.05f;
		    r = gen.nextInt();
		  //stop character moving off screen and stop map moving
		    if(y> 390) {
		    	y -= delta * 0.05f;
		    	mapY += 0.15;
		    }
		   	    
		}
		if (input.isKeyDown(Input.KEY_LEFT))
		{
		    sprite = left;
		    //if (!isBlocked(x, y - delta * 0.1f)) {
		    sprite.update(delta);
		    mapX +=0.15;
		    x -= delta * 0.05f;
		    r = gen.nextInt();
		  //stop character moving off screen and stop map moving
		    if(x< 77) {
		    	x += delta * 0.05f;
		    	mapX -= 0.15;
		    }
		    
		}
		if (input.isKeyDown(Input.KEY_RIGHT))
		{
			sprite = right;
		    //if (!isBlocked(x, y - delta * 0.1f)) {
		    sprite.update(delta);
		    mapX -=0.15;
		    x += delta * 0.05f;
		    r = gen.nextInt();
		  //stop character moving off screen and stop map moving
		    if(x> 392) {
		    	x -= delta * 0.05f;
		    	mapX += 0.15;
		    }
		    
		}
		
	
		//int xpos = Mouse.getX();
		//int ypos = Mouse.getY();		
		if (xpos>300&&xpos<400 && (ypos>300&&ypos<400)){ 
			if(input.isMouseButtonDown(0)){
			
			}
		};
		
		
		 
		if ((x>400&&x<700)&&(y>400&&y<700) && r>8){
			
			r=0;
			x = 300f;
			y = 300f; 
			sprite.draw((int)x, (int)y);
			r=0;			
		}
		
	}
	
	public int getID(){
		return 1;
	}	
}