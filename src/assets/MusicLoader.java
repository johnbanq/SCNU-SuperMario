package assets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.EnumMap;

public class MusicLoader {
	//a class to locate the bgm assets
	private static EnumMap<MusicName,String> paths;
	
	static {
		paths = new EnumMap<MusicName,String>(MusicName.class); 
		paths.put(MusicName.√∞œ’µ∫1,"src\\Mp3\\FunnyRabbit.mp3");
		paths.put(MusicName.√∞œ’µ∫2,"src\\Mp3\\√∞œ’µ∫1.mp3");
	}
	
	public static FileInputStream loadStream(MusicName name) {
		try {
			return new FileInputStream(new File(paths.get(name)));	
		}catch(NullPointerException e) {
			e.printStackTrace();
			System.err.println(name.toString()+" is not refering a valid asset!");
		}catch(SecurityException e) {
			e.printStackTrace();
			System.err.println(name.toString()+" cannot be loaded!");
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			System.err.println(name.toString()+" cannot be loaded!");			
		}
		System.exit(-1);
		return null;//just to please the IDE
	}
}
