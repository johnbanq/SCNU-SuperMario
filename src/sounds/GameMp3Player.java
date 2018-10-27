package sounds;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import assets.MusicLoader;
import assets.MusicName;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;//jl1.jar

class GameMp3Player
{
	private MusicName playing;
	private boolean is_played = false;
	
	GameMp3Player(MusicName name){
		this.playing = name;
	}
	
	 public void play(){
		FileInputStream fis = MusicLoader.loadStream(playing);
		try {
			Player player = new Player(fis);
			if(is_played) {
				System.out.println("replay");
			}else {
				is_played = true;
			}
			player.play();
			System.out.println("GameMp3 Position "+player.getPosition());	
		}catch(JavaLayerException e) {
			e.printStackTrace();
			System.err.println("unable to play "+playing.toString());
			System.exit(-1);
		}
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
