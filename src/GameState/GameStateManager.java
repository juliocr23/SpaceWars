package GameState;

import java.awt.*;

public class GameStateManager {

	private GameState state;
	public static GameState menu;
	public static GameState level1;
	public static GameState level2;

	public GameStateManager() {

		menu = new MenuState(this);
		level1  = new Level1(this);
		level2 = new Level2(this);
		
		state = menu;
	}

	public void setState(GameState other) {
		state = other;
	}

	public void processInput(){
		state.processInput();
	}

	public void update() {
		state.update();
	}

	public void draw(Graphics g) {
			state.draw(g);
	}

}
