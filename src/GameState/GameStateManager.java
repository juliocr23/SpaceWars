package GameState;

import java.awt.*;

public class GameStateManager {

	private GameState state;
	public static GameState menu;
	public static GameState map;

	public GameStateManager() {

		menu = new MenuState(this);
		map  = new MapState(this);
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
