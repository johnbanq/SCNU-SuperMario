package assets;

import java.io.File;
import java.util.Map;

public class MusicLocator {
	//a class to locate the bgm assets
	private static Map<MusicName,String> paths;
	
	static {
		paths.put(MusicName.ð�յ�1,"src\\Mp3\\FunnyRabbit.mp3");
		paths.put(MusicName.ð�յ�2,"src\\Mp3\\ð�յ�1.mp3");
	}
	
	public static String pathOf(MusicName name) {
		return paths.get(name);
	}
}
