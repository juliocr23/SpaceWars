package GameState;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Main.*;
import org.omg.Messaging.SYNC_WITH_TRANSPORT;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import Main.Background;

public class MenuState extends GameState {

	private Background bg;

	private int currentChoice = 0;
	private String[] options = {"Start", "Help", "Quit"};

	private Color titleColor;
	private Font titleFont;
	private Font font;

	public MenuState(GameStateManager gsm) {

		this.gsm = gsm;

		try {

			bg = new Background("Resources/Background/menubg.jpg", 1);

			titleColor = new Color(255, 0, 0);
			titleFont = new Font("Century Gothic", Font.BOLD, 45);
			font = new Font("Arial", Font.BOLD, 24);

		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {

		bg.update();
	}

	@Override
	public void processInput() {

		if(GamePanel.input.keyDownOnce(KeyEvent.VK_ENTER)) {
			select();
		}
		if(GamePanel.input.keyDownOnce(KeyEvent.VK_UP)) {
			currentChoice--;
			if(currentChoice <= -1) {
				currentChoice = 0;
			}
		}
		if(GamePanel.input.keyDownOnce(KeyEvent.VK_DOWN)) {
			currentChoice++;
			if(currentChoice >= 3) {
				currentChoice = 2;
			}
		}
	}

	@Override
	public void draw(Graphics g) {

		// draw bg
		bg.draw(g,GamePanel.WIDTH,GamePanel.HEIGHT);

		// draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("Zero Fighter", GamePanel.WIDTH/2-130, 80);

		// draw menu options
		g.setFont(font);
		for(int i = 0; i < options.length; i++) {
			if(i == currentChoice) {
				g.setColor(Color.BLACK);
			}
			else {
				g.setColor(Color.GRAY);
			}
			g.drawString(options[i], GamePanel.WIDTH/2-25, 310 + i * 25);
		}
	}

	public void select() {

		if(currentChoice == 0) {
			// start
			gsm.setState(GameStateManager.map);

		}
		if(currentChoice == 1) {
			// help

		}
		if(currentChoice == 2) {
			// quit
			System.exit(0);
		}
	}
}