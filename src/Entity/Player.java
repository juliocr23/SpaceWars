/***
 * Things missing:
 *
 * Velocity
 * acceleration
 * collision
 * shooting
 */

package Entity;
import Main.Game;
import Main.GamePanel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class Player extends Rectangle {

    private BufferedImage player;
	
	//private double x;
	//private double y;
	
	private boolean moveLeft;
	private boolean moveRight;
	private boolean moveUp;
	private boolean moveDown;
	private boolean shoot;

	//private int width;
	//private int height;

	private int dx = 4;
	private int dy = 4;

	private ArrayList<Missile> rightMissile = new ArrayList<Missile>();
	private ArrayList<Missile> leftMissile = new ArrayList<Missile>();
	private int counter = -1;

	public Player(int x, int y) {

		super(x,y,0,0);

		try {
			player = ImageIO.read(new File("Resources/Background/player.png"));
			width = player.getWidth();
			height = player.getHeight();
		}
		catch(Exception e) {
			e.printStackTrace();
		}

	}
	
	public void moveLeft(boolean b) { moveLeft = b; }
	public void moveRight(boolean b) { moveRight = b; }
	public void moveUp(boolean b)  { moveUp = b; }
	public void moveDown(boolean b) {moveDown = b;}

	public void shoot(boolean b) {
		shoot = b;
	}
	
	public void update() {

		if(moveLeft &&(x-dx)>0)                         //Move to the left as long as
			x -= dx;                                     // it is not at the left end of the screen

		if(moveRight && (x+dx)<(GamePanel.WIDTH-width))   //Move to the right as long as it is not at the right
			x += dx;                                      //end of the  screen

		if(moveUp && (y-dy)>0)                            //Move up as long as it is not at the above edge of the screen
			y -= dy;

		if(moveDown && (y+dy)<(GamePanel.HEIGHT-height))  //Move down as long as it not at the below edge of the screen
			y += dy;


		if(shoot){
			counter++;
			rightMissile.add(new Missile(x+5,y-3));
			leftMissile.add(new Missile(x+width-8,y-3));
		}

		for(int i = 0; i< rightMissile.size(); i++){
			rightMissile.get(i).launch();
			leftMissile.get(i).launch();
		}
		
	}
	 
	public void draw(Graphics g) {
		g.drawImage(player, (int)x, (int)y,width,height, null);

		for(int i = 0; i < rightMissile.size(); i++){
			rightMissile.get(i).draw(g);
			leftMissile.get(i).draw(g);
		}
	}
	
	public ArrayList<Missile> getRightMissile() {
		return rightMissile;
	}
	
	public ArrayList<Missile> getLeftMissile() {
		return leftMissile;
	}
}
