package Entity;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Player {

    BufferedImage player;
	
	private double x;
	private double y;
	
	private boolean moveLeft;
	private boolean moveRight;
	private boolean moveUp;
	private boolean moveDown;
	
	public Player() {
	
		try {
			player = ImageIO.read(new File("Resources/Background/player.png"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public void moveLeft(boolean b) { moveLeft = b; }
	public void moveRight(boolean b) { moveRight = b; }
	public void moveUp(boolean b) { moveUp = b; }
	public void moveDown(boolean b) { moveDown = b; }
	
	public void update() {
		
		if(moveLeft) x -= 4;
		if(moveRight) x += 4;
		if(moveUp) y -= 4;
		if(moveDown) y += 4;
		
		
	}
	 
	public void draw(Graphics g) {
		   
		g.drawImage(player, (int)x, (int)y, null);
	      

	}
}
