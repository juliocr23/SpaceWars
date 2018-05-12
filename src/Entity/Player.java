/***
 * Things missing:
 *
 * Velocity
 * acceleration
 * collision
 * shooting
 */

package Entity;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;

public class Player {

    private BufferedImage player;
	
	public double x;
	public double y;
	
	private int health = 5;
	private boolean flinching;
	private boolean dead = false;
	private long flinchTimer;
	
	private boolean moveLeft;
	private boolean moveRight;
	private boolean moveUp;
	private boolean moveDown;
	
	public Player(double x, double y) {


		try {
			player = ImageIO.read(getClass().getResourceAsStream("/Background/player.png"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		this.x = x;
		this.y = y;

	}
	
	public Rectangle getBounds() {

		return new Rectangle((int)x, (int)y, player.getWidth(), player.getHeight());
	}
	
	public void moveLeft(boolean b) { moveLeft = b; }
	public void moveRight(boolean b) { moveRight = b; }
	public void moveUp(boolean b)  { moveUp = b; }
	public void moveDown(boolean b) {moveDown = b; }
	public boolean isDead() { return dead; }
	public boolean isFlinching() { return flinching; }
	
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void hit(int damage) {
		if(flinching) return;
		health -= damage;
		if(health < 0) health = 0;
		if(health == 0) dead = true;
		flinching = true;
		flinchTimer = System.nanoTime();
	}
	
	public void update() {

		if(moveLeft) x -= 4;
		if(moveRight) x += 4;
		if(moveUp) y -= 4;
		if(moveDown) y += 4;
		
		// check done flinching
		if(flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
				if(elapsed > 2000) flinching = false;
		}
		
	}
	 
	public void draw(Graphics g) {
		
		// draw player
		if(!isDead()) {
			if(flinching) {
				long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
					if(elapsed / 100 % 2 == 0) return;
			}
		}
		
		
		g.drawImage(player, (int)x, (int)y, player.getWidth(),player.getHeight(), null);
		//g.drawRect((int)x, (int)y, player.getWidth(), player.getHeight());

		
	}
	
	
}
