package Main;

import java.awt.Image;
import java.util.ArrayList;


// 	The animation class manages a series of images (frames) and the amount of time to display each frame
public class Animation {
	
	private ArrayList frames;
	private int currentFrameIndex;
	private long animTime;
	private long totalDuration;
	
	
	// 	Constructor - creates a new, empty Animation.
	public Animation() {
		frames = new ArrayList();
		totalDuration = 0;
		start();
	}
	
	
	//	 Adds an image to the animation with the specified duration (time to display the image).
	public synchronized void addFrame(Image image, long duration) {
		totalDuration += duration;
		frames.add(new AnimFrame(image, totalDuration));
	}
	
	
	// 	Starts this animation over from the beginning
	public synchronized void start() {
		animTime = 0;
		currentFrameIndex = 0;
	}
	
	
	// 	Updates this animation's current image (frame), if necessary.
	public synchronized void update(long elapsedTime) {
		if(frames.size() > 1) {
			animTime += elapsedTime;
			
			if(animTime >= totalDuration) {
				animTime = animTime % totalDuration; // 	Makes sure the animation time starts over when the animation is done so that it loops
				currentFrameIndex = 0;
			}
			
			while(animTime > getFrame(currentFrameIndex).endTime) {
				currentFrameIndex++;
			}
		}
	}
	
	
	// 	Gets this Animation's current image. Returns null if this animation has no images.
	public synchronized Image getImage() {
		if (frames.size() == 0) {
			return null;
		}
		
		else{
			return getFrame(currentFrameIndex).image;
		}
	}
	
	
	private AnimFrame getFrame(int i) {
		return (AnimFrame)frames.get(i);
	}
	
	private class AnimFrame {
		Image image;
		long endTime;
		
		public AnimFrame(Image image, long endTime) {
			this.image = image;
			this.endTime = endTime;
		}
	}
}
