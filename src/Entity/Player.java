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
import Main.Animation;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
<<<<<<< HEAD
import java.util.ArrayList;

=======
>>>>>>> 3d743b1ddcc9c2ca9e6fdda7c69629128baa1db6
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
	
	private boolean isDead;

	//private int width;
	//private int height;

	private int dx = 4;
	private int dy = 4;
	
	private Animation explosion;

	private ArrayList<Missile> rightMissile = new ArrayList<Missile>();
	private ArrayList<Missile> leftMissile = new ArrayList<Missile>();
	private int counter = -1;

	public Player(int x, int y) {

		super(x,y,0,0);
<<<<<<< HEAD
=======
		
		explosion = new Animation("Resources/explosion/explosion_", ".png", 38, 10);

		isDead = false;
>>>>>>> 3d743b1ddcc9c2ca9e6fdda7c69629128baa1db6

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

<<<<<<< HEAD
		for(int i = 0; i< rightMissile.size(); i++){
			rightMissile.get(i).launch();
			leftMissile.get(i).launch();
=======
		for(int i = 0; i<= counter; i++){

			if(rightMissile[i] != null) {
				rightMissile[i].launch();
				leftMissile[i].launch();
			}
>>>>>>> 3d743b1ddcc9c2ca9e6fdda7c69629128baa1db6
		}
	}
	 
	public void draw(Graphics g) {
		if(!explosion.isAnimationOver()) {
			
			if(!isDead) {
				g.drawImage(player, (int)x, (int)y,width,height, null);

				for(int i = 0; i<= counter; i++){
					if(rightMissile[i] != null) {
						rightMissile[i].draw(g);
						leftMissile[i].draw(g);
					}
				}
			}
			else {
				g.drawImage(explosion.nextImage(), (int)x, (int)y, null);
			}
		}
		
	}
	
	public Missile[] getRightMissile() {
		return rightMissile;
	}
	
	public Missile[] getLeftMissile() {
		return leftMissile;
	}
	
	public void setToDead() {
		isDead = true;
	}
	
	public boolean isDead() {
		return isDead;
	}

	public boolean isMoving(){
		return moveLeft || moveRight || moveDown || moveUp;
	}

	public boolean isAnimationOver(){
		return explosion.isAnimationOver();
	}

<<<<<<< HEAD
		for(int i = 0; i < rightMissile.size(); i++){
			rightMissile.get(i).draw(g);
			leftMissile.get(i).draw(g);
=======
	public void removeLaunchMissile(){
		for(int i = 0; i<= counter; i++){
			rightMissile[i] = null;
			leftMissile[i] = null;
>>>>>>> 3d743b1ddcc9c2ca9e6fdda7c69629128baa1db6
		}
	}
	
	public ArrayList<Missile> getRightMissile() {
		return rightMissile;
	}
	
	public ArrayList<Missile> getLeftMissile() {
		return leftMissile;
	}
}
