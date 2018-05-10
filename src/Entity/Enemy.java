package Entity;

import Main.Animation;
import Main.GamePanel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Enemy extends Rectangle{

    private BufferedImage enemy;
    
    private int health; // Regular enemies have 1 health, bosses can have more
    private boolean isDead; // to detect when to play explosion
    
    private Animation explosion;
	private boolean shoot;
	private Missile[] rightMissile;
	private Missile[] leftMissile;
	private int counter = -1;
	private double slope;

	public Enemy(int x, int y, int health) {

		super(x,y,0,0);
		
		explosion = new Animation("Resources/explosion/explosion_", ".png", 38, 10);
		isDead = false;
		slope = 0;

		this.health = health;
		
		try {
			enemy = ImageIO.read(new File("Resources/Background/alien1.png"));
			width = (int)(enemy.getWidth()*.2);
			height = (int)(enemy.getHeight()*.2);
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		leftMissile = new Missile[1000];
		rightMissile = new Missile[1000];
	}
	
	public void shoot(boolean b) {
		shoot = b;
	}
	
	public void tick() {
		y -= 100;
	}
	
	public void update() {

		if(shoot){
			counter++;
			rightMissile[counter] = new Missile(x+5,y-3);
			leftMissile[counter]  = new Missile(x+width-8,y-3);
		}

		for(int i = 0; i<= counter; i++){
			rightMissile[i].launch();
			leftMissile[i].launch();
		}

		if(slope > 0){
			x += 0.008*(y/slope);
			y = Math.abs(x*slope);
			//System.out.println(x);
		}

	}
	 
	public void draw(Graphics g) {
		/*if(!explosion.isAnimationOver()) {
			
			if(!isDead) {
				g.drawImage(enemy, (int)x, (int)y,50,100, null);
			
				for(int i = 0; i<= counter; i++){
					rightMissile[i].draw(g);
					leftMissile[i].draw(g);	
				}
			}
			
			
			else {
				g.drawImage(explosion.nextImage(), (int)x, (int)y, null);
				//super.moveBy(2000, 2000);
			}
		}*/

		if(!isDead) {
			g.drawImage(enemy, (int) x, (int) y, width, height, null);
			for (int i = 0; i <= counter; i++) {
				rightMissile[i].draw(g);
				leftMissile[i].draw(g);
			}
		}
		else {
			g.drawImage(explosion.nextImage(), (int)x, (int)y, null);
		}
	}
	
	public double getWidth() {
		return width;
	}
	
	public double getHeight() {
		return height;
	}
	
	public void setValues(double x, double y) {
		this.x = (int) x;
		this.y = (int) y;
	}
	
	public void setToDead() {
		isDead = true;
	}
	
	public boolean isDead() {
		return isDead;
	}

	public void setSlope(double m){
		slope = m;
	}

	public boolean isAnimationOver(){
		return explosion.isAnimationOver();
	}

}
