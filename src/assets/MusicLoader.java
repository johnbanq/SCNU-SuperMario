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
		paths.put(MusicName.�˵�BGM,"src\\Mp3\\FunnyRabbit.mp3");
		paths.put(MusicName.��ϷBGM,"src\\Mp3\\ð�յ�1.mp3");
		//Sounds
		paths.put(MusicName.���,"src\\Aduio\\���.wav");
		paths.put(MusicName.������,"src\\Aduio\\������.wav");
		paths.put(MusicName.ˮ��,"src\\\\Aduio\\\\ˮ��.wav");
		paths.put(MusicName.ʧ��,"src\\Aduio\\ʧ��.wav");
		paths.put(MusicName.���,"src\\Aduio\\��̤.wav");
		paths.put(MusicName.ײ����,"src\\Aduio\\ײ����.wav");
		paths.put(MusicName.��ף,"src\\Aduio\\��ף.wav");
		paths.put(MusicName.������,"src\\Aduio\\���1.wav");
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
