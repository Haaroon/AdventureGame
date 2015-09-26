package javagame;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.swing.JOptionPane;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;


public class OLDShop extends BasicGameState{

	
	Image shopimage;
	int shopimageX = 0;
	int shopimageY = 0;
	int v;
	Character player; 
	public String coins = "Null"; 
	
	public OLDShop(int state){
	}
	
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		shopimage = new Image("res/shopimage.png"); //create new object for image
		try {
			readState();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			importPlayer();
		} catch (IOException e) {
			e.printStackTrace();
		} 

	}

	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {	
		g.drawImage(shopimage, 0, 0 ); //draw image to screen
		g.drawImage(shopimage, shopimageX, shopimageY);
		g.drawString(coins, 100,100);
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {	
		
		Input input = gc.getInput();
				
		if (input.isKeyDown(Input.KEY_DOWN)){
    		//leave shop
     			sbg.enterState(v);   		
    	}
		
    	if (input.isKeyDown(Input.KEY_UP)){
    		//do nothing   	
    	}
    	
    	if (input.isKeyDown(Input.KEY_LEFT)){
    		//Buy potion
    		if (player.coins>=50)
    		{
    			player.potion = player.potion+1; 
    			player.coins = player.coins -50;
    			JOptionPane.showMessageDialog(null, "You used 50 coins to buy a Potion.");
    		}
    		else
    		{
    			JOptionPane.showMessageDialog(null, "You don't have enough coins.You need 50coins or more to purchase.");
    		}
    		try {
				Thread.sleep(250);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	if (input.isKeyDown(Input.KEY_RIGHT)){
    		//Buy Boost
    		if (player.coins>=150)
    		{
    			player.boost = player.boost+1; 
    			player.coins = player.coins -150; 
    			JOptionPane.showMessageDialog(null, "You used 150 coins to buy a Boost.");
    			try {
    				Thread.sleep(750);
    			} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    		else
    		{
    			JOptionPane.showMessageDialog(null, "You don't have enough coins. You need 150coins or more to purchase.");
    			try {
    				Thread.sleep(750);
    			} catch (InterruptedException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    			}
    		}
    		
    		
    	}
    	
    	coins = ": " + player.coins; 

	}
	
	public void readState() throws IOException{
		
		BufferedReader buffer;
		try {
			buffer = new BufferedReader( new InputStreamReader(new FileInputStream("res/state.txt")));
			double thestate;
		    v = Integer.parseInt(buffer.readLine());
		    buffer.close();
			
			} 
			catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error, File not found Player.txt. Closing");
			System.exit(0); 		
			}
		}
	    
public void importPlayer() throws IOException{
    	
    	BufferedReader buffer;
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
			valueMa = Double.parseDouble(buffer.readLine());
			valueHe = Double.parseDouble(buffer.readLine());
			valuePo = Double.parseDouble(buffer.readLine());
			valueBo = Double.parseDouble(buffer.readLine());
			valueCo = Double.parseDouble(buffer.readLine());
			player = new Hero(valueSt, valueRa , valueIn, valueMa, valueHe, valuePo,valueBo,valueCo);
			buffer.close();
    	    
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Error, File not found Player.txt. Closing");
			System.exit(0); 		
		}
    }
	
	public void theShop() {
		
	}
	
	
	
	
	public int getID(){
		return 7;
	}
	
}
