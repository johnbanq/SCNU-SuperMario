package util;

import java.awt.Image;

public class FrameSequence {
	private int current_frame = 0;
	private Image[] frames;
	
	public FrameSequence(Image[] frames) {
		this.frames = frames.clone();
	}
	
	public void next() {
		current_frame = (current_frame+1)%frames.length;
	}
	
	public Image currentFrame() {
		return frames[current_frame];
	}
	
	public Image currentFrameAndNext() {
		Image frame = frames[current_frame];
		next();
		return frame;
	}
	
}
