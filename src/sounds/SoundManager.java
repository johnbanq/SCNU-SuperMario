package sounds;

import assets.MusicName;

public class SoundManager {
	
	private Mp3Thread bgm_thread;
	
	public void playBGM(MusicName bgm_name) {
		if(bgm_thread!=null) {
			bgm_thread.changeMusicAndPlay(bgm_name);
		}else {
			bgm_thread = new Mp3Thread(bgm_name);
		}
	}
	
	public void stopBGM() {
		if(bgm_thread != null)
			bgm_thread.quit();
		bgm_thread = null;
	}
	
	public void playSound() {
		//todo......
	}
	
}
