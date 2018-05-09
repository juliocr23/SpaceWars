package Entity;

import Main.GamePanel;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class Enemy extends Rectangle{

    private BufferedImage enemy;
	
	//private double x, y;
	//private int width, height;
	private boolean shoot;
	private Missile[] rightMissile;
	private Missile[] leftMissile;
	private int counter = -1;

	public Enemy(int x, int y) {

		super(x,y,0,0);

		try {
			enemy = ImageIO.read(new File("Resources/Background/alien1.png"));
			//width = enemy.getWidth();
			//height = enemy.getHeight();
			width = 50;
			height = 100;
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
		
	}
	 
	public void draw(Graphics g) {
		g.drawImage(enemy, (int)x, (int)y,50,100, null);

		for(int i = 0; i<= counter; i++){
			rightMissile[i].draw(g);
			leftMissile[i].draw(g);
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
	

}
