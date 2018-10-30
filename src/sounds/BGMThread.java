package sounds;
import java.io.FileInputStream;

import assets.MusicLoader;
import assets.MusicName;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class BGMThread extends Thread{
	
	private Player player; 
	private MusicName music_name;
	
	public BGMThread(MusicName name) {
		this.music_name = music_name;
		try {
			FileInputStream fis = MusicLoader.loadStream(name);
			player = new Player(fis);
		} catch (JavaLayerException e) {
			e.printStackTrace();
			System.err.println("unable to play "+getMusicName().toString());
			System.exit(-1);
		}
	}
	
	public void run(){
		while(true){
			try {
				player.play();
			} catch (JavaLayerException e) {
				e.printStackTrace();
				System.err.println("unable to play "+getMusicName().toString());
				System.exit(-1);
			}	
		}
	}

	public MusicName getMusicName() {
		return music_name;
	}

}
