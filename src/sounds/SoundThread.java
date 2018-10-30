package sounds;
import java.io.FileInputStream;
import java.io.IOException;
import assets.MusicLoader;
import assets.MusicName;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class SoundThread extends Thread{
	
	private MusicName music_name;
	private AudioStream as;
	
	public SoundThread(MusicName name) {
		this.music_name = name;
		try {
			FileInputStream fis = MusicLoader.loadStream(name);
			as = new AudioStream(fis);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run(){
		AudioPlayer.player.start(as);
	}

	public MusicName getMusicName() {
		return music_name;
	}

}
