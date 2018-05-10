package Entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Enemy {

    private BufferedImage enemy;
	
	private double x, y;
	//private int width, height;
	private boolean shoot;
	private Missile[] rightMissile;
	private Missile[] leftMissile;
	private int counter = -1;

	public Enemy(double x, double y) {

		
		try {
			enemy = ImageIO.read(new File("Resources/Background/alien1.png"));
			//width = enemy.getWidth();
			//height = enemy.getHeight();
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		this.x = x;
		this.y = y;

		leftMissile = new Missile[1000];
		rightMissile = new Missile[1000];
	}
	
	public void shoot(boolean b) {
		shoot = b;
	}
	
	public void tick() {
		y -= 100;
	}
	
	public Rectangle getBounds() {

		return new Rectangle((int)x, (int)y, 50, 80);
	}
	
	public void update() {


		
		
	}
	 
	public void draw(Graphics g) {
		
		//g.drawRect((int)x, (int)y, 50, 80);
		g.drawImage(enemy, (int)x, (int)y, 50, 100, null);


	}
	
	
	

}
