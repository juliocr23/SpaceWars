package GameState;

import java.awt.Graphics;

import java.awt.event.KeyEvent;

import Main.Background;
import Entity.Player;
import Main.Game;
import Main.GamePanel;

public class MapState extends GameState {
	
	private Background bg;
	private Background bg1;
	
	private Player player;
	
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
	}
}
