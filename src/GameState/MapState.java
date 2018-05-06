package GameState;

import java.awt.Graphics;

import java.awt.event.KeyEvent;

import Main.Background;
import Entity.Player;
import Main.GamePanel;

public class MapState extends GameState {
	
	private Background bg;
	private Background bg1;
	
	private Player player;
	
	public MapState(GameStateManager gsm) {
		
		//this.gsm = gsm;
		init();
	}

	@Override
	public void init() {
		
			
		bg = new Background("Resources/Background/mapbg1.jpg", 1);
		bg1 = new Background("Resources/Background/clouds.png", 1);
		
		
		bg.setPosition(0, -1277);
		bg.setVector(0, 0.15);
		bg1.setPosition(0, -1377);
		bg1.setVector(0, 0.5);
		
		
		player = new Player(GamePanel.WIDTH/2-10,180);
		
		
	}

	@Override
	public void update() {
		
		player.update();
		
		bg.update();
		if(bg1.y >= 250) bg1.setPosition(0, -1500);
		bg1.update();
		
	}

	@Override
	public void draw(Graphics g) {
		

		bg.draw(g);
		bg1.draw(g);
		player.draw(g);
		
		
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
	
	}

}
