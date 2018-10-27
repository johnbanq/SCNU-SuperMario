package assets;

import java.io.File;
import java.util.Map;

public class MusicLocator {
	//a class to locate the bgm assets
	private static Map<MusicName,String> paths;
	
	static {
		paths.put(MusicName.√∞œ’µ∫1,"src\\Mp3\\FunnyRabbit.mp3");
		paths.put(MusicName.√∞œ’µ∫2,"src\\Mp3\\√∞œ’µ∫1.mp3");
	}
	
	public static String pathOf(MusicName name) {
		return paths.get(name);
	}
}
