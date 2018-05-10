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
	
	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void update() {

		if(moveLeft) x -= 4;
		if(moveRight) x += 4;
		if(moveUp) y -= 4;
		if(moveDown) y += 4;
		
	}
	 
	public void draw(Graphics g) {
		
		g.drawImage(player, (int)x, (int)y, player.getWidth(),player.getHeight(), null);

		
	}
	
	
}
