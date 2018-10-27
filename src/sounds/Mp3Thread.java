package sounds;
import javazoom.jl.decoder.JavaLayerException;

public class Mp3Thread implements Runnable{
	
	private String music_name;//the music name which is playing
	private GameMp3Player music_player;
	
	Mp3Thread(String music_name){
		//starts with the music name to play
		this.music_name=music_name;
		music_player = new GameMp3Player(music_name);
	}
	
	public void run(){
		while(true){
			music_player.play();
		}
	}
	
	public String getMusicName() {
		return music_name;
	}
	
	public void changeMusic(String name)
	{
		music_name = name;
		music_player.setName(name);
	}
	
}
