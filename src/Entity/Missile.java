package Entity;

import javax.imageio.ImageIO;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Missile {

    private BufferedImage missile;
    
    private double x;
    private double y;

    private double velocity = 2;
    private double acceleration = 0.02;

    public Missile(double x, double y){

        try {
        	
        		missile = ImageIO.read(getClass().getResourceAsStream("/Background/Missiles.png"));

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        
        this.x = x;
        this.y = y;
    }

    public double getX() { return this.x; }
	public double getY() { return this.y; }
	
	public Rectangle getBounds() {

		return new Rectangle((int)x, (int)y, (int) (missile.getWidth() * 0.4), (int) (missile.getHeight() * 0.2));
	}
    
    public void draw(Graphics g){
    	
    		//g.drawRect((int)x, (int)y, (int) (missile.getWidth() * 0.4), (int) (missile.getHeight() * 0.2));
    	
        g.drawImage(missile, (int)x, (int)y, (int) (missile.getWidth() * 0.4), (int) (missile.getHeight() * 0.2), null);
    }

    public void update(){

        if(velocity <= 4)
            velocity += acceleration;

        y -= velocity;
    }
    
    public void update2() {
    	
    	y += 5;
    }
   
}
