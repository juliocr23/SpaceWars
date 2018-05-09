package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import Audio.AudioPlayer;
import Main.Background;

public class GameOverState extends GameState {
	
	private Background bg;
	
	private AudioPlayer bgMusic;
	
	private Font font;
	
	private int currentChoice = 0;
	private String[] options = {"RETRY", "QUIT"};
	
	public GameOverState(GameStateManager gsm) {
		
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
	public void processInput() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		bg.draw(g);
		

		g.setColor(Color.RED);
		g.setFont(new Font("Comic Sans", Font.BOLD, 30));
		
		
		// draw menu options
		g.setFont(font);
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.WHITE);
			}
			else {
				g.setColor(Color.DARK_GRAY);
			}
			g.drawString(options[i], 350 + i * 10, 450 + i * 30);
		}
	}

}
