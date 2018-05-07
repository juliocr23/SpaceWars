package Main;

import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Game {
	public static void main(String[] args) {
		final  GamePanel game = new GamePanel();

		game.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				game.onWindowClosing();
			}
		});

		SwingUtilities.invokeLater(() -> {
			game.start();
		});
	}

}
