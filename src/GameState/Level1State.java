package GameState;

import java.awt.Graphics;

import java.awt.event.KeyEvent;

import Main.Background;
import Entity.Enemy;
import Entity.Missile;
import Entity.MissileController;
import Entity.Player;


public class Level1State extends GameState {
	
	private Background bg;
	private Background bg1;
	
	private Player player;
	private Enemy enemy[];
	private int counter = 5;
	
	MissileController leftMissile;
	MissileController rightMissile;
	
	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	@Override
	public void init() {
			
		bg = new Background("/Background/mapbg1.jpg", 1);
		bg1 = new Background("/Background/clouds.png", 1);

		bg.setPosition(0, -1227);
		bg.setVector(0, 0.15);
		bg1.setPosition(0, -1377);
		bg1.setVector(0, 0.5);
		
		leftMissile = new MissileController();
		rightMissile = new MissileController();
		
		player = new Player(120, 200);
		enemy = new Enemy[1];
		//tEnemy = new Enemy(200, -50);
		
		for(int i= 0; i<enemy.length; i++) {
			enemy[i] = new Enemy(20 + 50 * i, -100);
		}
	}
		
	@Override
	public void update() {
		
		if(player.x <= 0) {
			player.setPosition(0, player.y);
		}if(player.x >= 290) {
			player.setPosition(290, player.y);
		}if(player.y >= 215) {
			player.setPosition(player.x, 215);
		}if(player.y <= 0) {
			player.setPosition(player.x, 0);
		}
		
	
		
		
		player.update();
		
		if(isCollidingWithMissile() != false) {
			//enemy[i].explode();
			System.out.println("is hit with missle");
		}
		
		
		if(isCollidingWithPlayer()) {
			//player.die();
			System.out.println("is dead");
		}
		
		leftMissile.update();
		rightMissile.update();
		
		bg.update();
		if(bg1.y >= 250) bg1.setPosition(0, -1500);
		bg1.update();
		
		for(int i = 0; i<enemy.length; i++) {
			enemy[i].moveTowards(player);
			
			enemy[i].update();
		}
	}


	@Override
	public void draw(Graphics g) {
		bg.draw(g);
		bg1.draw(g);
		player.draw(g);
		
		leftMissile.draw(g);
		rightMissile.draw(g);
		
		for(int i = 0; i<enemy.length; i++) {
			
			
			enemy[i].draw(g);
		}
	}
	
	public boolean isCollidingWithPlayer() {
		boolean flag = false;
		
		for(int i = 0; i<enemy.length; i++) {
			if(enemy[i].getBounds().intersects(player.getBounds())) {
				flag = true;
				break;
			}
		}
		
		return flag;
	}
	
	
	public boolean isCollidingWithMissile() {
		
		boolean isColliding = false;
		
		
			for(int i = 0; i < enemy.length; i++) {
				for(int j = 0; j < leftMissile.b.size(); j++) {
						if(leftMissile.b.get(j).getBounds().intersects(enemy[i].getBounds())) {
							isColliding = true;
							leftMissile.b.remove(j);
							
						}
				}
				for(int j = 0; j < rightMissile.b.size(); j++) {
					if(rightMissile.b.get(j).getBounds().intersects(enemy[i].getBounds())) {
						isColliding = true;
						rightMissile.b.remove(j);
					}
				}
			}
		
		return isColliding;
	}


	@Override
	public void keyPressed(int k) {
		
		if(k == KeyEvent.VK_LEFT) player.moveLeft(true);
		if(k == KeyEvent.VK_RIGHT) player.moveRight(true);
		if(k == KeyEvent.VK_UP) player.moveUp(true);
		if(k == KeyEvent.VK_DOWN) player.moveDown(true);

	}

	@Override
	public void keyReleased(int k) {
		if(k == KeyEvent.VK_LEFT) player.moveLeft(false);
		if(k == KeyEvent.VK_RIGHT) player.moveRight(false);
		if(k == KeyEvent.VK_UP) player.moveUp(false);
		if(k == KeyEvent.VK_DOWN) player.moveDown(false);
		
		if(k == KeyEvent.VK_S) {
			
			leftMissile.addMissile(new Missile(player.x + 2, player.y));
			rightMissile.addMissile(new Missile(player.x + 24, player.y));
			
		}
	
	}
	
	
	
	
	
	
	
	
	
}
