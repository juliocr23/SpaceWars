package GameState;

import java.awt.Graphics;

import java.awt.event.KeyEvent;

import Main.Background;
import Entity.Enemy;
import Entity.Missile;
import Entity.Player;
import Main.Game;
import Main.GamePanel;

public class Level1State extends GameState {
	
	private Background bg;
	private Background bg1;
	
	private Player player;
	private Enemy enemy[];
	private int counter = 5;
	
	public Level1State(GameStateManager gsm) {
		this.gsm = gsm;
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
			enemy[i] = new Enemy(0,0);
		}
	}
		
	@Override
	public void update() {
		
		player.update();
		
		if(isCollidingWithMissile() != false) {
			//enemy[i].explode();
			System.out.println("is hit with missle");
		}
		
		if(isCollidingWithPlayer()) {
			//player.die();
			System.out.println("is dead");
		}
		
		removeOffScreenMissiles();
		
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
		player.draw(g);
		for(int i = 0; i<counter; i++) {
			
			int w = (int) enemy[i].getWidth();
			
			enemy[i].setValues((GamePanel.WIDTH/2-120)+i*w, 50);
			enemy[i].draw(g);
		}
	}
	
	public boolean isCollidingWithPlayer() {
		boolean flag = false;
		
		for(int i = 0; i<counter; i++) {
			if(enemy[i].overlaps(player)) {
				flag = true;
				break;
			}
		}
		
		return flag;
	}
	
	
	public boolean isCollidingWithMissile() {
		
		boolean isColliding = false;
		
		for(int i = 0; i<counter; i++) {
			for(int j = 0; j < player.getRightMissile().size(); j++) {
				
				if(player.getRightMissile() != null) {
					if(player.getLeftMissile().get(j).overlaps(enemy[i])
							|| player.getRightMissile().get(j).overlaps(enemy[i]) ) {
						isColliding = true;
						;
						
					}
				}
			}
		}
		
		return isColliding;
	}
	
	public void removeOffScreenMissiles() {
		
		for(int i = 0; i < player.getLeftMissile().size(); i++) {
			if(player.getLeftMissile().get(i).y <= 0) {
				player.getLeftMissile().remove(i);
				player.getRightMissile().remove(i);
			}
		}
	}
	
	
	
	
	
	
	
	
	
}
