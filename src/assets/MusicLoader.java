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
		//BGM
		paths.put(MusicName.菜单BGM,"src\\Mp3\\FunnyRabbit.mp3");
		paths.put(MusicName.游戏BGM,"src\\Mp3\\冒险岛1.mp3");
		//Sounds
		paths.put(MusicName.点击,"src\\Aduio\\点击.wav");
		paths.put(MusicName.嚼东西,"src\\Aduio\\嚼东西.wav");
		paths.put(MusicName.水泡,"src\\\\Aduio\\\\水泡.wav");
		paths.put(MusicName.失败,"src\\Aduio\\失败.wav");
		paths.put(MusicName.打击,"src\\Aduio\\踩踏.wav");
		paths.put(MusicName.撞箱子,"src\\Aduio\\撞箱子.wav");
		paths.put(MusicName.庆祝,"src\\Aduio\\庆祝.wav");
		paths.put(MusicName.鼠标进入,"src\\Aduio\\鼠标1.wav");
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
