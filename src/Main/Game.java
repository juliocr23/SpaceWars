package Main;

import javax.swing.JFrame;

public class Game {
	
	public static void main(String[] args) {
		
		JFrame window = new JFrame("Zero Fighter");
		window.setContentPane(new GamePanel());
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.pack();
		window.setLocationRelativeTo(null);     //Make the window appear in the center of the screen
		window.setVisible(true);

	}

}
