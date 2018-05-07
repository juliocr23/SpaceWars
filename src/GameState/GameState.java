package GameState;

import java.awt.Graphics;

public abstract class GameState {
	protected GameStateManager gsm;

	public abstract void init();
	public abstract void update();
	public abstract void processInput();
	public abstract void draw(Graphics g);
}
