package sounds;

import assets.MusicName;

public class SoundManager {
	
	private BGMThread bgm_thread;
	private SoundThread sound_thread;
	
	public void playBGM(MusicName bgm_name) {
		if(bgm_thread!=null) {
			stopBGM();
		}
		bgm_thread = new BGMThread(bgm_name);
		bgm_thread.start();
	}
	
	public void stopBGM() {
		if(bgm_thread!=null) {
			bgm_thread.stop();
		}
	}
	
	public void playSound(MusicName name) {
		if(sound_thread!=null) {
			stopSound();
		}
		sound_thread = new SoundThread(name);
		sound_thread.start();
	}
	
	public void stopSound() {
		if(sound_thread!=null) {
			sound_thread.stop();
		}
	}
	
}
