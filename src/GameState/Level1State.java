package GameState;

import java.awt.Color;
import java.awt.Font;
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
	
	MissileController leftMissile;
	MissileController rightMissile;
	MissileController enemyMissile;
	
	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	@Override
	public void init() {
			
		bg = new Background("/Background/mapbg1.jpg", 1);
		bg1 = new Background("/Background/clouds.png", 1);

		bg.setPosition(0, -1215);
		bg.setVector(0, 0.1);
		bg1.setPosition(0, -1377);
		bg1.setVector(0, 0.5);
		
		leftMissile = new MissileController();
		rightMissile = new MissileController();
		enemyMissile = new MissileController();
		
		player = new Player(140, 260);
		enemy = new Enemy[1];
		//tEnemy = new Enemy(200, -50);
		
		for(int i= 0; i<enemy.length; i++) {
			enemy[i] = new Enemy(20 + 50 * i, -100);
		}
	}
		
	@Override
	public void update() {
		
		if(player.x <= -2) {
			player.setPosition(-2, player.y);
		}if(player.x >= 292) {
			player.setPosition(292, player.y);
		}if(player.y >= 275) {
			player.setPosition(player.x, 275);
		}if(player.y <= 0) {
			player.setPosition(player.x, 0);
		}
		
	
		
		
		player.update();
		
		if(isEnemyCollidingWithMissile() != false) {
			//enemy[i].explode();
			System.out.println("Enemy is hit with missle");
		}
		
		if(isPlayerCollidingWithProjectiles() != false) {
			if(!player.isFlinching() && !player.isDead()) {
				System.out.println("player is hit with missiles");
				player.hit(1);
			}
		}
		
		if(isCollidingWithPlayer()) {
			if(!player.isFlinching() && !player.isDead()) {
				System.out.println("player is hit");
				player.hit(1);
			}
		}
		
		if(player.isDead()) {
			System.out.println("Player is dead");
		}
		
		leftMissile.update();
		rightMissile.update();
		
		
		
		bg.update();
		if(bg1.y >= 300) bg1.setPosition(0, -1500);
		bg1.update();
		
		
		for(int i = 0; i<enemy.length; i++) {
			enemy[i].moveTowards(player);
			
			enemy[i].update();
		}
		
		
		
		for(int i = 0; i < enemy.length; i++) {
			if(enemy[i].isShooting()) {
				enemyMissile.addMissile(new Missile(enemy[i].x+25,enemy[i].y+20));
			}
			
		}
		
		enemyMissile.update2();
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
		
		enemyMissile.draw(g);
		
		
		g.setColor(Color.GREEN);
		g.setFont(new Font("Comic Sans", Font.BOLD, 5));
		g.drawString("HP :", 255, 280);
		
		g.setColor(Color.GRAY);
		g.fillRect(265, 275, 50, 5);
		g.setColor(Color.GREEN);
		g.fillRect(265, 275, player.getHealth() * 10, 5);
		g.setColor(Color.BLACK);
		g.drawRect(265, 275, 50, 5);
	}
	
	
	public boolean isCollidingWithPlayer() {
		
		if(!player.isFlinching()) {
			for(int i = 0; i<enemy.length; i++) {
				if(enemy[i].getBounds().intersects(player.getBounds())) {
					return true;
				}
			}
		}
		
		return false;
	}
	
	public boolean isPlayerCollidingWithProjectiles() {
		
		if(!player.isFlinching()) {
			for(int i = 0; i < enemyMissile.b.size(); i++) {
				if(enemyMissile.b.get(i).getBounds().intersects(player.getBounds())) {
					enemyMissile.b.remove(i);
					return true;
				}
			}
		}
		
		return false;
	}
	
	
	public boolean isEnemyCollidingWithMissile() {
		
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
