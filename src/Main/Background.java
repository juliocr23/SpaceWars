package Main;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;


public class Background {
	
	private BufferedImage image;

	public double x;
	public double y;
	private double dx;
	private double dy;
	
	private double moveScale;
	
	public Background(String s, double ms) {
		
		try {
			image = ImageIO.read(new File(s));
			moveScale = ms;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setPosition(double x, double y) {
		
		this.x = x * moveScale;
		this.y = y * moveScale;
	}
	
	public void setVector(double dx, double dy) {
		
		this.dx = dx;
		this.dy = dy;
	}
	
	public void update() {
		
		x += dx * moveScale;
		y += dy * moveScale;
	}
	
	public void draw(Graphics g) {
		g.drawImage(image, (int)x, (int)y, null);
	}

	public void draw(Graphics g, int w, int h){
		g.drawImage(image, (int)x, (int)y,w,h, null);
	}
}