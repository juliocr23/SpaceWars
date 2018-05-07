package Main;
import GameState.GameStateManager;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.*;

@SuppressWarnings("serial")
public class GamePanel extends JFrame implements Runnable {

	public static KeyboardInput input;

	private BufferStrategy bs;                 //Use for page flipping or double

	// dimensions
	public static final int WIDTH = 320*2;
	public static final int HEIGHT = 240*2;

	// game thread
	private Thread thread;
	private boolean running;
	private Canvas canvas;

	// image
	private BufferedImage image;
	private Graphics2D g;

	// game state manager
	private GameStateManager gsm;

	public void start() {

		//Setup the canvas
		canvas = new Canvas();
		canvas.setSize(WIDTH, HEIGHT);
		canvas.setBackground(Color.WHITE);
		canvas.setIgnoreRepaint(true);

		//Add keyboard listener to the canvas
		input = new KeyboardInput();
		canvas.addKeyListener(input);

		getContentPane().add(canvas);
		setTitle("Zero Fighter");
		setIgnoreRepaint(true);
		setResizable(false);
		pack();

		setLocationRelativeTo(null);
		setVisible(true);

		canvas.createBufferStrategy(2);
		bs = canvas.getBufferStrategy();
		canvas.requestFocus();

		gsm = new GameStateManager();

		thread = new Thread(this);
		thread.start();
	}

	public void run() {

		running = true;

		double timePerTick= 1.0E9/60;
		double delta = 0;

		long start  = System.nanoTime();
		long end    = start;

		while (running) {

			start = System.nanoTime();
			delta += (start-end)/timePerTick;    //Calculate target 60 fps
			end = start;

			//Make sure to run the program 60 fps
			if(delta >= 1) {
				processInput();
				update();
				renderFrame();
				delta--;
			}
		}
	}

	private void processInput(){
		input.poll();
		gsm.processInput();

	}

	private void update() {
		gsm.update();
	}

	private void draw(Graphics g) {
		gsm.draw(g);
	}

	protected void renderFrame() {
		do {
			do {
				Graphics g = null;
				try {
					g = bs.getDrawGraphics();
					g.clearRect(0, 0, getWidth(), getHeight());
					draw(g);
				} finally {
					if (g != null) {
						g.dispose();
					}
				}
			} while (bs.contentsRestored());
			bs.show();
		} while (bs.contentsLost());
	}

	public  void onWindowClosing() {
		try {
			running = false;
			thread.join();
		} catch( InterruptedException e ) {
			e.printStackTrace();
		}
		System.exit( 0 );
	}
}