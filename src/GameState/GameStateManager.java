package GameState;

import java.awt.*;

public class GameStateManager {

	private GameState[] gameStates;
	private int currentState;
	private GameState state;
	
	private int score;
	
	public static final int MENUSTATE = 0;
	public static final int LEVEL1STATE = 1;
	public static final int LEVEL2STATE = 2;
	public static final int GAMEOVERSTATE = 3;

	public GameStateManager() {
		
		gameStates = new GameState[4];
		
		currentState = MENUSTATE;
		loadState(currentState);
		
	}

	private void loadState(int state) {
		if(state == MENUSTATE)
			gameStates[state] = new MenuState(this);
		if(state == LEVEL1STATE)
			gameStates[state] = new Level1State(this);
		if(state == LEVEL2STATE)
			gameStates[state] = new Level2State(this);
		if(state == GAMEOVERSTATE)
			gameStates[state] = new GameOverState(this);
	}
	
	private void unloadState(int state) {
		gameStates[state] = null;
	}
	
	public void setState(int state) {
		unloadState(currentState);
		currentState = state;
		loadState(currentState);

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
