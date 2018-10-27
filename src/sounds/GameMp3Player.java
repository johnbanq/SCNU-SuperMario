package sounds;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import assets.MusicLocator;
import assets.MusicName;
import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;//jl1.jar

class GameMp3Player
{
	private String music_name;
	private boolean is_played = false;
	
	GameMp3Player(String name){
		this.music_name=name;
	}
	
	 public void play(){
		FileInputStream fis = loadMusicFileStream(MusicName.valueOf(music_name));
		Player player = new Player(fis);
		if(is_played) {
			System.out.println("replay");
		}else {
			is_played = true;
		}
		player.play();
		System.out.println("GameMp3 Position "+player.getPosition());
	 }
	 
	 public void changeMusicAndPlay(String name){
		 this.music_name=name;
		 play();
	 }
	 
	 private FileInputStream loadMusicFileStream(MusicName name) {
		 	File f = new File(MusicLocator.pathOf(name));
		 	FileInputStream stream = new FileInputStream(f); 
			return stream;
	 }
}
