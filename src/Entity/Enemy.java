package Entity;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Enemy {

    private BufferedImage enemy;
	
	private double x, y;
	private int A;
	//private int width, height;
	private boolean shoot;

	public Enemy(double x, double y) {

		
		try {
			enemy = ImageIO.read(getClass().getResourceAsStream("/Enemies/alien1.png"));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		
		this.x = x;
		this.y = y;

	}
	
	public void shoot(boolean b) { shoot = b; }
	
	
	public Rectangle getBounds() {

		return new Rectangle((int)x, (int)y, 50, 80);
	}
	
	public void moveTowards(Player player) {
	    
		turnTowards(player);
			
		double ux = Lookup.cos[A];
		double uy = Lookup.sin[A];
	
		double vx = player.x - x;
		double vy = player.y - y;
	
		double d = vx*ux + vy*uy;
	
	
		if ( d > 20)
			moveForwardBy(5);
		
	}
	
	public void moveForwardBy(int distance) {
		x += distance * Math.cos(A*Math.PI/180);
	    //y += distance * Math.sin(A*Math.PI/180);
		
	}
	
	public void turnTowards(Player player) {
		
	      double ux = Lookup.cos[A];
	      double uy = Lookup.sin[A];

	      double nx = -uy;
	      double ny = ux;

	      double vx = player.x - x;
	      double vy = player.y - y;

	      double d = vx*nx + vy*ny;

	      if(d > 10)
	      {
	          A += 3;
	          if (A >= 360)  A -= 360;
	      }
	      if(d < -10)
	      {
	         A -= 3;
	         if(A < 0)  A += 360;
	      }

	}
	
	
	public void update() {

		if(y != 0)
			y += 1;
		
		
	}
	 
	public void draw(Graphics g) {
		
		//g.drawRect((int)x, (int)y, 50, 80);
		g.drawImage(enemy, (int)x, (int)y, 50, 100, null);


	}
	
	
	

}
