package sounds;
import assets.MusicName;
import javazoom.jl.decoder.JavaLayerException;

public class Mp3Thread extends Thread{
	
	private GameMp3Player music_player;
	private volatile boolean stopping = false;
	
	public Mp3Thread(MusicName name){
		music_player = new GameMp3Player(name);
	}
	
	public void run(){
		while(!stopping){
			music_player.play();	
		}
	}
	
	public void quit() {
		stopping = true;
		music_player.stop();
	}
	
	public MusicName getMusicName() {
		return music_player.getMusicName();
	}
	
	public void changeMusicAndPlay(MusicName name){
		music_player.changeMusicAndPlay(name);
	}
	
}
