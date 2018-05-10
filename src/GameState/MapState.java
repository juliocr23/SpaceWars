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

	private int starting = 0;
	private int counter = 3;
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
			enemy[i] = new Enemy(100*i, -10, 1);
			enemy[i].setSlope(getSlope());
		}
	}
		
	@Override
	public void update() {

		player.update();
		updateEnemy();

		System.out.println(enemy[2].x);

		int index  = isCollidingWithPlayer();

		if(index != -1){
			enemy[index].setToDead();
			player.setToDead();
		}

		if(player.isAnimationOver())
			player = null;

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
					enemy[i].draw(g);
				}
		}

		g.drawString(""+score,40,40);
	}
	
	public int isCollidingWithPlayer() {
		int flag = -1;

		if(player == null)
			return flag;

		for(int i = starting; i<counter; i++) {
			if(enemy[i] != null) {
				if (enemy[i].overlaps(player)) {
					flag = i;
					break;
				}
			}
		}
		
		return flag;
	}
	
	
	public int isCollidingWithMissile() {
		
		int flag = -1;
		Missile m1[] = player.getRightMissile();
		Missile m2[] = player.getLeftMissile();
		
		for(int i = starting; i<counter; i++) {
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

	private void updateEnemy(){

		//Change slope if player's move
	//	if(player.isMoving()){
	//		for(int i = 0; i<counter; i++)
		//		enemy[i].setSlope(getSlope());
		//}

		//Update enemy
		for(int i = starting; i<counter; i++){
			if(enemy[i] != null) {
				enemy[i].update();

				if(enemy[i].isAnimationOver()) {
					enemy[i] = null;

					if(enemy[i] != null) {
						if (enemy[i].y >= GamePanel.HEIGHT) {
							enemy[i] = null;
						}
					}
				}
			}
		}

		int index = isCollidingWithMissile();

		if(index != -1){
			enemy[index].setToDead();
			player.removeLaunchMissile();
		}

	}

	
	
	
	
	
	
}
