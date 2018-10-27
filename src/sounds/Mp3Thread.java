package sounds;
import assets.MusicName;
import javazoom.jl.decoder.JavaLayerException;

public class Mp3Thread implements Runnable{
	
	private GameMp3Player music_player;
	
	public Mp3Thread(MusicName name){
		//starts with the music name to play
		music_player = new GameMp3Player(name);
	}
	
	public void run(){
		while(true){
			music_player.play();
		}
	}
	
	public MusicName getMusicName() {
		return music_player.getMusicName();
	}
	
	public void changeMusic(MusicName name)
	{
		music_player.changeMusicAndPlay(name);
	}
	
}
