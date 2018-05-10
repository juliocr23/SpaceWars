package GameState;

import java.awt.Graphics;
import java.awt.event.KeyEvent;

import Entity.Player;

public class Level2State extends GameState {
	
	private Player player;

	public Level2State(GameStateManager gsm) {
		
		this.gsm = gsm;
		init();
	}
	
	@Override
	public void init() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
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
