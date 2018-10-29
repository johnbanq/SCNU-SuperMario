package sounds;
import java.io.FileInputStream;

import assets.MusicLoader;
import assets.MusicName;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;//jl1.jar

class GameMp3Player
{
	private MusicName playing;
	private Player player;
	
	GameMp3Player(MusicName name){
		this.playing = name;
	}
	
	 public void play(){
		try {
			FileInputStream fis = MusicLoader.loadStream(playing);
			player = new Player(fis);
			player.play();
		}catch(JavaLayerException e) {
			e.printStackTrace();
			System.err.println("unable to play "+playing.toString());
			System.exit(-1);
		}
	 }
	 
	 public void stop() {
		 player.close();
	 }
	 
	 public MusicName getMusicName() {
		 return playing;
	 }
	 
	 public void changeMusic(MusicName name) {
		 this.playing = name;
	 }
	 
	 public void changeMusicAndPlay(MusicName name){
		 changeMusic(name);
		 play();
	 }
	
}
