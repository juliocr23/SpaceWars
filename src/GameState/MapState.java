package GameState;

import java.awt.Graphics;

import java.awt.event.KeyEvent;

import Main.Background;
import Entity.Enemy;
import Entity.Missile;
import Entity.Player;
import Main.Game;
import Main.GamePanel;

public class MapState extends GameState {
	
	private Background bg;
	private Background bg1;
	
	private Player player;
	private Enemy enemy[];

	private int counter = 8;
	private int score = 0;

	
	public MapState(GameStateManager gsm) {
		init();
	}

	@Override
	public void init() {
			
		bg = new Background("Resources/Background/newBG.jpg", 1);
		bg1 = new Background("Resources/Background/newCloud.png", 1);

		bg.setPosition(0, -1000);
		bg.setVector(0, 0.15);
		bg1.setPosition(150, -1377);
		bg1.setVector(0, 0.5);
		
		player = new Player(GamePanel.WIDTH/2-10,300);
		enemy = new Enemy[60];
		
		for(int i= 0; i<enemy.length; i++) {
			enemy[i] = new Enemy(-10, -10, 1);
		}

		enemy[0].setSlope(getSlope());
	}
		
	@Override
	public void update() {
		
		player.update();

		//enemy[0].setSlope(getSlope());
		enemy[0].update();


		//if(isCollidingWithMissile() != -1) {
			//enemy[isCollidingWithMissile()].setToDead();

		//}
		
		if(isCollidingWithPlayer()) {
			player.setToDead();
		}
		
		bg.update();
		if(bg1.y >= 250) bg1.setPosition(0, -1500);
		bg1.update();
		
	}

	@Override
	public void processInput() {
		player.moveLeft(GamePanel.input.keyDown(KeyEvent.VK_LEFT));
		player.moveRight(GamePanel.input.keyDown(KeyEvent.VK_RIGHT));
		player.moveDown(GamePanel.input.keyDown(KeyEvent.VK_DOWN));
		player.moveUp(GamePanel.input.keyDown(KeyEvent.VK_UP));
		player.shoot(GamePanel.input.keyDownOnce(KeyEvent.VK_S));
	}

	@Override
	public void draw(Graphics g) {

		bg.draw(g);
		bg1.draw(g);
		
		if(!player.isDead()) player.draw(g);
		for(int i = 0; i<counter; i++) {
			
			if(enemy[i] != null) {
				int w = (int) enemy[i].getWidth();
				
				enemy[i].setValues((GamePanel.WIDTH/2-120)+i*w, 50);
				enemy[i].draw(g);
			}

		//	enemy[i].setValues((GamePanel.WIDTH/2-120)+i*w, 50);
			enemy[i].draw(g);
		}

		g.drawString(""+score,40,40);
	}
	
	public boolean isCollidingWithPlayer() {
		boolean flag = false;
		
		for(int i = 0; i<counter; i++) {
			if(enemy[i].overlaps(player)) {
				flag = true;
				enemy[i].setToDead();
				enemy[i] = null;
				player.setToDead();
				//player = null;
				break;
			}
		}
		
		return flag;
	}
	
	
	public int isCollidingWithMissile() {
		
		int flag = -1;
		Missile m1[] = player.getRightMissile();
		Missile m2[] = player.getLeftMissile();
		
		for(int i = 0; i<counter; i++) {
			for(int j = 0; j<m1.length; j++) {
				
				if(m1[j] != null) {
					if( m2[j].overlaps(enemy[i]) || m1[j].overlaps(enemy[i]) ) {
						flag = i;
						break;
						
					}
				}
			}
		}
		
		return flag;
	}
	
	
	private double getSlope(){

		double y2 = (enemy[0].y +enemy[0].height);
		double y1 = player.y;
		double x2 = (enemy[0].x+enemy[0].width);
		double x1  = player.x;
		double m = (y2 - y1) / (x2 - x1);

		return m;
	}
	
	
	
	
	
	
}
